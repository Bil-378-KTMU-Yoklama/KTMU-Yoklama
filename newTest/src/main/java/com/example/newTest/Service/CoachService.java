package com.example.newTest.Service;


import com.example.newTest.dto.IdAndStatus;
import com.example.newTest.dto.LessonIdandKod;
import com.example.newTest.dto.LessonRegister;
import com.example.newTest.entity.*;
import com.example.newTest.dto.CoachRegister;
import com.example.newTest.repositories.CoachRepository;
import com.example.newTest.repositories.EnrollRepository;
import com.example.newTest.repositories.LessonRepository;
import com.example.newTest.repositories.UserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CoachService {
    @Autowired
    public CoachRepository coachRepository;
    @Autowired
    private UserInfoRepository user_infoRepository;
    @Autowired
    public BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    public LessonRepository lessonRepository;
    @Autowired
    public EnrollRepository enrollRepository;

    public void coachRegister(CoachRegister coachRegister) {
        UserInfo user_info = new UserInfo(null, coachRegister.getLogin(),bCryptPasswordEncoder.encode(coachRegister.getPassword()), "coach");
        UserInfo user_info2 = user_infoRepository.save(user_info);
        Coach coach = new Coach(null, coachRegister.getKod(), coachRegister.getName(), coachRegister.getSurname(), user_info2);
        coachRepository.save(coach);
    }



}
