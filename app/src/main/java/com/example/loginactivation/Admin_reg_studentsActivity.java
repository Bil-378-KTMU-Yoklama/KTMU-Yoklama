package com.example.loginactivation;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.MediaController;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.loginactivation.service.ApiService;
import com.example.loginactivation.service.ApiUtils;
import com.example.loginactivation.service.model.MyModel;

import java.security.Permission;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Admin_reg_studentsActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    private static final int IMAGE_CAPTURE_CODE = 1001;
    String[] faculty = {"None","Engineering", "Economics and Management", "Sciences", "Theology", "Veterinary Medicine", "Tourism and Hotel Management", "Communication", "Humanities", "Fine Arts", "Agriculture", "Agriculture"};
    String[] department = {"None", "Computer Engineering", "Chemical Engineering", "Environmental Engineering", "Food Engineering", "Industrial Department", ""};

    Button submit;
    ImageButton student_photo;
    EditText student_number, student_name, student_surname, student_login, student_password;
    private static final int PERMISSION_CODE = 1000;

    Uri image_uri;
    private ApiService mAPIService;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_reg_students);

        mAPIService = ApiUtils.getAPIService();

        submit = (Button) findViewById(R.id.submit_student);
        student_photo = (ImageButton) findViewById(R.id.student_photo);
        student_number = (EditText) findViewById(R.id.student_number);
        student_name = (EditText) findViewById(R.id.name_student);
        student_surname = (EditText) findViewById(R.id.student_surname);
        student_password = (EditText) findViewById(R.id.password_student);

        Spinner spin_fac = (Spinner) findViewById(R.id.fac_sp);
        spin_fac.setOnItemSelectedListener((AdapterView.OnItemSelectedListener) this);


        Spinner spin_dep = (Spinner) findViewById(R.id.dep_sp);
        spin_dep.setOnItemSelectedListener((AdapterView.OnItemSelectedListener) this);

        //Creating the ArrayAdapter instance having the country list
        ArrayAdapter aa = new ArrayAdapter(this, android.R.layout.simple_spinner_item, faculty);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        spin_fac.setAdapter(aa);


        //Creating the ArrayAdapter instance having the country list
        ArrayAdapter a = new ArrayAdapter(this, android.R.layout.simple_spinner_item, department);
        a.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        spin_dep.setAdapter(a);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String kod_t = student_number.getText().toString().trim();
                String name_t = student_name.getText().toString().trim();
                String surname_t = student_surname.getText().toString().trim();
                String password_t = student_password.getText().toString().trim();
                if(!TextUtils.isEmpty(kod_t) && !TextUtils.isEmpty(name_t) && !TextUtils.isEmpty(surname_t) && !TextUtils.isEmpty(password_t)) {
                    Log.d("keldi",kod_t);
                   // sendPost(kod_t, name_t, surname_t, password_t);
                }
            }
        });


        student_photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                    if(checkSelfPermission(Manifest.permission.CAMERA) ==
                            PackageManager.PERMISSION_DENIED ||
                            checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) ==
                                    PackageManager.PERMISSION_DENIED){
                        String[] permission = {Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE};
                        requestPermissions(permission, PERMISSION_CODE);
                    }
                    else{
                        openCamera();
                    }
                }
                else {
                    openCamera();
                }
            }
        });

    }

    private void openCamera() {
        ContentValues values = new ContentValues();
        values.put(MediaStore.Images.Media.TITLE, "New Picture");
        values.put(MediaStore.Images.Media.TITLE, "From the camera");
        image_uri = getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);

        Intent intent_Camera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent_Camera.putExtra(MediaStore.EXTRA_OUTPUT, image_uri);
        startActivityForResult(intent_Camera, IMAGE_CAPTURE_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case PERMISSION_CODE:{
                if(grantResults.length > 0 && grantResults[0] ==
                        PackageManager.PERMISSION_GRANTED){
                    openCamera();
                }
                else {
                    Toast.makeText(this, "Permission denied...", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            student_photo.setImageURI(image_uri);
        }
    }

    //Performing action onItemSelected and onNothing selected
    @Override
    public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long id) {
        Toast.makeText(getApplicationContext(),faculty[position] , Toast.LENGTH_LONG).show();
        Toast.makeText(getApplicationContext(),department[position] , Toast.LENGTH_LONG).show();
    }



    @Override
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    }


   /* private void sendPost(String t_kod, String t_name, String t_surname, String t_password) {
        try {
            MyModel post = new MyModel(t_kod, t_name, t_surname, t_password);
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

                        Log.i("TAG", "post submitted to API." + response.body().toString());
                        Log.i("TAG", "post submitted to API." + response.body().toString());
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

    */

}
