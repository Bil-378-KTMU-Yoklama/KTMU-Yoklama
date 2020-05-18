package com.example.loginactivation.service;

public class ApiUtils {

    private ApiUtils() {}

    public static final String BASE_URL = "https://a68ac0f7.ngrok.io";

    public static ApiService getAPIService() {

        return RetrofitService.getApiClient(BASE_URL).create(ApiService.class);
    }
}
