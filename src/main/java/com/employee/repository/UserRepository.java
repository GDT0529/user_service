package com.employee.repository;


import com.employee.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String>{
    
    User findByUsernameAndPassword(String name, String password);
    User findByUsername(String username);

}
