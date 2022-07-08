package com.example.schoolgest.Classes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.example.schoolgest.Activities.MainActivity;
import com.example.schoolgest.R;

public class SchoolAdapter extends RecyclerView.Adapter<SchoolViewHolder> {

    private final LayoutInflater mInflater;

    public SchoolAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
    }


    @Override
    public SchoolViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.school_list_row, parent, false);
        return new SchoolViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(SchoolViewHolder holder, int position) {
        final School school = MainActivity.sSchoolList.get(position);
        holder.mSchoolName.setText(school.getName());
        holder.mSchoolInitials.setText(school.getInitials());
        holder.mSchoolCity.setText(school.getCity());
    }

    @Override
    public int getItemCount() {
        return MainActivity.sSchoolList.size();
    }
}
