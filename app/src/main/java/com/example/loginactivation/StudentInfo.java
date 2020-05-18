package com.example.loginactivation.service.model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class StudentInfo {

    @SerializedName("id")
    @Expose
    private Integer studentId;
    @SerializedName("kod")
    @Expose
    private String studentKod;
    @SerializedName("name")
    @Expose
    private String studentName;
    @SerializedName("surname")
    @Expose
    private String studentSurname;
    @SerializedName("faculty")
    @Expose
    private String studentFaculty;
    @SerializedName("department")
    @Expose
    private String studentDepartment;

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public String getStudentKod() {
        return studentKod;
    }

    public void setStudentKod(String studentKod) {
        this.studentKod = studentKod;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentSurname() {
        return studentSurname;
    }

    public void setStudentSurname(String studentSurname) {
        this.studentSurname = studentSurname;
    }

    public String getStudentFaculty() {
        return studentFaculty;
    }

    public void setStudentFaculty(String studentFaculty) {
        this.studentFaculty = studentFaculty;
    }

    public String getStudentDepartment() {
        return studentDepartment;
    }

    public void setStudentDepartment(String studentDepartment) {
        this.studentDepartment = studentDepartment;
    }

    @Override
    public String toString() {
        return this.studentName + " " + this.studentSurname;
    }
}
