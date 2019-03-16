package com.example.skyjar.dormitoryapp.Entities;

import java.io.Serializable;

public class User implements Serializable {
    private String fullname;
    private String email;
    private String sex;
    private String phone;
    private String birthdate;
    private int id;

    public User () {}

    public User(String fullname, String email, String sex, String phone, String birthdate, int id) {
        this.fullname = fullname;
        this.email = email;
        this.sex = sex;
        this.phone = phone;
        this.birthdate = birthdate;
        this.id = id;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "User{" +
                "fullname='" + fullname == null? "null":fullname +
                '}';
    }

}
