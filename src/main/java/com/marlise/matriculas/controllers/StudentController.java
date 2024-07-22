/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.marlise.matriculas.controllers;

import com.marlise.matriculas.LangManager;
import com.marlise.matriculas.models.Attendant;
import com.marlise.matriculas.models.Student;
import com.marlise.matriculas.repository.AttendantRepository;
import com.marlise.matriculas.repository.StudentRepository;
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
import org.springframework.web.bind.annotation.RequestParam;
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
    
    @Autowired
    AttendantRepository attendantRepository;
    
    @GetMapping("/all")
    public ResponseEntity getStudents(){
        try{
            List<Student> students = studentRepository.findAll();
            return ResponseEntity.ok(students);
        }catch(Exception e){
            return ResponseEntity.internalServerError().body(String.format(LangManager.getString("error_unknown"), e.getLocalizedMessage()));
        }  
    }
    
   
   
    
    @GetMapping("/list/{attendantDoc}")
    public ResponseEntity getStudentsByAttendantDoc(@PathVariable(value = "attendantDoc") String attendantDoc){
        try{
            List<Student> students = studentRepository.findByAttendant_Doc(attendantDoc);
            return ResponseEntity.ok(students);
        }catch(Exception e){
            return ResponseEntity.internalServerError().body(String.format(LangManager.getString("error_unknown"), e.getLocalizedMessage()));
        }  
    }
    

    
    @GetMapping("/search")
    public ResponseEntity getStudentByName(@RequestParam("q") Optional<String> nameOpt){
        String name = "";
        if(nameOpt.isPresent()){
            name = nameOpt.get();
        }
        try{
            List<Student> students = studentRepository.findByName(name);
            return ResponseEntity.ok(students);
        }catch(Exception e){
            return ResponseEntity.internalServerError().body(String.format(LangManager.getString("error_unknown"), e.getLocalizedMessage()));
        }
    }
    
    @GetMapping("/{doc}")
    public ResponseEntity getStudent(@PathVariable(value = "doc") String doc){
        try{
            Optional<Student> studentReturned = studentRepository.findByDoc(doc);
            if(studentReturned.isEmpty()){
                return ResponseEntity.badRequest().body(LangManager.getString("error_student_not_found"));
            }else{
                return ResponseEntity.ok(studentReturned.get());
            }
        }catch(Exception e){
            return ResponseEntity.internalServerError().body(String.format(LangManager.getString("error_unknown"), e.getLocalizedMessage()));
        }
    }
    
    @PostMapping("/add")
    public ResponseEntity createStudent(@RequestBody Student student){
        if(studentRepository.findByDoc(student.getDoc()).isPresent()){
            return ResponseEntity.badRequest().body(LangManager.getString("error_student_already_created"));
        }

        try{
            Student studentReturned = studentRepository.save(student);
            return ResponseEntity.ok(studentReturned);
        }catch(Exception e){
            return ResponseEntity.internalServerError().body(String.format(LangManager.getString("error_unknown"), e.getLocalizedMessage()));
        }
    }
    
    @PutMapping("/update")
    public ResponseEntity updateAttendant(@RequestBody Student student){
        Optional<Student> studentOpt = studentRepository.findByDoc(student.getDoc());
        if(studentOpt.isEmpty()){
            return ResponseEntity.badRequest().body(LangManager.getString("error_student_not_found"));
        }

        try{
            Student studentToUpdate = studentOpt.get();
            studentToUpdate.setDocType(student.getDocType());
            studentToUpdate.setFirstName(student.getFirstName());
            studentToUpdate.setLastName(student.getLastName());
            studentToUpdate.setAddress(student.getAddress());
            studentToUpdate.setSpecialConditions(student.getSpecialConditions());
            
            Optional<Attendant> attendantOpt = attendantRepository.findByDoc(student.getAttendant().getDoc());
            if(attendantOpt.isEmpty()){
                return ResponseEntity.badRequest().body(LangManager.getString("error_attendant_not_found"));
            }
            
            studentToUpdate.setAttendant(attendantOpt.get());
            studentRepository.save(studentToUpdate);
           return ResponseEntity.ok(studentToUpdate);
        }catch(Exception e){
            return ResponseEntity.internalServerError().body(String.format(LangManager.getString("error_unknown"), e.getLocalizedMessage()));
        }
    }
    
    @DeleteMapping("/delete")
    public ResponseEntity deleteStudent(@RequestBody Student student){
        Optional<Student> studentOpt = studentRepository.findByDoc(student.getDoc());
        if(studentOpt.isEmpty()){
            return ResponseEntity.badRequest().body(LangManager.getString("error_student_not_found"));
        }
        
        try{
            Student studentToDelete = studentOpt.get();
            studentRepository.delete(studentToDelete);
            return ResponseEntity.ok(studentToDelete);
        }catch(Exception e){
            return ResponseEntity.internalServerError().body(String.format(LangManager.getString("error_unknown"), e.getLocalizedMessage()));
        }
    }
}
