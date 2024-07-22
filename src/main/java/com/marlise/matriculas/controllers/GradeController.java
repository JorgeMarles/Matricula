/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.marlise.matriculas.controllers;

import com.marlise.matriculas.LangManager;
import com.marlise.matriculas.models.Grade;
import com.marlise.matriculas.models.Student;
import com.marlise.matriculas.repository.GradeRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Jorge Marles
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/tuition/grade")
public class GradeController {
    @Autowired
    GradeRepository gradeRepository;
    
    @GetMapping("/all")
    public ResponseEntity getAllGrades(){
        try{
            List<Grade> grades = gradeRepository.findAll();
            return ResponseEntity.ok(grades);
        }catch(Exception e){
            return ResponseEntity.internalServerError().body(String.format(LangManager.getString("error_unknown"), e.getLocalizedMessage()));
        }  
    }
    
    @GetMapping("/{number}")
    public ResponseEntity getGradeByNumber(@PathVariable(value = "number") int number){
        try{
            Optional<Grade> gradeReturned = gradeRepository.findByNumber(number);
            if(gradeReturned.isEmpty()){
                return ResponseEntity.badRequest().body(LangManager.getString("error_student_not_found"));
            }else{
                return ResponseEntity.ok(gradeReturned.get());
            }
        }catch(Exception e){
            return ResponseEntity.internalServerError().body(String.format(LangManager.getString("error_unknown"), e.getLocalizedMessage()));
        }  
    }
    
    @GetMapping("/search")
    public ResponseEntity getGradesByName(@RequestParam("q") Optional<String> nameOpt){
        String name = "";
        if(nameOpt.isPresent()){
            name = nameOpt.get();
        }
        try{
            List<Grade> grades = gradeRepository.findByName(name);
            return ResponseEntity.ok(grades);
        }catch(Exception e){
            return ResponseEntity.internalServerError().body(String.format(LangManager.getString("error_unknown"), e.getLocalizedMessage()));
        }
    }
}
