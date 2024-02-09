/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.marlise.matriculas.payload.response;

/**
 *
 * @author Jorge Marles
 */
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
public class UserInfoResponse {
        @Getter
        @Setter
	private Long id;
        @Getter
        @Setter
	private String doc;
        @Getter
        @Setter
	private String name;
        @Getter
        @Setter
	private List<String> roles;

	
}
