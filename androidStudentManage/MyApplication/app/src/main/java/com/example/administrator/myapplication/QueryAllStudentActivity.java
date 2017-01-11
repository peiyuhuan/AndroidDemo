package com.example.administrator.myapplication;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.administrator.myapplication.adapter.QueryAllStudentAdapter;
import com.example.administrator.myapplication.bean.Student;

import java.util.ArrayList;
import java.util.List;

public class QueryAllStudentActivity extends AppCompatActivity {
    List<Student> students;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_query_all_student);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerview);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        students = new ArrayList<>();
        Cursor cursor = App.getApp().getSqLiteDatabase().query("stu", null, null, null, null, null, null);


        while (cursor.moveToNext()){
            String name = cursor.getString( cursor.getColumnIndex("name"));
            int code = cursor.getInt( cursor.getColumnIndex("code"));
            Log.e("TAG",""+name+""+code);
            Student student = new Student(name,code);
            students.add(student);
        }



        QueryAllStudentAdapter queryAllStudentAdapter = new QueryAllStudentAdapter(students);
        recyclerView.setAdapter(queryAllStudentAdapter);
    }
}
