package com.example.loginactivation.service.model;


import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Register2Lesson {

    @SerializedName("kod")
    @Expose
    private String kod;
    @SerializedName("department")
    @Expose
    private String department;
    @SerializedName("faculty")
    @Expose
    private String faculty;
    @SerializedName("week")
    @Expose
    private Integer week;
    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("coach_id")
    @Expose
    private Integer coach_id;
    @SerializedName("students")
    @Expose
    private List<Integer> students = null;

    public Register2Lesson(String kod, String department, String faculty, Integer week, Boolean status, Integer coach_id, List<Integer> students) {
        this.kod = kod;
        this.department = department;
        this.faculty = faculty;
        this.week = week;
        this.status = status;
        this.coach_id = coach_id;
        this.students = students;
    }

    public String getKod() {
        return kod;
    }

    public void setKod(String kod) {
        this.kod = kod;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getFaculty() {
        return faculty;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    public Integer getWeek() {
        return week;
    }

    public void setWeek(Integer week) {
        this.week = week;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Integer getCoach_id() {
        return coach_id;
    }

    public void setCoach_id(Integer coach_id) {
        this.coach_id = coach_id;
    }

    public List<Integer> getStudents() {
        return students;
    }

    public void setStudents(List<Integer> students) {
        this.students = students;
    }

}