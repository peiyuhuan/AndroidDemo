package com.example.administrator.myapplication;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.ConditionVariable;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

/**
 *  Created by Administrator on 2016/11/26.
 *
 */
public class AddStudentActivity extends AppCompatActivity {

    //activity 创建会调用
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_addstudent);
        setTitle("添加学生");


        final EditText et_name = (EditText) findViewById(R.id.et_name);
        final EditText et_code = (EditText) findViewById(R.id.et_code);



        findViewById(R.id.bt_add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = et_name.getText().toString();
                String code = et_code.getText().toString();


                for (int i = 0; i < 100; i++) {
                    ContentValues contentValues = new ContentValues();
                    contentValues.put("name",name+i);
                    contentValues.put("code",i);
                    App.getApp().getSqLiteDatabase().insert("stu",null,contentValues);
                }

                Toast.makeText(AddStudentActivity.this, "添加成功", Toast.LENGTH_SHORT).show();

            }
        });
    }





}
