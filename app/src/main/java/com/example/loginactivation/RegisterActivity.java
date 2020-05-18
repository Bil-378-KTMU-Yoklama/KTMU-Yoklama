package com.example.loginactivation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import com.example.loginactivation.service.ApiService;
import com.example.loginactivation.service.ApiUtils;
import com.example.loginactivation.service.model.UserSave;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {

    EditText rText_username_r;
    EditText rText_password_r;
    EditText rText_confirm_password_r;
    Button rButton_save_r;

    String token;

    private ApiService mAPIService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mAPIService = ApiUtils.getAPIService();

        rText_username_r = (EditText)findViewById(R.id.editText_username_r);
        rText_password_r = (EditText)findViewById(R.id.editText_password_r);
        rText_confirm_password_r = (EditText)findViewById(R.id.editText_confirm_password_r);
        rButton_save_r = (Button)findViewById(R.id.button_save_r);

        rButton_save_r.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = rText_username_r.getText().toString().trim();
                String password = rText_password_r.getText().toString().trim();
                String status = rText_confirm_password_r.getText().toString().trim();
                userSave(username, password, status);
            }
        });
    }

    private void userSave(String username, String password, String status) {
        UserSave userSave = new UserSave(username, password, status);

        Call<UserSave> postCall = mAPIService.userSave(userSave);

        postCall.enqueue(new Callback<UserSave>() {
            @Override
            public void onResponse(Call<UserSave> call, Response<UserSave> response) {
                if (response.isSuccessful()) {
                    Log.d("save", "Ответ сервера: " + response.code());

                }
                else {
                    Log.d("Save", "ishtegen jok");
                }
            }

            @Override
            public void onFailure(Call<UserSave> call, Throwable t) {
                Log.d("log", t.getMessage());
            }
        });

    }
}
