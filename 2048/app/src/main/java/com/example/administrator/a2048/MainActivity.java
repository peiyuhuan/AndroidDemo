package com.example.administrator.a2048;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView tv_number;

    public MainActivity() {
        this.mainActivity = this;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv_number = (TextView) findViewById(R.id.number);


    }

    private int number;

    public void addNumber(int number){
        this.number = this.number + number;

        tv_number.setText(this.number+"");
    }


    public void clearNumber(){
        this.number = 0;
        tv_number.post(new Runnable() {
            @Override
            public void run() {
                addNumber(number);
            }
        });

    }



    public static MainActivity mainActivity;


    public static MainActivity getMainActivity() {
        return mainActivity;
    }
}
