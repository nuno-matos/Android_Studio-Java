package com.example.schoolgest.Activities;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.schoolgest.Classes.School;
import com.example.schoolgest.Classes.Student;
import com.example.schoolgest.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class
FormActivity extends AppCompatActivity {

    private EditText mStudentNumber;
    private EditText mName;
    private EditText mBirthDate;
    private EditText mAddress;
    private EditText mPhone;
    private EditText mMobilePhone;
    private EditText mEmail;
    private School mSelectedSchool;
    private TextView mFormTitle;

    private Button mBtnInsert;
    private DatePickerDialog mDatePicker;
    private Spinner mSchool;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

        mStudentNumber = findViewById(R.id.et_student_number);
        mName = findViewById(R.id.et_name);
        mBirthDate = findViewById(R.id.et_birth_date);
        mBirthDate.setInputType(InputType.TYPE_NULL);
        mAddress = findViewById(R.id.et_address);
        mPhone = findViewById(R.id.et_phone);
        mMobilePhone = findViewById(R.id.et_mobile_phone);
        mEmail = findViewById(R.id.et_email);
        mSchool = findViewById(R.id.sp_school);

        mBtnInsert = findViewById(R.id.btn_insert);

        mFormTitle = findViewById(R.id.tv_title);

        /* DATE PICKER */
        initDatePicker();

        /* ADAPTER SPINNER */
        initSpinner();

        //BUTTON
        initButtonListener();

        //Função de verifica se é editar ou inserir
        isAddOrEdit();

    }

    //################################ Date Picker #################################################
    private void initDatePicker() {
        mBirthDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar calendar = Calendar.getInstance();

                if (!mBirthDate.getText().toString().isEmpty()) {
                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                    try {
                        calendar.setTime(sdf.parse(mBirthDate.getText().toString()));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }

                int day = calendar.get(Calendar.DAY_OF_MONTH);
                int month = calendar.get(Calendar.MONTH);
                int year = calendar.get(Calendar.YEAR);

                mDatePicker = new DatePickerDialog(
                        FormActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        mBirthDate.setText(dayOfMonth + "/" + (month + 1) + "/" + year);
                    }
                }, year, month, day);

                mDatePicker.show();
            }
        });
    }

    //################################ Spinner #####################################################
    private void initSpinner() {
        final ArrayAdapter<School> adapterSpinner = new ArrayAdapter(this,
                android.R.layout.simple_spinner_dropdown_item, MainActivity.sSchoolList);
        adapterSpinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSchool.setAdapter(adapterSpinner);

        mSchool.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mSelectedSchool = (School) parent.getItemAtPosition(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    //################################ Listener #####################################################
    private void initButtonListener() {
        mBtnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Student newStudent = new Student();

                newStudent.setStudentNumber(Integer.parseInt(mStudentNumber.getText().toString()));
                newStudent.setName(mName.getText().toString());
                newStudent.setBirthDate(mBirthDate.getText().toString());
                newStudent.setAddress(mAddress.getText().toString());
                newStudent.setPhone(Integer.parseInt(mPhone.getText().toString()));
                newStudent.setMobilePhone(Integer.parseInt(mMobilePhone.getText().toString()));
                newStudent.setEmail(mEmail.getText().toString());
                newStudent.setSchool(mSelectedSchool);

                Intent replyIntent = new Intent();
                replyIntent.putExtra(MainActivity.EXTRA_STUDENT, newStudent);
                setResult(RESULT_OK, replyIntent);
                finish();
            }
        });
    }

    //################################ Function to Edit ############################################
    private void isAddOrEdit() {
        Intent intent = getIntent();
        Student selectedStudent = (Student) intent.getSerializableExtra(MainActivity.EXTRA_STUDENT);
        if (selectedStudent != null) {
            mStudentNumber.setText(selectedStudent.getStudentNumber().toString());
            mName.setText(selectedStudent.getName());
            mBirthDate.setText(selectedStudent.getBirthDate());
            mAddress.setText(selectedStudent.getAddress());
            mPhone.setText(selectedStudent.getPhone().toString());
            mMobilePhone.setText(selectedStudent.getMobilePhone().toString());
            mEmail.setText(selectedStudent.getEmail());
            mBtnInsert.setText(R.string.Update);
            mFormTitle.setText(R.string.Edit_Student);
            String schoolName = selectedStudent.getSchool().getName();
            findSpinnerPosition(schoolName);
            int position = findSpinnerPosition(schoolName);
            mSchool.setSelection(position);
        }
    }

    // function returns spinner Schools position
    private int findSpinnerPosition(String schoolName) {
        for (School school : MainActivity.sSchoolList) {
            if (school.getName().equals(schoolName)) {
                return MainActivity.sSchoolList.indexOf(school);
            }
        }
        return -1;
    }
}