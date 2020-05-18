package com.example.loginactivation.service;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class AuthenticationInterceptor implements Interceptor {

    private String authToken;

    AuthenticationInterceptor(String token) {
        this.authToken = token;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request originalRequest = chain.request();
        String authorizationToken = authToken;
        Request authorizedRequest = originalRequest.newBuilder()
                .header("Authorization", authorizationToken)
                .build();
        return chain.proceed(authorizedRequest);
    }
}
