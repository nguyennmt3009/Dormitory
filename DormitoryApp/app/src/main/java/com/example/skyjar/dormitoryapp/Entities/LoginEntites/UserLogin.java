package com.example.skyjar.dormitoryapp.Entities.LoginEntites;

public class UserLogin {
    private String grant_type;
    private String username;
    private String password;

    public UserLogin(String username, String password) {
        this.grant_type = "password";
        this.username = username;
        this.password = password;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "grant_type=" + grant_type + "&username=" + username + "&password=" + password;
    }
}
