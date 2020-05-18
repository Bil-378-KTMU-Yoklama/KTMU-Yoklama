package com.example.loginactivation;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.loginactivation.service.ApiService;
import com.example.loginactivation.service.ApiUtils;
import com.example.loginactivation.service.model.StudentPage;
import com.example.loginactivation.service.model.UserLesson;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Student_page_1Activity extends AppCompatActivity {

    private ApiService mAPIService;
    List<String> yoklama;
    TextView textView;
    ListView listView;
    ArrayAdapter<String> arrayAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_studend_page_1);

        mAPIService = ApiUtils.getAPIService();
        listView = findViewById(R.id.list_st_page);

        Call<List<StudentPage>> allCategoriesListCall = mAPIService.studentPage(MainActivity.getToken());
        allCategoriesListCall.enqueue(new Callback<List<StudentPage>>() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onResponse(Call<List<StudentPage>> call, Response<List<StudentPage>> response) {
                if (response.isSuccessful()) {
                    List<StudentPage> studentPages = response.body();
                    yoklama = new ArrayList<>();
                    for (StudentPage studentPage:studentPages){
                        yoklama.add(studentPage.getName() + "              " + studentPage.getPercentage() + "              " + studentPage.getStatus());

                        arrayAdapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, yoklama);
                        listView.setAdapter(arrayAdapter);
                       // Log.d("Lesson", studentPage.getName() + " " + studentPage.getPercentage() + " " + studentPage.getStatus());

                    }
                }
                else{
                    Log.d("LOG", "Ответ сервера: " + response.code());
                }
            }
            @Override
            public void onFailure(Call<List<StudentPage>> call, Throwable throwable) {
                Log.d("LOG", throwable.getMessage());
            }
        });


    }
}
