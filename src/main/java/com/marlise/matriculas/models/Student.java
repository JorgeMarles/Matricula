/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.marlise.matriculas.models;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotBlank;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 *
 * @author Jorge Marles
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "student",
        uniqueConstraints = {
            @UniqueConstraint(columnNames = "doc")
        })
public class Student {
   
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank
    @Column(unique = true)
    private String doc;
    
    @NotBlank
    private String firstName;
    
    @NotBlank
    private String lastName;
    
    @NotBlank
    private String address;
    
    @NotBlank
    private String specialConditions;
    
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "doctype_id", nullable = false)
    private DocType docType;
    
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "attendant_id", nullable = false)
    private Attendant attendant;
    
    @OneToOne(mappedBy = "student", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private Tuition tuition;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate;
    
}
