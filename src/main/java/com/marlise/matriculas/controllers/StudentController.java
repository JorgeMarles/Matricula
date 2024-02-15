/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.marlise.matriculas.controllers;

import com.marlise.matriculas.models.Attendant;
import com.marlise.matriculas.models.Student;
import com.marlise.matriculas.repository.AttendantRepository;
import com.marlise.matriculas.repository.StudentRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Jorge Marles
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/tuition/student")
public class StudentController {
    @Autowired
    StudentRepository studentRepository;
    
    @GetMapping("/all")
    public ResponseEntity<List<Student>> getStudents(){
        List<Student> students = studentRepository.findAll();
        return ResponseEntity.ok(students);
    }
}
