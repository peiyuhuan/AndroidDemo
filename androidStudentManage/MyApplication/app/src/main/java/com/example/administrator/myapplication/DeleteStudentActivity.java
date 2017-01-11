package com.example.administrator.myapplication;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.administrator.myapplication.adapter.DeleteAdapter;
import com.example.administrator.myapplication.bean.Student;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/11/26.
 */
public class DeleteStudentActivity extends AppCompatActivity {
    List<Student> students;
    private DeleteAdapter deleteAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);
        ListView listView = (ListView) findViewById(R.id.listview);
        students = new ArrayList<>();
        query();
        //ctrl + alt + f
        deleteAdapter = new DeleteAdapter(students);

        listView.setAdapter(deleteAdapter);

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, final int index, long l) {


                AlertDialog.Builder builder = new AlertDialog.Builder(DeleteStudentActivity.this);
                builder.setTitle("提示");
                builder.setMessage("是否删除？？？");
                builder.setPositiveButton("删除？", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(DeleteStudentActivity.this, "删除", Toast.LENGTH_SHORT).show();
                        int _id = students.get(index).get_id();

                        App.getApp().getSqLiteDatabase().delete("stu","_id = ?",new String[]{_id+""});

                        students.remove(index);



                        deleteAdapter.notifyDataSetChanged();

                    }
                });
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(DeleteStudentActivity.this, "取消", Toast.LENGTH_SHORT).show();
                    }
                });

                builder.show();


                return true;
            }
        });


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, final int index, long l) {
                Intent intent= new Intent(DeleteStudentActivity.this,AlterStudentActivity.class);
                intent.putExtra("name",""+students.get(index).getName());
                intent.putExtra("code",""+students.get(index).getCode());
                intent.putExtra("_id",""+students.get(index).get_id());


                startActivityForResult(intent,22222);
            }
        });
    }


    private void query(){
        if(students != null) {
            students.clear();
        }


        Cursor cursor = App.getApp().getSqLiteDatabase().query("stu", null, null, null, null, null, null);


        while (cursor.moveToNext()){
            String name = cursor.getString( cursor.getColumnIndex("name"));
            int code = cursor.getInt( cursor.getColumnIndex("code"));
            int _id = cursor.getInt( cursor.getColumnIndex("_id"));
            Log.e("TAG",""+name+""+code);
            Student student = new Student(_id,name,code);
            students.add(student);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == 11111){
            Toast.makeText(DeleteStudentActivity.this, "刷新啦", Toast.LENGTH_SHORT).show();
            query();
            deleteAdapter.notifyDataSetChanged();
        }
    }
}
