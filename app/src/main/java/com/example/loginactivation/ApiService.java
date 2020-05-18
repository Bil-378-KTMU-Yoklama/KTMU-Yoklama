package com.example.loginactivation.service;

import com.example.loginactivation.service.model.Authentication;
import com.example.loginactivation.service.model.Coach;
import com.example.loginactivation.service.model.GetStudents;
import com.example.loginactivation.service.model.LessonList;
import com.example.loginactivation.service.model.Login;
import com.example.loginactivation.service.model.MyModel;
import com.example.loginactivation.service.model.Register2Lesson;
import com.example.loginactivation.service.model.StudentInfo;
import com.example.loginactivation.service.model.StudentPage;
import com.example.loginactivation.service.model.UserLesson;
import com.example.loginactivation.service.model.UserSave;
import com.example.loginactivation.service.model.Yoklama;
import com.google.gson.JsonObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;

public interface ApiService {
    @Headers({ "Content-Type: application/json; charset=utf-8",
            "x-ms-logging-context: com.microsoft.azure.management.servicebus.Namespaces regenerateKeys" })


    @POST("/login")
    Call<Authentication> login(@Body Login log);

    @POST("/user/save")
    Call<UserSave> userSave(@Body UserSave userSave);

    @GET("user/list")
    Call<List<MyModel>> exampleGet(@Header("Authorization") String token);

    @GET("coach/list")
    Call<List<Coach>> exampleGet2(@Header("Authorization") String token);

    @POST("/coach/register")
    Call<MyModel> examplePost(@Header("Authorization") String token,
                               @Body MyModel post);

    @POST("lesson/register1")
    Call<List<StudentInfo>> getStudentInfo( @Header("Authorization") String token,
                                            @Body JsonObject lesson);

    @POST("/lesson/register2")
    Call<Register2Lesson> examplePost2( @Header("Authorization") String token,
                                        @Body Register2Lesson post1);
    @GET("lesson/list")
    Call<List<LessonList>> exampleGet3( @Header("Authorization") String token);

    //user page

    @GET("coach/lessons")
    Call<List<UserLesson>> getLesson(@Header("Authorization") String token);

    @POST("/enroll/getStudents")
    Call<List<GetStudents>> getStudents(@Header("Authorization") String token,
                                        @Body JsonObject lesson);

    @POST("/yoklama/submit")
    Call<Yoklama> yoklama(@Header("Authorization") String token,
                          @Body Yoklama yoklama);

    @GET("/student/percentage")
    Call<List<StudentPage>> studentPage(@Header("Authorization") String token);

    @PUT("eaxmple")
    Call<List<MyModel>> examplePut();

    @DELETE("eaxmple")
    Call<List<MyModel>> exampleDelete();
}
