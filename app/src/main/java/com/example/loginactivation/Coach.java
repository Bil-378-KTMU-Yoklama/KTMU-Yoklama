package com.example.loginactivation.service.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Coach {


    @SerializedName("id")
    @Expose
    private Integer coachId;
    @SerializedName("kod")
    @Expose
    private String coachKod;
    @SerializedName("name")
    @Expose
    private String coachName;
    @SerializedName("surname")
    @Expose
    private String coachSurname;
    @SerializedName("userInfo")
    @Expose
    private UserInfo userInfo;

    @Override
    public String toString() {
        return this.coachName + " " + this.coachSurname;
    }

    public Integer getCoachId() {
        return coachId;
    }

    public void setCoachId(Integer coachId) {
        this.coachId = coachId;
    }

    public String getCoachKod() {
        return coachKod;
    }

    public void setCoachKod(String coachKod) {
        this.coachKod = coachKod;
    }

    public String getCoachName() {
        return coachName;
    }

    public void setCoachName(String coachName) {
        this.coachName = coachName;
    }

    public String getCoachSurname() {
        return coachSurname;
    }

    public void setCoachSurname(String coachSurname) {
        this.coachSurname = coachSurname;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

}