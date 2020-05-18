package com.example.loginactivation;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import com.example.loginactivation.service.ApiService;
import com.example.loginactivation.service.ApiUtils;
import com.example.loginactivation.service.model.Authentication;
import com.example.loginactivation.service.model.Login;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    EditText mText_username;
    EditText mText_password;
    Button mButton_login;
    RadioButton mRadio_button;
    TextView mTextView_forgot_password;
    private String LOG = "salam";
    private ApiService mAPIService;
    private static String token;
    private static String  status;
    private static Integer id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mText_username = findViewById(R.id.editText_username);
        mText_password = findViewById(R.id.editText_password);
        mButton_login = findViewById(R.id.button_login);
        mRadio_button = findViewById(R.id.radioButton);
        mTextView_forgot_password = findViewById(R.id.textView_forgot_password);

        mAPIService = ApiUtils.getAPIService();

        mTextView_forgot_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent registerIntent1 = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(registerIntent1);
            }
        });


        mButton_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = mText_username.getText().toString().trim();
                String password = mText_password.getText().toString().trim();

                login(username, password);
            }

        });

    }
    private void login(String username, String password) {

        Login login = new Login(username, password);
        Call<Authentication> allCategoriesListCall = mAPIService.login(login);

        allCategoriesListCall.enqueue(new Callback<Authentication>() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onResponse(Call<Authentication> call, Response<Authentication> response) {
                if (response.isSuccessful()) {
                    Authentication categoryDTOS = response.body();
                    token = response.body().getToken();
                    status = String.valueOf(categoryDTOS.getStatus());
                   // id = Integer.valueOf(categoryDTOS.getId());
                   // setId(id);
                    setStatus(status);
                    Log.d("token", token);
                    Log.d("status", status);

                    switch (status){
                        case "admin":
                            Intent registerIntent1 = new Intent(MainActivity.this, Admin_1Activity.class);
                            startActivity(registerIntent1);
                            break;
                        case "coach":
                            Intent registerIntent2 = new Intent(MainActivity.this, User_page_1.class);
                            startActivity(registerIntent2);
                            break;
                        case "student":
                            Intent registerIntent = new Intent(MainActivity.this, Student_page_1Activity.class);
                            startActivity(registerIntent);
                            break;
                        default:
                            break;
                    }

                }
                else{
                    Log.d(LOG, "Ответ сервера: " + response.code());
                }

                String st = status;
//                Log.d("status", st);

            }

            @Override
            public void onFailure(Call<Authentication> call, Throwable throwable) {
                Log.d(LOG, throwable.getMessage());
            }
        });

    }

    public static String getToken(){
        return token;
    }
    public static void setToken(String token) {
        MainActivity.token = token;
    }

    public static String getStatus(){
        return status;
    }
    public static void setStatus(String status) {
        MainActivity.status = status;
    }

    public static Integer getId() {
        return id;
    }

    public static void setId(Integer id) {
        MainActivity.id = id;
    }
}
