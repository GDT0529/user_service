package com.employee.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private String username;
    private String firstName;
    private String lastName;
    private String password;
    private String email;
    private String role;
    private Integer salary;
}
