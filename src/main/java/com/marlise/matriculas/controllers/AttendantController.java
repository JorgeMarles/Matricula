package com.marlise.matriculas.controllers;

import com.marlise.matriculas.LangManager;
import com.marlise.matriculas.models.Attendant;
import com.marlise.matriculas.repository.AttendantRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Jorge Marles
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/tuition/attendant")
public class AttendantController {
    @Autowired
    AttendantRepository attendantRepository;
    
    @GetMapping("/all")
    public ResponseEntity<List<Attendant>> getAttendants(){
        List<Attendant> attendants = attendantRepository.findAll();
        return ResponseEntity.ok(attendants);
    }
    
    @PostMapping("/add")
    public ResponseEntity createAttendant(@RequestBody Attendant attendant){
        if(attendantRepository.findByDoc(attendant.getDoc()).isPresent()){
            return ResponseEntity.badRequest().body(LangManager.getString("error_attendant_already_created"));
        }

        try{
            Attendant attendantReturned = attendantRepository.save(attendant);
            return ResponseEntity.ok(attendantReturned);
        }catch(Exception e){
            return ResponseEntity.internalServerError().body(String.format(LangManager.getString("error_unknown"), e.getLocalizedMessage()));
        }
    }
}
