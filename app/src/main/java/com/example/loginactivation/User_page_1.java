package com.example.loginactivation;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import com.example.loginactivation.service.ApiService;
import com.example.loginactivation.service.ApiUtils;
import com.example.loginactivation.service.model.GetStudents;
import com.example.loginactivation.service.model.UserLesson;
import com.example.loginactivation.service.model.Yoklama;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class User_page_1 extends AppCompatActivity {

    ListView listView, listView2;
    EditText name;
    Button face, submit;
    RadioButton theory, practice;
    RadioGroup radioGroup;
    private ApiService mAPIService;
    Spinner lesson;

    String Name, Date;

    public static int kod_id = 0;
    public static String kod_lesson = null;
    public static Boolean kod_status = null;

    private static ArrayAdapter<UserLesson> kod;
    private static ArrayAdapter<String> student_fio1;
    private static ArrayAdapter<Integer> student_id1;

    List<String> student_list_fio;
    List<Integer> student_list_id;
    private static List<Integer> students_id1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_page_1);
        mAPIService = ApiUtils.getAPIService();

        listView = findViewById(R.id.list);
        listView2 = findViewById(R.id.list2);
        listView2.setChoiceMode(AbsListView.CHOICE_MODE_MULTIPLE);

        lesson = findViewById(R.id.spinner);
        radioGroup = findViewById(R.id.gr);

        name = findViewById(R.id.name);
        face = findViewById(R.id.face_rec);
        submit = findViewById(R.id.submit);
        theory = findViewById(R.id.theory);
        practice = findViewById(R.id.practice);


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Name = name.getText().toString().trim();
                Date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
                students_yoklama(Name, Date, kod_id, getStudents_id());
            }
        });

        lesson.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                UserLesson userLesson = (UserLesson)parent.getItemAtPosition(position);
                kod_id = userLesson.getId();
                kod_lesson = userLesson.getKod();
                kod_status = userLesson.getStatus();

                getStudent(kod_id, kod_lesson, kod_status);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch(checkedId){
                    case R.id.theory:
                        break;
                    case R.id.practice:
                        break;
                }
            }
        });

        listView2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {

                // textView.setText("");
                int cntChoice = listView2.getCount();
                students_id1 = new ArrayList<>();
                String unchecked = "";
                SparseBooleanArray sparseBooleanArray = listView2
                        .getCheckedItemPositions();

                for (int i = 0; i < cntChoice; i++) {

                    if (sparseBooleanArray.get(i) == true) {
                        students_id1.add(Integer.valueOf(listView2.getItemAtPosition(i).toString()));
                    } else if (sparseBooleanArray.get(i) == false) {
                        unchecked += listView2.getItemAtPosition(i).toString()
                                + "\n";
                    }
                }
                Log.d("student", String.valueOf(getStudents_id()));
            }
        });



        Call<List<UserLesson>>allCategoriesListCall = mAPIService.getLesson(MainActivity.getToken());
        allCategoriesListCall.enqueue(new Callback<List<UserLesson>>() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onResponse(Call<List<UserLesson>> call, Response<List<UserLesson>>response) {
                if (response.isSuccessful()) {
                    kod = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, response.body());
                    lesson.setAdapter(kod);
                }
                else{
                    Log.d("LOG", "Ответ сервера: " + response.code());
                }
            }
            @Override
            public void onFailure(Call<List<UserLesson>> call, Throwable throwable) {
                Log.d("LOG", throwable.getMessage());
            }
        });

    }

    private void getStudent(int kod_id, String kod_lesson, Boolean kod_status) {

        JsonObject lesson = new JsonObject();
        try {
            JSONObject jsonObj_ = new JSONObject();
            jsonObj_.put("id",kod_id);
            jsonObj_.put("kod",kod_lesson);
            jsonObj_.put("status",kod_status);
            JsonParser jsonParser = new JsonParser();
            lesson = (JsonObject) jsonParser.parse(jsonObj_.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        Call<List<GetStudents>> call = mAPIService.getStudents(MainActivity.getToken(), lesson);
        call.enqueue(new Callback<List<GetStudents>>() {
            @Override
            public void onResponse(@NonNull Call<List<GetStudents>> call, @NonNull Response<List<GetStudents>> response) {
                if (response.isSuccessful()) {
                    List<GetStudents> message = response.body();

                    student_list_fio = new ArrayList<>();
                    student_list_id = new ArrayList<>();
                    for(GetStudents example : message){
                        student_list_fio.add(example.getName() + " " + example.getSurname());
                        student_list_id.add(example.getId());
                        Log.d("student", example.getName());
                    }

                    student_fio1 = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, student_list_fio);
                    listView.setAdapter(student_fio1);

                    student_id1 = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_multiple_choice, student_list_id);
                    listView2.setAdapter(student_id1);

                } else {
                    System.out.println(response.body());
                    try {
                        System.out.println(response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    System.out.println("no access to resource");
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<GetStudents>> call, @NonNull Throwable t) {
                if (t.getMessage() != null) {
                    System.out.println("Error occurred");
                    Log.d("Error", t.getMessage());
                }
            }
        });
    }

    private void students_yoklama(String name, String date, Integer lesson_id, List<Integer> students_id){
        Yoklama yoklama = new Yoklama(name, date, lesson_id, students_id);
        Call<Yoklama> postCall = mAPIService.yoklama(MainActivity.getToken(), yoklama);
        postCall.enqueue(new Callback<Yoklama>() {
            @Override
            public void onResponse(Call<Yoklama> call, Response<Yoklama> response) {
                if(!response.isSuccessful()){
                    Log.d("LOG1", "Ответ сервера: " + response.code());
                    return;
                }
                else{
                    Yoklama post1 = response.body();
                }
            }
            @Override
            public void onFailure(Call<Yoklama> call, Throwable t) {
                Log.d("Log",t.getMessage());
            }
        });
    }

    public static List<Integer> getStudents_id() {
        return students_id1;
    }

    public static void setStudents_id(List<Integer> students_id) {
        User_page_1.students_id1 = students_id;
    }
}
