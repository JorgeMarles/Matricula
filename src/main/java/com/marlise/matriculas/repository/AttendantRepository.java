/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.marlise.matriculas.repository;

import com.marlise.matriculas.models.Attendant;
import com.marlise.matriculas.models.Student;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Jorge Marles
 */
@Repository
public interface AttendantRepository extends JpaRepository<Attendant, Long>{
    Optional<Attendant> findByDoc(String doc);
    
    Optional<Attendant> findById(Long id);
    
    List<Attendant> findByFirstNameLikeOrLastNameLike(String firstName, String secondName);
}
