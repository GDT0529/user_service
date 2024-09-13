package com.employee.service;

import com.employee.dto.UserDTO;
import com.employee.entity.User;
import org.springframework.stereotype.Component;

@Component
public class Converter {

    public User coverToEntity(UserDTO userDTO) {
        User user = new User();
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setUsername(userDTO.getUsername());
        user.setPassword(userDTO.getPassword());
        user.setEmail(userDTO.getEmail());
        user.setRole(userDTO.getRole());
        user.setUserId(userDTO.getUserId());
        return user;
    }

    public UserDTO coverToDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setFirstName(user.getFirstName());
        userDTO.setLastName(user.getLastName());
        userDTO.setUsername(user.getUsername());
        userDTO.setPassword(user.getPassword());
        userDTO.setEmail(user.getEmail());
        userDTO.setRole(user.getRole());
        userDTO.setUserId(user.getUserId());
        return userDTO;
    }
}
