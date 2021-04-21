package com.example.marsapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

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

}
