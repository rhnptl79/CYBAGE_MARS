package com.example.marsapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.marsapp.CourseVideoListActivity;
import com.example.marsapp.EntranceTestActivity;
import com.example.marsapp.R;
import com.example.marsapp.data.CourseData;

import java.util.ArrayList;

public class AdapterCourseList extends RecyclerView.Adapter<AdapterCourseList.MyViewHolder> {

    ArrayList<CourseData> _dataList;
    Context _context;

    public AdapterCourseList(Context courseListActivity, ArrayList<CourseData> data) {
        this._dataList = data;
        this._context = courseListActivity;
    }

    @NonNull
    @Override
    public AdapterCourseList.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.adapter_course_list, parent, false);

        return new AdapterCourseList.MyViewHolder(listItem);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterCourseList.MyViewHolder holder, int position) {
        holder.tv_name.setText(_dataList.get(position).getCourseName());
        holder.tv_type.setText(_dataList.get(position).getCourseFee());
        if (_dataList.get(position).isPaid()) {
            holder.parentView.setOnClickListener(v -> {
                Intent intent;
                if (_dataList.get(position).getCourseFee().equalsIgnoreCase("Purchased")) {
                    intent = new Intent(_context, CourseVideoListActivity.class);
                } else {
                    intent = new Intent(_context, EntranceTestActivity.class);
                }
                intent.putExtra("id", _dataList.get(position).getCourseId());
                intent.putExtra("name", _dataList.get(position).getCourseName());
                intent.putExtra("fees", _dataList.get(position).getCourseFee().replace("$", ""));
                _context.startActivity(intent);
            });
        } else {
            holder.parentView.setOnClickListener(v -> {
                Intent intent = new Intent(_context, CourseVideoListActivity.class);
                intent.putExtra("id", _dataList.get(position).getCourseId());
                intent.putExtra("name", _dataList.get(position).getCourseName());
                intent.putExtra("fees", _dataList.get(position).getCourseFee().replace("$", ""));
                _context.startActivity(intent);
            });
        }


    }

    @Override
    public int getItemCount() {
        return _dataList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tv_name, tv_type;
        ConstraintLayout parentView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_name = itemView.findViewById(R.id.tv_name);
            tv_type = itemView.findViewById(R.id.tv_type);
            parentView = itemView.findViewById(R.id.parentView);
        }
    }

}
