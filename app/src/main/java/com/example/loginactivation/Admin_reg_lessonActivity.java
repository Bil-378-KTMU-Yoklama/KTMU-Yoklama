package com.example.loginactivation;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.loginactivation.service.ApiService;
import com.example.loginactivation.service.ApiUtils;
import com.example.loginactivation.service.model.Coach;
import com.example.loginactivation.service.model.Register2Lesson;
import com.example.loginactivation.service.model.StudentInfo;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Admin_reg_lessonActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    Model model = new Model();
    String[] faculty = model.getFaculty();
    String[] department = model.getDepartment();
    String[] status = model.getStatus();

    EditText kod, week;
    Spinner spin_fac, spin_dep, spin_status;
    AutoCompleteTextView autoCompleteTextView;

    private static Boolean st;
    private static Integer teacher_id;

    ArrayAdapter aa, a, b;
    Button submit;

    private static String kod_lesson, fac, dep;

    String  weeks, teacher, status_;

    private static Integer weeks_;

    public static int coach_id = 0;

    private static List<Integer> list;
    private static List<String> student_fio;

    ArrayAdapter<Coach> arrayAdapter;

    private String LOG = "salam";
    private ApiService mAPIService;

    @Override
    protected void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_reg_lesson);

        mAPIService = ApiUtils.getAPIService();

        kod = findViewById(R.id.kod_lesson);
        week = findViewById(R.id.week);
        spin_fac = findViewById(R.id.fac);
        spin_fac.setOnItemSelectedListener(this);

        spin_dep = findViewById(R.id.dep);
        spin_dep.setOnItemSelectedListener(this);

        autoCompleteTextView = findViewById(R.id.teacher_spin);

        coach_list();

        spin_status = findViewById(R.id.status_spin);
        spin_status.setOnItemSelectedListener(this);

        submit = findViewById(R.id.submit);



        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                kod_lesson = kod.getText().toString().trim();
                weeks = week.getText().toString().trim();
                teacher = autoCompleteTextView.getText().toString().trim();
                status_ = spin_status.getSelectedItem().toString();
                weeks_ = Integer.parseInt(week.getText().toString());
                st = Boolean.parseBoolean(status_);

                if(!TextUtils.isEmpty(kod_lesson) && !TextUtils.isEmpty(weeks) && !TextUtils.isEmpty(fac) && !TextUtils.isEmpty(dep) && !TextUtils.isEmpty(teacher) && !TextUtils.isEmpty(status_)) {
                    lesson_fac_dep(fac, dep);
                }
            }
        });


        aa = new ArrayAdapter(this, android.R.layout.simple_spinner_item, faculty);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin_fac.setAdapter(aa);


        a = new ArrayAdapter(this, android.R.layout.simple_spinner_item, department);
        a.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin_dep.setAdapter(a);


        b = new ArrayAdapter(this, android.R.layout.simple_spinner_item, status);
        b.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin_status.setAdapter(b);


        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Coach coach = (Coach)parent.getItemAtPosition(position);
                System.out.println(coach);
                coach_id = coach.getCoachId();
            }
        });

    }

    private void coach_list() {
        Call<List<Coach>> allCategoriesListCall = mAPIService.exampleGet2(MainActivity.getToken());

        allCategoriesListCall.enqueue(new Callback<List<Coach>>() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onResponse(Call<List<Coach>> call, Response<List<Coach>> response) {

                if (!response.isSuccessful()) {
                    Log.d(LOG, "Ответ сервера: " + response.code());
                    return;
                }
                else{
                    arrayAdapter = new ArrayAdapter<>(getApplicationContext(),android.R.layout.simple_list_item_1,response.body());
                    autoCompleteTextView.setAdapter(arrayAdapter);
                }
            }

            @Override
            public void onFailure(Call<List<Coach>> call, Throwable throwable) {
                Log.d(LOG, throwable.getMessage());
            }
        });
    }

    private void lesson_fac_dep(String faculty, String department){
        JsonObject lesson = new JsonObject();
        try {
            JSONObject jsonObj_ = new JSONObject();
            jsonObj_.put("faculty",faculty);
            jsonObj_.put("department",department);
            JsonParser jsonParser = new JsonParser();
            lesson = (JsonObject) jsonParser.parse(jsonObj_.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        Call<List<StudentInfo>> call = mAPIService.getStudentInfo(MainActivity.getToken(), lesson);
        call.enqueue(new Callback<List<StudentInfo>>() {
            @Override
            public void onResponse(@NonNull Call<List<StudentInfo>> call, @NonNull Response<List<StudentInfo>> response) {
                if (response.isSuccessful()) {
                    List<StudentInfo> message = response.body();
                    list = new ArrayList<>();
                    student_fio = new ArrayList<>();
                    for(StudentInfo example : message){
                        list.add(example.getStudentId());
                        setList(list);
                        student_fio.add(example.getStudentName() + " " + example.getStudentSurname());
                        setStudent_fio(student_fio);
                    }
                    Intent registerIntent1 = new Intent(Admin_reg_lessonActivity.this, Admin_lesson_student_registerActivity.class);

                    setKod_lesson(kod_lesson);
                    setWeeks_(weeks_);
                    setFac(fac);
                    setDep(dep);
                    setTeacher_id(1);
                    setSt(st);
                    startActivity(registerIntent1);

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
            public void onFailure(@NonNull Call<List<StudentInfo>> call, @NonNull Throwable t) {
                if (t.getMessage() != null) {
                    System.out.println("Error occurred");
                    Log.d("Error", t.getMessage());
                }
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long id) {
        fac = spin_fac.getSelectedItem().toString();
        dep = spin_dep.getSelectedItem().toString();
        ((TextView) arg0.getChildAt(0)).setTextColor(Color.WHITE);
    }

    @Override
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    }


    public static Boolean getSt() {
        return st;
    }

    public static void setSt(Boolean st) {
        Admin_reg_lessonActivity.st = st;
    }

    public static Integer getTeacher_id() {
        return teacher_id;
    }

    public static void setTeacher_id(Integer teacher_id) {
        Admin_reg_lessonActivity.teacher_id = teacher_id;
    }

    public static String getKod_lesson() {
        return kod_lesson;
    }

    public static void setKod_lesson(String kod_lesson) {
        Admin_reg_lessonActivity.kod_lesson = kod_lesson;
    }

    public static String getFac() {
        return fac;
    }

    public static void setFac(String fac) {
        Admin_reg_lessonActivity.fac = fac;
    }

    public static String getDep() {
        return dep;
    }

    public static void setDep(String dep) {
        Admin_reg_lessonActivity.dep = dep;
    }

    public static Integer getWeeks_() {
        return weeks_;
    }

    public static void setWeeks_(Integer weeks_) {
        Admin_reg_lessonActivity.weeks_ = weeks_;
    }

    public static List getList(){
        return list;
    }
    public static void setList(List list){
        Admin_reg_lessonActivity.list = list;
    }

    public static List<String> getStudent_fio() {
        return student_fio;
    }

    public static void setStudent_fio(List<String> student_fio) {
        Admin_reg_lessonActivity.student_fio = student_fio;
    }
}
