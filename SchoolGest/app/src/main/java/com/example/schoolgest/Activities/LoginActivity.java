package com.example.schoolgest.Activities;

import static android.text.InputType.TYPE_TEXT_VARIATION_PASSWORD;
import static android.text.InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.schoolgest.Classes.AESCrypt;
import com.example.schoolgest.Classes.Preferences;
import com.example.schoolgest.Classes.UserLogin;
import com.example.schoolgest.R;

public class LoginActivity extends AppCompatActivity {

    public static final String USER_LOGIN_EXTRA = "USER_LOGIN_EXTRA";
    private final String sFILENAME = "LOGIN_ACTIVITY_PREFERENCES";

    private EditText mEditTextEmail;
    private EditText mEditTextPassword;
    private CheckBox mCheckBoxRememberMe;
    private Button mButtonLogin;

    private Preferences mPreferences;

    private ImageView mShowPassword;
    private boolean pwShow = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mEditTextEmail = findViewById(R.id.ed_email);
        mEditTextPassword = findViewById(R.id.ed_password);
        mCheckBoxRememberMe = findViewById(R.id.chkbox_remember);
        mButtonLogin = findViewById(R.id.btn_login);

        mShowPassword = findViewById(R.id.iv_show_password);

        mPreferences = new Preferences(this, sFILENAME);
        mPreferences.loadPreferences();

        mShowPassword.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!pwShow) {
                    pwShow = true;
                    mEditTextPassword.setInputType(InputType.TYPE_CLASS_TEXT | TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                } else {
                    pwShow = false;
                    mEditTextPassword.setInputType(InputType.TYPE_CLASS_TEXT | TYPE_TEXT_VARIATION_PASSWORD);
                }
            }
        });

        //RESTORE DATA
        if (mPreferences.getUserLogin() != null) {
            String pw = mPreferences.getUserLogin().getPassword();
            pw = AESCrypt.decrypt(pw);
            mEditTextEmail.setText(mPreferences.getUserLogin().getEmail());
            mEditTextPassword.setText(pw);
        }

        //SAVE DATA
        mButtonLogin.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                UserLogin userLogin = new UserLogin();
                userLogin.setEmail(mEditTextEmail.getText().toString());
                userLogin.setPassword(AESCrypt.encrypt(mEditTextPassword.getText().toString()));


                if (mCheckBoxRememberMe.isChecked()) {
                    mPreferences.setUserLogin(userLogin);
                    mPreferences.savePreferences();
                }

                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.putExtra(USER_LOGIN_EXTRA, userLogin);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onPause() {
        if (mPreferences.getUserLogin() != null) {
            String pw = mPreferences.getUserLogin().getPassword();
            pw = AESCrypt.decrypt(pw);
            mEditTextEmail.setText(mPreferences.getUserLogin().getEmail());
            mEditTextPassword.setText(pw);
        }
        super.onPause();
    }
}