package com.employee.repository;


import com.employee.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, Integer>{
    
    User findByUsernameAndPassword(String name, String password);

    User findByUsername(String name);
    User findByUserId(int userId);

}
