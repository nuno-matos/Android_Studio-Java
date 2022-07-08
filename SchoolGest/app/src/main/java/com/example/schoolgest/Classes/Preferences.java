package com.example.schoolgest.Classes;

import android.content.Context;
import android.content.SharedPreferences;

public class Preferences {

    private UserLogin mUserLogin = null;

    private SharedPreferences sharedPreferences;

    public Preferences(Context context, String fileName) {
        sharedPreferences = context.getSharedPreferences(fileName, Context.MODE_PRIVATE);
    }

    public void loadPreferences() {
        mUserLogin = new UserLogin();
        mUserLogin.setEmail(sharedPreferences.getString("username", ""));
        mUserLogin.setPassword(sharedPreferences.getString("password", ""));
    }

    public boolean savePreferences() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("username", mUserLogin.getEmail());
        editor.putString("password", mUserLogin.getPassword());
        return editor.commit();
    }

    public UserLogin getUserLogin() {
        return mUserLogin;
    }

    public void setUserLogin(UserLogin userLogin) {
        this.mUserLogin = userLogin;
    }
}