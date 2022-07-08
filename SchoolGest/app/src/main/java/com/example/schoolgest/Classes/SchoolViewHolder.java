package com.example.schoolgest.Classes;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.schoolgest.R;

public class SchoolViewHolder extends RecyclerView.ViewHolder {
    public TextView mSchoolName, mSchoolInitials, mSchoolCity;

    public SchoolViewHolder(View view) {
        super(view);
        mSchoolName = view.findViewById(R.id.tv_school_name);
        mSchoolInitials = view.findViewById(R.id.tv_school_initials);
        mSchoolCity = view.findViewById(R.id.tv_school_city);
    }
}
