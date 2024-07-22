/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.marlise.matriculas.controllers;

import com.marlise.matriculas.LangManager;
import com.marlise.matriculas.models.Course;
import com.marlise.matriculas.models.Grade;
import com.marlise.matriculas.repository.CourseRepository;
import com.marlise.matriculas.repository.GradeRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Jorge Marles
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/tuition/course")
public class CourseController {
    @Autowired
    CourseRepository courseRepository;
    
    @Autowired
    GradeRepository gradeRepository;
    
    @GetMapping("/all")
    public ResponseEntity getCourses(){
        try{
            List<Course> courses = courseRepository.findAll();
            return ResponseEntity.ok(courses);
        }catch(Exception e){
            return ResponseEntity.internalServerError().body(String.format(LangManager.getString("error_unknown"), e.getLocalizedMessage()));
        }  
    }
    
    @GetMapping("/list/{gradeNumber}")
    public ResponseEntity getCoursesByGradeDoc(@PathVariable(value = "gradeNumber") int gradeNumber){
        try{
            Optional<Grade> grade = gradeRepository.findByNumber(gradeNumber);
            if(grade.isPresent()){
                List<Course> courses = courseRepository.findByGrade(grade.get());
                return ResponseEntity.ok(courses);
            }else{
                throw new RuntimeException("Grado no encontrado");
            }
        }catch(Exception e){
            return ResponseEntity.internalServerError().body(String.format(LangManager.getString("error_unknown"), e.getLocalizedMessage()));
        }  
    }
    
    @PostMapping("/add")
    public ResponseEntity createCourse(@RequestBody Course course){
        if(courseRepository.findByName(course.getName()).isPresent()){
            return ResponseEntity.badRequest().body(LangManager.getString("error_student_already_created"));
        }

        try{
            Course courseReturned = courseRepository.save(course);
            return ResponseEntity.ok(courseReturned);
        }catch(Exception e){
            return ResponseEntity.internalServerError().body(String.format(LangManager.getString("error_unknown"), e.getLocalizedMessage()));
        }
    }
    
    @PutMapping("/update")
    public ResponseEntity updateCourse(@RequestBody Course course){
        Optional<Course> courseOpt = courseRepository.findByName(course.getName());
        if(courseOpt.isEmpty()){
            return ResponseEntity.badRequest().body(LangManager.getString("error_student_not_found"));
        }

        try{
            Course courseToUpdate = courseOpt.get();
            courseToUpdate.setCapacity(course.getCapacity());
            
            
            courseRepository.save(courseToUpdate);
           return ResponseEntity.ok(courseToUpdate);
        }catch(Exception e){
            return ResponseEntity.internalServerError().body(String.format(LangManager.getString("error_unknown"), e.getLocalizedMessage()));
        }
    }
    
    @DeleteMapping("/delete")
    public ResponseEntity deleteCourse(@RequestBody Course course){
        Optional<Course> courseOpt = courseRepository.findByName(course.getName());
        if(courseOpt.isEmpty()){
            return ResponseEntity.badRequest().body(LangManager.getString("error_student_not_found"));
        }
        
        try{
            Course courseToDelete = courseOpt.get();
            courseRepository.delete(courseToDelete);
            return ResponseEntity.ok(courseToDelete);
        }catch(Exception e){
            return ResponseEntity.internalServerError().body(String.format(LangManager.getString("error_unknown"), e.getLocalizedMessage()));
        }
    }
    
}
