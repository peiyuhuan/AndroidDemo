package com.example.administrator.myapplication.bean;

/**
 * Created by Administrator on 2016/11/26.
 */
public class Student {

    private int _id;

    private String name;
    private int code;
    //alt + insert
    public Student(String name, int code) {
        this.name = name;
        this.code = code;
    }

    public Student(int _id, String name, int code) {
        this._id = _id;
        this.name = name;
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }
}
