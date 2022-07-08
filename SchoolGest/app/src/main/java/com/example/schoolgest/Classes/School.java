package com.example.schoolgest.Classes;

import java.io.Serializable;

public class School implements Serializable {

    private String mName;
    private String mInitials;
    private String mCity;

    public School() {
    }

    public School(String name, String initials, String city) {
        mName = name;
        mInitials = initials;
        mCity = city;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getInitials() {
        return mInitials;
    }

    public void setInitials(String initials) {
        mInitials = initials;
    }

    public String getCity() {
        return mCity;
    }

    public void setCity(String city) {
        mCity = city;
    }

    @Override
    public String toString() {
        return this.mName;
    }
}