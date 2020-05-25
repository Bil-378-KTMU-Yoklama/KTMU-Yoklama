package com.example.newTest.controllers;

import com.example.newTest.Service.StudentService;
import com.example.newTest.dto.PercentageInfo;
import com.example.newTest.entity.Student;
import com.example.newTest.dto.StudentRegister;
import com.example.newTest.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {
    @Autowired
    public StudentService studentService;
    @Autowired
    public StudentRepository studentRepository;


    @GetMapping("/list")
    public List<Student> studentList (){
        return studentRepository.findAll();
    }

    @GetMapping("/{id}")
    public Student getById(@PathVariable String id){
        Integer tempId = Integer.parseInt(id);
        return studentRepository.findById(tempId).get();
    }



}
