/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.marlise.matriculas.repository;

import com.marlise.matriculas.models.Attendant;
import com.marlise.matriculas.models.Student;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Jorge Marles
 */
@Repository
public interface StudentRepository extends JpaRepository<Student, Long>{
    Optional<Student> findByDoc(String doc);
    
    Optional<Student> findById(Long id);
    
    List<Student> findByFirstNameLikeOrLastNameLike(String firstName, String secondName);
    
    List<Student> findBySpecialConditionsIsNotNull();
    
    List<Student> findByCreationDateBetween(Date date1, Date date2);
    
    List<Student> findByAttendant(Attendant attendant);
}
