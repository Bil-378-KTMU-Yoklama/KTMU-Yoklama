package com.example.loginactivation.service.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LessonList {

    @SerializedName("id")
    @Expose
    private Integer lessonId;
    @SerializedName("kod")
    @Expose
    private String lessonKod;
    @SerializedName("faculty")
    @Expose
    private String lessonFaculty;
    @SerializedName("department")
    @Expose
    private String lessonDepartment;
    @SerializedName("week")
    @Expose
    private Integer lessonWeek;
    @SerializedName("status")
    @Expose
    private Boolean lessonStatus;
    @SerializedName("coachId")
    @Expose
    private Coach coachId;

    public Integer getLessonId() {
        return lessonId;
    }

    public void setLessonId(Integer lessonId) {
        this.lessonId = lessonId;
    }

    public String getLessonKod() {
        return lessonKod;
    }

    public void setLessonKod(String lessonKod) {
        this.lessonKod = lessonKod;
    }

    public String getLessonFaculty() {
        return lessonFaculty;
    }

    public void setLessonFaculty(String lessonFaculty) {
        this.lessonFaculty = lessonFaculty;
    }

    public String getLessonDepartment() {
        return lessonDepartment;
    }

    public void setLessonDepartment(String lessonDepartment) {
        this.lessonDepartment = lessonDepartment;
    }

    public Integer getLessonWeek() {
        return lessonWeek;
    }

    public void setLessonWeek(Integer lessonWeek) {
        this.lessonWeek = lessonWeek;
    }

    public Boolean getLessonStatus() {
        return lessonStatus;
    }

    public void setLessonStatus(Boolean lessonStatus) {
        this.lessonStatus = lessonStatus;
    }

    public Coach getCoachId() {
        return coachId;
    }

    public void setCoachId(Coach coachId) {
        this.coachId = coachId;
    }

}