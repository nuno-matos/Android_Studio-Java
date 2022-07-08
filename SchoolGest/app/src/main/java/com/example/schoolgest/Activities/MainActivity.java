package com.example.schoolgest.Activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.schoolgest.Classes.School;
import com.example.schoolgest.Classes.Student;
import com.example.schoolgest.Classes.StudentsAdapter;
import com.example.schoolgest.Classes.UserLogin;
import com.example.schoolgest.R;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    public static final String EXTRA_STUDENT = "com.example.schoolgest3.extra.EXTRA_STUDENT";
    public static final String EXTRA_SCHOOL = "com.example.schoolgest3.extra.EXTRA_SCHOOL";

    public static Activity sActivity;

    public static ArrayList<Student> sStudentList = new ArrayList<>();
    public static ArrayList<School> sSchoolList = new ArrayList<>();

    private RecyclerView mRecyclerView;
    private StudentsAdapter mAdapter;
    private TextView mUser;

    private int FORM_REQUEST_ADD = 1;
    private int FORM_REQUEST_UPDATE = 2;
    private int SCHOOL_FORM_REQUEST_ADD = 3;
    private int SCHOOL_LIST = 4;

    public int mStudentPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //MainActivity
        sActivity = this;
        mRecyclerView = (RecyclerView) findViewById(R.id.student_recyclerview);
        mAdapter = new StudentsAdapter(this);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.addItemDecoration(new DividerItemDecoration(
                this, LinearLayoutManager.VERTICAL));
        mRecyclerView.setAdapter(mAdapter);
        //Add Schools for spinner
        createSchool();
        //Add Student to the list for testing
        createStudent();

        Intent intent = getIntent();
        UserLogin userLogged = (UserLogin) intent.getSerializableExtra(LoginActivity.USER_LOGIN_EXTRA);
        mUser = findViewById(R.id.tv_user);
        mUser.setText(userLogged.getEmail());

    }

    //############################################ Menu ############################################
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    //################################## On Options Item Selected ##################################
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_add) {
            Intent intent = new Intent(getApplicationContext(), FormActivity.class);
            startActivityForResult(intent, FORM_REQUEST_ADD);
            return true;
        } else if (id == R.id.action_add_school) {
            Intent intent = new Intent(getApplicationContext(), SchoolFormActivity.class);
            startActivityForResult(intent, SCHOOL_FORM_REQUEST_ADD);
            return true;
        } else if (id == R.id.action_list_school) {
            Intent intent = new Intent(getApplicationContext(), SchoolActivity.class);
            startActivityForResult(intent, SCHOOL_LIST);
        }
        return super.onOptionsItemSelected(item);
    }

    //################################ On Activity Result Event ####################################
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == FORM_REQUEST_ADD) {
            if (resultCode == RESULT_OK) {
                int studentListSize = sStudentList.size();
                Student newStudent = (Student) data.getSerializableExtra(EXTRA_STUDENT);
                sStudentList.add(newStudent);
                mRecyclerView.getAdapter().notifyItemInserted(studentListSize);
                mRecyclerView.smoothScrollToPosition(studentListSize);
            }
        } else if (requestCode == FORM_REQUEST_UPDATE) {
            if (resultCode == RESULT_OK) {
                Student newStudent = (Student) data.getSerializableExtra(EXTRA_STUDENT);
                sStudentList.set(mStudentPosition, newStudent);
                mAdapter.notifyDataSetChanged();
            }
        } else if (requestCode == SCHOOL_FORM_REQUEST_ADD) {
            if (resultCode == RESULT_OK) {
                School newSchool = (School) data.getSerializableExtra(EXTRA_SCHOOL);
                sSchoolList.add(newSchool);
            }
        }
    }

    //################################ On Context Item Selected ####################################
    @Override
    public boolean onContextItemSelected(@NonNull final MenuItem item) {
        Intent intent;
        final Student student = sStudentList.get(item.getGroupId());
        mStudentPosition = item.getGroupId();

        switch (item.getItemId()) {
            case 1:
                //Edit Student
                intent = new Intent(sActivity, FormActivity.class);
                intent.putExtra(MainActivity.EXTRA_STUDENT, student);
                startActivityForResult(intent, FORM_REQUEST_UPDATE);
                return true;
            case 2:
                //Delete Student
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage(R.string.dialog_message)
                        .setTitle(R.string.dialog_title);
                builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        sStudentList.remove(item.getGroupId());
                        mAdapter.notifyDataSetChanged();
                    }
                });

                builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // NOTHING TO DO
                    }
                });

                AlertDialog dialog = builder.create();
                dialog.show();
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }

    //################################ Function Create Schools #####################################
    private void createSchool() {
        School school = new School("Escola Superior de Tecnologia e Gestão",
                "ESTG", "Portalegre");
        sSchoolList.add(school);

        school = new School("Escola Superior de Educação e Ciências Sociais",
                "ESECS", "Portalegre");
        sSchoolList.add(school);

        school = new School("Escola Superior de Saúde", "ESS", "Portalegre");
        sSchoolList.add(school);

        school = new School("Escola Superior Agrária", "ESAE", "Elvas");
        sSchoolList.add(school);
    }

    //################################ Function Create Students ####################################
    private void createStudent() {
        School school = sSchoolList.get(0);

        Student student = new Student(16203, "Marcelo Sousa",
                "3/10/1995", "Destany Ports Str.",
                537463930, 319145134, "Marcelo.Sousa@hotmail.com", school);
        sStudentList.add(student);

        school = sSchoolList.get(1);

        student = new Student(19002, "Beatriz Branco",
                "28/6/2007", "Avn. Rasheed Dam",
                334303016, 646003985, "Beat_Br79@yahoo.com", school);
        sStudentList.add(student);

        school = sSchoolList.get(2);

        student = new Student(12948, "Melissa Brito",
                "21/5/2006", "Quigley Drive Str.",
                163587908, 677557349, "Mel.B06@hotmail.com", school);
        sStudentList.add(student);
    }
}