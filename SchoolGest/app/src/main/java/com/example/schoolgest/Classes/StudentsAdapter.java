package com.example.schoolgest.Classes;

import android.content.Context;
import android.content.Intent;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.example.schoolgest.Activities.MainActivity;
import com.example.schoolgest.Activities.StudentDetailsActivity;
import com.example.schoolgest.R;

public class StudentsAdapter extends RecyclerView.Adapter<StudentViewHolder> {

    private final LayoutInflater mInflater;

    public StudentsAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public StudentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.student_list_row, parent, false);
        return new StudentViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(StudentViewHolder holder, final int position) {
        final Student student = MainActivity.sStudentList.get(position);
        holder.mStudentNumber.setText(student.getStudentNumber().toString());
        holder.mName.setText(student.getName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.sActivity, StudentDetailsActivity.class);
                intent.putExtra(MainActivity.EXTRA_STUDENT, student);
                MainActivity.sActivity.startActivity(intent);
            }
        });

        holder.itemView.setOnCreateContextMenuListener(new View.OnCreateContextMenuListener() {
            @Override
            public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
                menu.setHeaderTitle(R.string.select_option);
                menu.add(position, 1, 1, R.string.edit);
                menu.add(position, 2, 2, R.string.delete);
            }
        });
    }

    @Override
    public int getItemCount() {
        return MainActivity.sStudentList.size();
    }
}
