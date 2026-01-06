package com.shreyans.springmodules.service;

import com.shreyans.springmodules.entity.RegistrationDetails;
import com.shreyans.springmodules.entity.Student;
import com.shreyans.springmodules.repository.RegistrationDetailsRepository;
import com.shreyans.springmodules.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class StudentService {
    private final StudentRepository studentRepository;

    @Autowired
    private RegistrationDetailsRepository registrationDetailsRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }
    public List<Student> getAllStudent(){
        return studentRepository.findAll();
    }
    @Transactional
    public Student saveStudent(Student studentDetails){
        return studentRepository.save(studentDetails);
    }

    public RegistrationDetails updateRegistrationDetails(RegistrationDetails registrationDetails){
      return registrationDetailsRepository.save(registrationDetails);
    }



}
