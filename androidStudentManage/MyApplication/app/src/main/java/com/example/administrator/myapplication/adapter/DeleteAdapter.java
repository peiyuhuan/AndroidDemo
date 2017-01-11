package com.example.administrator.myapplication.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.bean.Student;

import java.util.List;

/**
 * Created by Administrator on 2016/11/26.
 */
public class DeleteAdapter extends BaseAdapter {

    List<Student> students;

    public DeleteAdapter(List<Student> students) {
        this.students = students;
    }

    //返回列表中的数据个数大小
    @Override
    public int getCount() {
        return students.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View v = View.inflate(viewGroup.getContext(), R.layout.item_allstudent,null);
        TextView name = (TextView) v.findViewById(R.id.name);
        TextView code = (TextView) v.findViewById(R.id.code);
        name.setText(students.get(i).getName());
        code.setText(students.get(i).getCode()+"");


        return v;
    }
}
