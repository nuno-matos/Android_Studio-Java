package com.example.schoolgest.Classes;

import java.io.Serializable;

public class UserLogin implements Serializable {
    private String mEmail;
    private String mPassword;

    public UserLogin() {
    }

    public UserLogin(String username, String password) {
        mEmail = username;
        mPassword = password;
    }

    public String getEmail() {
        return mEmail;
    }

    public void setEmail(String email) {
        mEmail = email;
    }

    public String getPassword() {
        return mPassword;
    }

    public void setPassword(String password) {
        mPassword = password;
    }
}