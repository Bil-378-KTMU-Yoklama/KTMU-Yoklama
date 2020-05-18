package com.example.loginactivation;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.loginactivation.service.ApiService;
import com.example.loginactivation.service.ApiUtils;
import com.example.loginactivation.service.model.Coach;
import com.example.loginactivation.service.model.LessonList;
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

public class Admin_lesson_spisokActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {


 /*   String[] faculty = {"None", "Engineering", "Economics and Management", "Sciences", "Theology", "Veterinary Medicine", "Tourism and Hotel Management", "Communication", "Humanities", "Fine Arts", "Agriculture", "Agriculture"};
    String[] department = {"None", "Computer", "Chemical Engineering", "Environmental Engineering", "Food", "Industrial Department", ""};

    private ApiService mAPIService;
    Spinner spin_fac, spin_dep;

    ArrayAdapter aa, a;
    SearchView searchView;
    ListView listView;
    ArrayList<String> arrayList;
    ArrayAdapter<String> arrayAdapter;

    String fac, dep;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_lesson_spisok);

        mAPIService = ApiUtils.getAPIService();

        searchView = findViewById(R.id.search);
        listView = findViewById(R.id.list_student);
        spin_fac = findViewById(R.id.fac_sp);
        spin_fac.setOnItemSelectedListener((AdapterView.OnItemSelectedListener) this);


        spin_dep = findViewById(R.id.dep_sp);
        spin_dep.setOnItemSelectedListener((AdapterView.OnItemSelectedListener) this);


        aa = new ArrayAdapter(this, android.R.layout.simple_spinner_item, faculty);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin_fac.setAdapter(aa);


        a = new ArrayAdapter(this, android.R.layout.simple_spinner_item, department);
        a.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin_dep.setAdapter(a);


        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                arrayAdapter.getFilter().filter(newText);
                return false;
            }
        });
    }

    private void search(String fac, String dep) {
        JsonObject lesson = new JsonObject();
        try {
            JSONObject jsonObj_ = new JSONObject();
            jsonObj_.put("faculty", fac);
            jsonObj_.put("department", dep);
            JsonParser jsonParser = new JsonParser();
            lesson = (JsonObject) jsonParser.parse(jsonObj_.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }


        System.out.println(MainActivity.getToken());

        Call<List<LessonList>> call = mAPIService.exampleGet3(MainActivity.getToken(),lesson);
        call.enqueue(new Callback<List<LessonList>>() {
            @Override
            public void onResponse(@NonNull Call<List<LessonList>> call, @NonNull Response<List<LessonList>> response) {
                //  progressBar.setVisibility(View.GONE);
                if (response.isSuccessful()) {
                    List<LessonList> message = response.body();
                    arrayList = new ArrayList<>();

                    for (LessonList example : message) {
                        Log.d("code: ", example.getLessonKod());
                        Log.d("faculty: ", example.getLessonFaculty());
                        Log.d("department: ", example.getLessonDepartment());
                        arrayList.add(example.getLessonKod());
                        Log.d("coach", String.valueOf(arrayList));
                    }
                    arrayAdapter = new ArrayAdapter<String>
                            (getApplicationContext(), android.R.layout.simple_list_item_1, arrayList.toArray(new String[0])) {

                        @Override
                        public View getView(int position, View convertView, ViewGroup parent) {
                            View view = super.getView(position, convertView, parent);

                            TextView textView = (TextView) view.findViewById(android.R.id.text1);

                            /*YOUR CHOICE OF COLOR*/
                         /*   textView.setTextColor(Color.WHITE);

                            return view;
                        }
                    };
                    listView.setAdapter(arrayAdapter);

                } else {
                    System.out.println(response.body());
                    try {
                        System.out.println(response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    // error response, no access to resource?
                    System.out.println("no access to resource");
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<LessonList>> call, @NonNull Throwable t) {
                //progressBar.setVisibility(View.GONE);
                // something went completely south (like no internet connection)
                if (t.getMessage() != null) {
                    System.out.println("Error occurred");
                    Log.d("Error", t.getMessage());
                }
            }
        });
    }

    //Performing action onItemSelected and onNothing selected
    @Override
    public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long id) {
        fac = spin_fac.getSelectedItem().toString();
        dep = spin_dep.getSelectedItem().toString();

        search(fac, dep);
        Toast.makeText(getApplicationContext(), faculty[position], Toast.LENGTH_LONG).show();
        Toast.makeText(getApplicationContext(), department[position], Toast.LENGTH_LONG).show();
    }


    @Override
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    }

   */
 private ApiService mAPIService;

    SearchView searchView;
    ListView listView;
    private String LOG = "LOG";

    ArrayAdapter<String> arrayAdapter;
    ArrayList<String> arrayList;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_lesson_spisok);

        mAPIService = ApiUtils.getAPIService();

        searchView = findViewById(R.id.search);
        listView = findViewById(R.id.list_teachers);

        Call<List<LessonList>> call = mAPIService.exampleGet3(MainActivity.getToken());
        call.enqueue(new Callback<List<LessonList>>() {
            @Override
            public void onResponse(@NonNull Call<List<LessonList>> call, @NonNull Response<List<LessonList>> response) {
                //  progressBar.setVisibility(View.GONE);
                if (response.isSuccessful()) {
                    List<LessonList> message = response.body();
                    arrayList = new ArrayList<>();

                    for (LessonList example : message) {
                        Log.d("code: ", example.getLessonKod());
                        Log.d("faculty: ", example.getLessonFaculty());
                        Log.d("department: ", example.getLessonDepartment());
                        arrayList.add(example.getLessonKod());
                        Log.d("coach", String.valueOf(arrayList));
                    }
                    arrayAdapter = new ArrayAdapter<String>
                            (getApplicationContext(), android.R.layout.simple_list_item_1, arrayList.toArray(new String[0])) {

                        @Override
                        public View getView(int position, View convertView, ViewGroup parent) {
                            View view = super.getView(position, convertView, parent);

                            TextView textView = (TextView) view.findViewById(android.R.id.text1);

                            textView.setTextColor(Color.WHITE);

                            return view;
                        }
                    };
                    listView.setAdapter(arrayAdapter);

                } else {
                    System.out.println(response.body());
                    try {
                        System.out.println(response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    // error response, no access to resource?
                    System.out.println("no access to resource");
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<LessonList>> call, @NonNull Throwable t) {
                //progressBar.setVisibility(View.GONE);
                // something went completely south (like no internet connection)
                if (t.getMessage() != null) {
                    System.out.println("Error occurred");
                    Log.d("Error", t.getMessage());
                }
            }
        });

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                arrayAdapter.getFilter().filter(newText);
                return false;
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}