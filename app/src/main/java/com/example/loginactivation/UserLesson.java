package com.example.loginactivation.service.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserLesson {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("kod")
    @Expose
    private String kod;
    @SerializedName("status")
    @Expose
    private Boolean status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getKod() {
        return kod;
    }

    public void setKod(String kod) {
        this.kod = kod;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return this.kod;
    }
}
