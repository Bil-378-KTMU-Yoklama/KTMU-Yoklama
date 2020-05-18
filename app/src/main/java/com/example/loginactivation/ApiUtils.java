package com.example.loginactivation.service;

public class ApiUtils {

    private ApiUtils() {}

    public static final String BASE_URL = "https://9ed5c5e1.ngrok.io";

    public static ApiService getAPIService() {

        return RetrofitService.getApiClient(BASE_URL).create(ApiService.class);
    }
}