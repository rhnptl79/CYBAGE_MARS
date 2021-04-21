package com.example.marsapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.marsapp.data.CourseContentData;

import java.util.ArrayList;

public class AdapterCourseVideo extends RecyclerView.Adapter<AdapterCourseVideo.MyVideoHolder> {
    ArrayList<CourseContentData> _list;
    Context _context;
    public AdapterCourseVideo(ArrayList<CourseContentData> list, Context context) {
        _list=list;
        _context=context;
    }

    @NonNull
    @Override
    public AdapterCourseVideo.MyVideoHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.adapter_course_list, parent, false);

        return new MyVideoHolder(listItem);
    }
}
