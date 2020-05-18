package com.example.loginactivation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.loginactivation.service.ApiService;
import com.example.loginactivation.service.ApiUtils;
import com.example.loginactivation.service.model.MyModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Admin_reg_teacherActivity extends AppCompatActivity {
    private TextView mResponseTv;
    private ApiService mAPIService;
    private String TAG = "Tag";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_reg_teacher);

        final EditText kod = findViewById(R.id.t_kod);
        final EditText name = findViewById(R.id.t_name);
        final EditText surname = findViewById(R.id.t_surname);
        final EditText login = findViewById(R.id.t_login);
        final EditText password = findViewById(R.id.t_password);
        Button submit = findViewById(R.id.t_submit);
        mResponseTv = findViewById(R.id.tv_response);

        mAPIService = ApiUtils.getAPIService();

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String kod_t = kod.getText().toString().trim();
                String name_t = name.getText().toString().trim();
                String surname_t = surname.getText().toString().trim();
                String login_t = login.getText().toString().trim();
                String password_t = password.getText().toString().trim();
                if(!TextUtils.isEmpty(kod_t) && !TextUtils.isEmpty(name_t) && !TextUtils.isEmpty(surname_t) && !TextUtils.isEmpty(login_t) && !TextUtils.isEmpty(password_t)) {
                    Log.d("keldi",kod_t);
                    sendPost(kod_t, name_t, surname_t, login_t, password_t);
                }
            }
        });
    }

    private void sendPost(String t_kod, String t_name, String t_surname, String t_login, String t_password) {
        try {
            MyModel post = new MyModel(t_kod, t_name, t_surname, t_login, t_password);
            Call<MyModel> postCall = mAPIService.examplePost(MainActivity.getToken(), post);
            postCall.enqueue(new Callback<MyModel>() {
                @Override
                public void onResponse(Call<MyModel> call, Response<MyModel> response) {
                    if (!response.isSuccessful()) {
                        Log.d("LOG", "Ответ сервера: " + response.code());
                        return;
                    } else {
                        MyModel post1 = response.body();
                        String c = "";
                        c += "kod: " + post1.getKod() + "\n";
                        c += "name: " + post1.getName();
                        c += "surname: " + post1.getSurname();
                        c += "login: " + post1.getLogin();
                        c += "password: " + post1.getPassword();

                        Log.d("c ", c);

                        Log.i(TAG, "post submitted to API." + response.body().toString());
                        Log.i(TAG, "post submitted to API." + response.body().toString());
                    }

                }

                @Override
                public void onFailure(Call<MyModel> call, Throwable t) {
                    Log.d("Log", t.getMessage());
                }
            });
        } catch (Exception e) {
            Log.d("ReadRdaJSONFeedTask", e.getLocalizedMessage());
        }
    }
}
