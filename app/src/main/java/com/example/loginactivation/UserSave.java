package com.example.loginactivation.service.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserSave {

    @SerializedName("username")
    @Expose
    private String username;
    @SerializedName("password")
    @Expose
    private String password;
    @SerializedName("status")
    @Expose
    private String status;

    public UserSave(String username, String password, String status) {
        this.username = username;
        this.password = password;
        this.status = status;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}