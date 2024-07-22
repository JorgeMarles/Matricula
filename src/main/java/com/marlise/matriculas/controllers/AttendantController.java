package com.marlise.matriculas.controllers;

import com.marlise.matriculas.LangManager;
import com.marlise.matriculas.models.Attendant;
import com.marlise.matriculas.repository.AttendantRepository;
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
    public ResponseEntity getAttendants(){
        try{
            List<Attendant> attendants = attendantRepository.findAll();
            return ResponseEntity.ok(attendants);
        }catch(Exception e){
            return ResponseEntity.internalServerError().body(String.format(LangManager.getString("error_unknown"), e.getLocalizedMessage()));
        }  
    }
    
    @GetMapping("/search")
    public ResponseEntity getAttendantByName(@RequestParam("q") Optional<String> nameOpt){
        String name = "";
        if(nameOpt.isPresent()){
            name = nameOpt.get();
        }
        try{
            List<Attendant> attendants = attendantRepository.findByName(name);
            return ResponseEntity.ok(attendants);
        }catch(Exception e){
            return ResponseEntity.internalServerError().body(String.format(LangManager.getString("error_unknown"), e.getLocalizedMessage()));
        }
    }
    
    @GetMapping("/{doc}")
    public ResponseEntity getAttendant(@PathVariable(value = "doc") String doc){
        try{
            Optional<Attendant> attendantReturned = attendantRepository.findByDoc(doc);
            if(attendantReturned.isEmpty()){
                return ResponseEntity.badRequest().body(LangManager.getString("error_attendant_not_found"));
            }else{
                return ResponseEntity.ok(attendantReturned.get());
            }
        }catch(Exception e){
            return ResponseEntity.internalServerError().body(String.format(LangManager.getString("error_unknown"), e.getLocalizedMessage()));
        }
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
    
    @PutMapping("/update")
    public ResponseEntity updateAttendant(@RequestBody Attendant attendant){
        Optional<Attendant> attendantOpt = attendantRepository.findByDoc(attendant.getDoc());
        if(attendantOpt.isEmpty()){
            return ResponseEntity.badRequest().body(LangManager.getString("error_attendant_not_found"));
        }

        try{
            Attendant attToUpdate = attendantOpt.get();
            attToUpdate.setDocType(attendant.getDocType());
            attToUpdate.setEmail(attendant.getEmail());
            attToUpdate.setFirstName(attendant.getFirstName());
            attToUpdate.setLastName(attendant.getLastName());
            attToUpdate.setHomeAddress(attendant.getHomeAddress());
            attToUpdate.setHomePhoneNumber(attendant.getHomePhoneNumber());
            attToUpdate.setMobileNumber(attendant.getMobileNumber());
            attToUpdate.setRelationship(attendant.getRelationship());
            attToUpdate.setWork(attendant.getWork());
            attToUpdate.setWorkAddress(attendant.getWorkAddress());
            attToUpdate.setWorkPhoneNumber(attendant.getWorkPhoneNumber());
            attendantRepository.save(attToUpdate);
           return ResponseEntity.ok(attToUpdate);
        }catch(Exception e){
            return ResponseEntity.internalServerError().body(String.format(LangManager.getString("error_unknown"), e.getLocalizedMessage()));
        }
    }
    
    @DeleteMapping("/delete")
    public ResponseEntity deleteAttendant(@RequestBody Attendant attendant){
        Optional<Attendant> attendantOpt = attendantRepository.findByDoc(attendant.getDoc());
        if(attendantOpt.isEmpty()){
            return ResponseEntity.badRequest().body(LangManager.getString("error_attendant_not_found"));
        }
        
        try{
            Attendant attToDelete = attendantOpt.get();
            attendantRepository.delete(attToDelete);
            return ResponseEntity.ok(attToDelete);
        }catch(Exception e){
            return ResponseEntity.internalServerError().body(String.format(LangManager.getString("error_unknown"), e.getLocalizedMessage()));
        }
    }
}
