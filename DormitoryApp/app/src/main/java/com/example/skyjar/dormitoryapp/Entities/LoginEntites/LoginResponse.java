package com.example.skyjar.dormitoryapp.Entities.LoginEntites;

import java.io.Serializable;

public class LoginResponse implements Serializable {

    private String error;
    private String error_description;
    private String access_token;
    private String token_type;
    private int expires_in;

    public LoginResponse() {
    }

    public LoginResponse(String error, String error_description, String access_token) {
        this.error = error;
        this.error_description = error_description;
        this.access_token = access_token;
    }

    public LoginResponse(String error, String error_description, String access_token, String token_type, int expires_in) {
        this.error = error;
        this.error_description = error_description;
        this.access_token = access_token;
        this.token_type = token_type;
        this.expires_in = expires_in;
    }

    public String getToken_type() {
        return token_type;
    }

    public void setToken_type(String token_type) {
        this.token_type = token_type;
    }

    public int getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(int expires_in) {
        this.expires_in = expires_in;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getError_description() {
        return error_description;
    }

    public void setError_description(String error_description) {
        this.error_description = error_description;
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    @Override
    public String toString() {
        return "Login{" +
                "error='" + error + '\'' +
                ", error_description='" + error_description + '\'' +
                ", access_token='" + access_token + '\'' +
                '}';
    }


}
