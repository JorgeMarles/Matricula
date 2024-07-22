/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.marlise.matriculas.repository;

import com.marlise.matriculas.models.Course;
import com.marlise.matriculas.models.Grade;
import com.marlise.matriculas.models.Student;
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
public interface CourseRepository extends JpaRepository<Course, Long>{
    @Query("SELECT c FROM Course c WHERE c.name LIKE :name")
    Optional<Course> findByName(@Param("name") String name);
    
    List<Course> findByGrade(Grade grade);
    
    List<Student> findByGrade_Number(int number);
}
