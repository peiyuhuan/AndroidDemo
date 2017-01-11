package com.example.administrator.myapplication;

import android.content.ContentValues;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class AlterStudentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alterstudent);

        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        String code = intent.getStringExtra("code");
        final String _id = intent.getStringExtra("_id");

        TextView textView = (TextView) findViewById(R.id.textView);
        textView.setText("请输入学生姓名(旧的："+name+")");


        TextView textView2 = (TextView) findViewById(R.id.textView2);
        textView2.setText("请输入学生学号(旧的："+code+")");

        final EditText et_name = (EditText) findViewById(R.id.et_name);
        final EditText et_code = (EditText) findViewById(R.id.et_code);


        findViewById(R.id.bt_add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ContentValues contentValues = new ContentValues();
                contentValues.put("name",et_name.getText().toString());
                contentValues.put("code",et_code.getText().toString());
                App.getApp().getSqLiteDatabase().update("stu",contentValues,"_id = ?",new String[]{_id});

                setResult(11111);
                finish();
            }
        });


    }
}
