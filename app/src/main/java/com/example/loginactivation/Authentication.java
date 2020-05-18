package com.example.loginactivation.service.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Authentication {
    @SerializedName("token")
    @Expose
    String token;
    @SerializedName("status")
    @Expose
    String status;
  //  @SerializedName("id")
 //   @Expose
 //   Integer id;
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

 //   public Integer getId() {
 //       return id;
//    }

    //public void setStatus(Integer id) {
   //     this.id = id;
   // }
}
