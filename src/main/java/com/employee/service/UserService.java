package com.employee.service;

import com.employee.dto.UserDTO;

public interface UserService {
    void addUser(UserDTO userDTO);
//    UserDTO login(String name, String password);
//    String login(String username, String password);
    String generateToken(String username, String password);
    void deleteUser(String username);
    void updateUser(String username, UserDTO userDTO);
    UserDTO getUser(String username);
    UserDTO getUserByNameAndPasswords(String username, String password);
}
