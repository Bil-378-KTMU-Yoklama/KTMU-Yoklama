package com.example.loginactivation;

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
import com.example.loginactivation.service.model.MyModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Admin_teacher_spisokActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    private ApiService mAPIService;

    SearchView searchView;
    ListView listView;
    private String LOG = "LOG";

    ArrayAdapter<String> arrayAdapter;
    ArrayList<String> arrayList;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_teacher_spisok);

        mAPIService = ApiUtils.getAPIService();

        searchView = findViewById(R.id.search);
        listView = findViewById(R.id.list_teachers);

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
                    arrayList = new ArrayList<String>();
                    List<Coach> coaches = response.body();

                    for (Coach coach : coaches){
                        arrayList.add(coach.getCoachName() + " " + coach.getCoachSurname());
                        Log.d("coach", String.valueOf(arrayList));
                    }
                    arrayAdapter = new ArrayAdapter<String>
                            (getApplicationContext(),android.R.layout.simple_list_item_1,arrayList.toArray(new String[0])){

                        @Override
                        public View getView(int position, View convertView, ViewGroup parent) {
                            View view =super.getView(position, convertView, parent);

                            TextView textView=(TextView) view.findViewById(android.R.id.text1);

                            /*YOUR CHOICE OF COLOR*/
                            textView.setTextColor(Color.WHITE);

                            return view;
                        }
                    };
                    listView.setAdapter(arrayAdapter);

                }
            }


            @Override
            public void onFailure(Call<List<Coach>> call, Throwable throwable) {
                Log.d(LOG, throwable.getMessage());
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
