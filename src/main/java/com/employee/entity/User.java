package com.employee.entity;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@ToString
public class User {

	@Id
	private String username;
	private String firstName;
	private String lastName;
	private String password;
	private String email;
    private String role;
	private Integer salary;

    
}
