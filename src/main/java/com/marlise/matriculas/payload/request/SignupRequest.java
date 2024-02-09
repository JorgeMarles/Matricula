/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.marlise.matriculas.payload.request;

/**
 *
 * @author Jorge Marles
 */
import java.util.Set;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
 
public class SignupRequest {
    @NotBlank
    @Size(min = 3, max = 20)
    @Getter
    @Setter
    private String doc;
 
    @NotBlank
    @Getter
    @Setter
    private String name;
    
    @Getter
    @Setter
    private Set<String> role;
    
    @NotBlank
    @Size(min = 6, max = 40)
    @Getter
    @Setter
    private String password;
  

}
