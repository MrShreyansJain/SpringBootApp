package com.shreyans.springmodules.service;

import com.shreyans.springmodules.entity.Student;
import com.shreyans.springmodules.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Service
public class StudentService {
    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }
    public List<Student> getAllStudent(){
        return studentRepository.findAll();
    }
    public Student saveStudent(@RequestBody Student studentDetails){
        return studentRepository.save(studentDetails);
    }



}
