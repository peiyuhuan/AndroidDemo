package com.example.administrator.myapplication.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.bean.Student;

import java.util.List;

/**
 * Created by Administrator on 2016/11/26.
 */
public class QueryAllStudentAdapter extends RecyclerView.Adapter {

    List<Student> students;

    public QueryAllStudentAdapter(List<Student> students) {
        this.students = students;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(parent.getContext(), R.layout.item_allstudent,null);


        return new MyViewHolder(view);
    }

    private class MyViewHolder extends RecyclerView.ViewHolder{
        TextView name;
        TextView code;

        public MyViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.name);
            code = (TextView) itemView.findViewById(R.id.code);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((MyViewHolder)holder).name.setText(students.get(position).getName());
        ((MyViewHolder)holder).code.setText(students.get(position).getCode()+"");
    }



    @Override
    public int getItemCount() {
        return students.size();
    }
}
