package com.example.loginactivation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Admin_1Activity extends AppCompatActivity {

    Button reg_student, sp_student, reg_teacher, sp_teacher, reg_lesson, sp_lesson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_1);

        reg_student = (Button)findViewById(R.id.reg_student);
        sp_student = (Button)findViewById(R.id.sp_student);
        reg_teacher = (Button)findViewById(R.id.reg_teacher);
        sp_teacher = (Button)findViewById(R.id.sp_teacher);
        reg_lesson = (Button)findViewById(R.id.reg_lesson);
        sp_lesson = (Button)findViewById(R.id.sp_lesson);

        reg_student.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registerIntent_reg_student = new Intent(Admin_1Activity.this, Admin_reg_studentsActivity.class);
                startActivity(registerIntent_reg_student);
            }
        });

        reg_teacher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registerIntent_reg_teacher = new Intent(Admin_1Activity.this, Admin_reg_teacherActivity.class);
                startActivity(registerIntent_reg_teacher);
            }
        });

        reg_lesson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registerIntent_reg_lesson = new Intent(Admin_1Activity.this, Admin_reg_lessonActivity.class);
                startActivity(registerIntent_reg_lesson);
            }
        });

        sp_student.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registerIntent_sp_student = new Intent(Admin_1Activity.this, Admin_SpisokActivity.class);
                startActivity(registerIntent_sp_student);
            }
        });

        sp_teacher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registerIntent_sp_student = new Intent(Admin_1Activity.this, Admin_teacher_spisokActivity.class);
                startActivity(registerIntent_sp_student);
            }
        });

        sp_lesson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registerIntent_sp_student = new Intent(Admin_1Activity.this, Admin_lesson_spisokActivity.class);
                startActivity(registerIntent_sp_student);
            }
        });
    }
}
