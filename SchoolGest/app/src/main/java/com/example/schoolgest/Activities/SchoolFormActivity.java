package com.example.schoolgest.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.schoolgest.Classes.School;
import com.example.schoolgest.R;

public class SchoolFormActivity extends AppCompatActivity {

    private EditText mSchoolName;
    private EditText mSchoolInitials;
    private EditText mSchoolCity;

    private Button mButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_school_form);

        mSchoolName = findViewById(R.id.et_school_name);
        mSchoolInitials = findViewById(R.id.et_school_initials);
        mSchoolCity = findViewById(R.id.et_school_city);

        mButton = findViewById(R.id.btn_insert_school);

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                School newSchool = new School();
                newSchool.setName(mSchoolName.getText().toString());
                newSchool.setInitials(mSchoolInitials.getText().toString());
                newSchool.setCity(mSchoolCity.getText().toString());

                Intent replyIntent = new Intent();
                replyIntent.putExtra(MainActivity.EXTRA_SCHOOL, newSchool);
                setResult(RESULT_OK, replyIntent);
                finish();
            }
        });
    }
}
