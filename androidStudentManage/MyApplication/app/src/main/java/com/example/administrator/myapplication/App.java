package com.example.administrator.myapplication;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

/**
 * Created by Administrator on 2016/11/26.
 */
public class App extends Application {
    static App app;
    SQLiteDatabase sqLiteDatabase;
    @Override
    public void onCreate() {
        super.onCreate();

        Toast.makeText(App.this, "Application", Toast.LENGTH_SHORT).show();

        app = this;
        MySqliteHelper mySqliteHelper = new MySqliteHelper(App.this,"student.db",null,1);
        sqLiteDatabase = mySqliteHelper.getWritableDatabase();

    }


    public static App getApp(){
        return app;
    }

    public SQLiteDatabase getSqLiteDatabase() {
        return sqLiteDatabase;
    }
}
