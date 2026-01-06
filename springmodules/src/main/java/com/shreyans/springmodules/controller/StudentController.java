package com.shreyans.springmodules.controller;

import com.shreyans.springmodules.entity.RegistrationDetails;
import com.shreyans.springmodules.entity.Student;
import com.shreyans.springmodules.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping(path = "/fetch")
    public List<Student> getAllStudent(){
        return studentService.getAllStudent();
    }

    @PostMapping(path = "/save")
    public Student saveStudent(@RequestBody Student studentDetails){
         return studentService.saveStudent(studentDetails);
    }

    @PostMapping(value = "/registration/Update")
    public RegistrationDetails updateRegistrationDetails(@RequestParam(value = "registrationDetails")RegistrationDetails registrationDetails){
          return studentService.updateRegistrationDetails(registrationDetails);
    }

}
