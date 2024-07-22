/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.marlise.matriculas.repository;

import com.marlise.matriculas.models.Grade;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author Jorge Marles
 */
public interface GradeRepository extends JpaRepository<Grade, Long>{
    @Query("SELECT g FROM Grade g WHERE g.name LIKE CONCAT('%',:name,'%')")
    List<Grade> findByName(@Param("name") String name);
    
    Optional<Grade> findByNumber(int number);
}
