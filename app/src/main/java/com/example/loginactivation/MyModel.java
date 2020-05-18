package com.example.loginactivation.service.model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

//модель для результата который придет от сервера
public class MyModel {
    /*@SerializedName("imageUrl")
    @Expose
    private String imageUrl;
    @SerializedName("name")
    @Expose
    private String name;

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }*/


   /* @SerializedName("id")
    @Expose
    private int user_id;
    @SerializedName("username")
    @Expose
    private String  user_login;
    @SerializedName("password")
    @Expose
    private String  user_password;
    @SerializedName("status")
    @Expose
    private String  user_status;

    */

    @SerializedName("kod")
    @Expose
    private String kod;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("surname")
    @Expose
    private String surname;
    @SerializedName("login")
    @Expose
    private String login;
    @SerializedName("password")
    @Expose
    private String password;

    private Integer id;

    public MyModel(String kod, String name, String surname, String login, String password) {
        this.kod = kod;
        this.name = name;
        this.surname = surname;
        this.login = login;
        this.password = password;
    }

    public MyModel() {

    }

  /*  public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getUser_login() {
        return user_login;
    }

    public void setUser_login(String user_login) {
        this.user_login = user_login;
    }

    public String getUser_password() {
        return user_password;
    }

    public void setUser_password(String user_password) {
        this.user_password = user_password;
    }

    public String getUser_status() {
        return user_status;
    }

    public void setUser_status(String user_status) {
        this.user_status = user_status;
    }
   */
    public String getKod() {
        return kod;
    }

    public void setKod(String kod) {
        this.kod = kod;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
