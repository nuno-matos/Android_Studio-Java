package com.example.schoolgest.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.schoolgest.Classes.Student;
import com.example.schoolgest.R;

public class StudentDetailsActivity extends AppCompatActivity {

    private TextView mStudentNumber;
    private TextView mName;
    private TextView mBirthDate;
    private TextView mAddress;
    private TextView mPhone;
    private TextView mMobilePhone;
    private TextView mEmail;
    private TextView mSchool;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        Intent intent = getIntent();
        Student selectedStudent = (Student) intent.getSerializableExtra(MainActivity.EXTRA_STUDENT);

        mStudentNumber = (TextView) findViewById(R.id.tv_details_student_number);
        mName = (TextView) findViewById(R.id.tv_details_name);
        mBirthDate = (TextView) findViewById(R.id.tv_details_birth_date);
        mAddress = (TextView) findViewById(R.id.tv_details_address);
        mPhone = (TextView) findViewById(R.id.tv_details_phone);
        mMobilePhone = (TextView) findViewById(R.id.tv_details_mobile_phone);
        mEmail = (TextView) findViewById(R.id.tv_details_email);
        mSchool = (TextView) findViewById(R.id.tv_details_school);

        mStudentNumber.setText(selectedStudent.getStudentNumber().toString());
        mName.setText(selectedStudent.getName());
        mBirthDate.setText(selectedStudent.getBirthDate());
        mAddress.setText(selectedStudent.getAddress());
        mPhone.setText(selectedStudent.getPhone().toString());
        mMobilePhone.setText(selectedStudent.getMobilePhone().toString());
        mEmail.setText(selectedStudent.getEmail());
        mSchool.setText(selectedStudent.getSchool().getName());
    }
}