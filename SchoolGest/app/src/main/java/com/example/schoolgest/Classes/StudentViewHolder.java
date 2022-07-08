package com.example.schoolgest.Classes;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.schoolgest.R;

public class StudentViewHolder extends RecyclerView.ViewHolder {
    public TextView mStudentNumber, mName;

    public StudentViewHolder(View view) {
        super(view);
        mStudentNumber = view.findViewById(R.id.tv_student_number);
        mName = view.findViewById(R.id.tv_name);
    }
}