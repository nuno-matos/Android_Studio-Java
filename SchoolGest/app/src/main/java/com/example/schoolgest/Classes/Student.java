package com.example.schoolgest.Classes;

import java.io.Serializable;

public class Student implements Serializable {

    private Integer mStudentNumber;
    private String mName;
    private String mBirthDate;
    private String Address;
    private Integer mPhone;
    private Integer mMobilePhone;
    private String mEmail;
    private School mSchool;

    public Student() {

    }

    public Student(Integer studentNumber, String name, String birthDate, String address, Integer phone, Integer mobile, String email, School school) {
        mStudentNumber = studentNumber;
        mName = name;
        mBirthDate = birthDate;
        Address = address;
        mPhone = phone;
        mMobilePhone = mobile;
        mEmail = email;
        mSchool = school;
    }

    public Integer getStudentNumber() {
        return mStudentNumber;
    }

    public void setStudentNumber(Integer studentNumber) {
        mStudentNumber = studentNumber;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getBirthDate() {
        return mBirthDate;
    }

    public void setBirthDate(String birthDate) {
        mBirthDate = birthDate;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public Integer getPhone() {
        return mPhone;
    }

    public void setPhone(Integer phone) {
        mPhone = phone;
    }

    public Integer getMobilePhone() {
        return mMobilePhone;
    }

    public void setMobilePhone(Integer mobilePhone) {
        mMobilePhone = mobilePhone;
    }

    public String getEmail() {
        return mEmail;
    }

    public void setEmail(String email) {
        mEmail = email;
    }

    public School getSchool() {
        return mSchool;
    }

    public void setSchool(School school) {
        mSchool = school;
    }

    @Override
    public String toString() {
        return "Student{" +
                "mStudentNumber=" + mStudentNumber +
                ", mName='" + mName + '\'' +
                ", mBirthDate='" + mBirthDate + '\'' +
                ", Address='" + Address + '\'' +
                ", mPhone=" + mPhone +
                ", mMobilePhone=" + mMobilePhone +
                ", mEmail='" + mEmail + '\'' +
                ", mSchool=" + mSchool +
                '}';
    }
}
