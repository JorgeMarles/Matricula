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
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import java.util.Date;
import java.util.List;
import java.util.Set;
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
@Table(name = "attendant",
        uniqueConstraints = {
            @UniqueConstraint(columnNames = "doc")
        })
public class Attendant {
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
    @Email
    private String email;
    
    @NotBlank
    private String mobileNumber;
    
    @NotBlank
    private String homeAddress;
    
    private String homePhoneNumber;
    
    @NotBlank
    private String work;
    
    private String workAddress;
    
    private String workPhoneNumber;
    
    @NotBlank
    private String relationship;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate;
    
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "doctype_id", nullable = false)
    private DocType docType;
    
    @OneToMany(mappedBy = "attendant", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private Set<Student> students;
}
