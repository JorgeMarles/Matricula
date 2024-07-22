/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.marlise.matriculas.repository;

import com.marlise.matriculas.models.Attendant;
import com.marlise.matriculas.models.Course;
import com.marlise.matriculas.models.Grade;
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
        
    @Query("SELECT s FROM Student s WHERE s.firstName LIKE CONCAT('%',:name,'%') OR s.lastName LIKE CONCAT('%',:name,'%')")
    List<Student> findByName(@Param("name") String name);
        
    List<Student> findByCreationDateBetween(Date date1, Date date2);
        
    List<Student> findByAttendant_Doc(String doc);
}
