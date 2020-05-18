package com.example.loginactivation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.loginactivation.service.ApiService;
import com.example.loginactivation.service.ApiUtils;
import com.example.loginactivation.service.model.Register2Lesson;
import com.example.loginactivation.service.model.StudentInfo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Admin_lesson_student_registerActivity extends AppCompatActivity {

    private ApiService mAPIService;

    private static String kod_lesson = Admin_reg_lessonActivity.getKod_lesson();
    private static Integer weeks = Admin_reg_lessonActivity.getWeeks_();
    private static String fac = Admin_reg_lessonActivity.getFac();
    private static String dep = Admin_reg_lessonActivity.getDep();
    private static Boolean status = Admin_reg_lessonActivity.getSt();
    private static List<Integer> students_id = new ArrayList<>(Admin_reg_lessonActivity.getList());
    private static List<String> student_fio = new ArrayList<>(Admin_reg_lessonActivity.getStudent_fio());
    private static List<Integer> students_id_;

    ArrayAdapter<Integer> arrayAdapter_id;
    ArrayAdapter<String> arrayAdapter_fio;

    ListView listView, listView1;
    Button submit;

    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_lesson_student_register);

        mAPIService = ApiUtils.getAPIService();
        textView = findViewById(R.id.textView);
        listView = findViewById(R.id.list1);
        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        listView1 = findViewById(R.id.list);
        submit = findViewById(R.id.submit);

        arrayAdapter_id = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_multiple_choice, students_id);
        listView.setAdapter(arrayAdapter_id);

        arrayAdapter_fio = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, student_fio);
        listView1.setAdapter(arrayAdapter_fio);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {

                textView.setText("");
                int cntChoice = listView.getCount();
                students_id_ = new ArrayList<>();
                String unchecked = "";
                SparseBooleanArray sparseBooleanArray = listView
                        .getCheckedItemPositions();

                for (int i = 0; i < cntChoice; i++) {

                    if (sparseBooleanArray.get(i) == true) {
                        students_id_ .add(Integer.valueOf(listView.getItemAtPosition(i).toString()));
                    } else if (sparseBooleanArray.get(i) == false) {
                        unchecked += listView.getItemAtPosition(i).toString()
                                + "\n";
                        // выводим список невыбранных элементов
                        textView.setText(unchecked);
                    }
                }
                Log.d("student", String.valueOf(getStudents_id_()));
            }
        });


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lesson_register(Admin_reg_lessonActivity.getKod_lesson(), Admin_reg_lessonActivity.getWeeks_(), Admin_reg_lessonActivity.getFac(), Admin_reg_lessonActivity.getDep(), Admin_reg_lessonActivity.coach_id, Admin_reg_lessonActivity.getSt(), getStudents_id_());
            }
        });
        }

    private void lesson_register(String kod_lesson, Integer weeks, String fac, String dep, Integer teacher, Boolean status_, List<Integer> students) {
        Log.d("", String.valueOf(students));
        Log.d("", String.valueOf(kod_lesson));
        Log.d("", String.valueOf(weeks));
        Log.d("", String.valueOf(status_));
        Log.d("", String.valueOf(teacher));


        Register2Lesson post = new Register2Lesson(kod_lesson, dep, fac, weeks, status_, teacher, students);
        Call<Register2Lesson> postCall = mAPIService.examplePost2(MainActivity.getToken(), post);
        postCall.enqueue(new Callback<Register2Lesson>() {
            @Override
            public void onResponse(Call<Register2Lesson> call, Response<Register2Lesson> response) {
                if(!response.isSuccessful()){
                    Log.d("LOG1", "Ответ сервера: " + response.code());
                    return;
                }
                else{
                    Register2Lesson post1 = response.body();
                }

            }

            @Override
            public void onFailure(Call<Register2Lesson> call, Throwable t) {
                Log.d("Log",t.getMessage());
            }
        });
    }

    public static List<Integer> getStudents_id_() {
        return students_id_;
    }

    public static void setStudents_id_(List<Integer> students_id_) {
        Admin_lesson_student_registerActivity.students_id_ = students_id_;
    }
}
