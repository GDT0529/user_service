package com.employee.controller;


import com.employee.dto.UserDTO;
import com.employee.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:80")
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
	UserService userService;

	@GetMapping("/healthy")
	public String healthy() {
		return "healthy";
	}

	@GetMapping("/getUser/{userId}")
	public UserDTO getUser(@PathVariable int userId) {
		return userService.getUser(userId);
	}

	@PostMapping("/addUser")
	public void addUser(@RequestBody UserDTO dto) {
		userService.addUser(dto);
	}

	@DeleteMapping("/deleteUser/{userId}")
	public void deleteUser(@PathVariable int userId) {
		userService.deleteUser(userId);
	}

//	@GetMapping("/login")
//	public Map<String, Object>  login(@RequestParam("username") String name, @RequestParam("password") String password) {
//
//		String token = null;
//		token = userService.generateToken(name, password);
//		Map<String, Object> response = new HashMap<>();
//		response.put("token", token);
//
//		UserDTO userDTO = new UserDTO();
//		userDTO = userService.getUserByNameAndPasswords(name, password);
//
//		response.put("user", userDTO);
//
//		return response;
//	}
	@GetMapping("/login")
	public ResponseEntity<Map<String, Object>> login(@RequestParam("username") String username, @RequestParam("password") String password) {

		Map<String, Object> response = new HashMap<>();

		// Generate token based on credentials
		String token = userService.generateToken(username, password);

		// Check if the token is null (invalid credentials)
		if (token == null) {
			response.put("message", "Invalid username or password");
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
		}

		// Retrieve user details
		UserDTO userDTO = userService.getUserByNameAndPasswords(username, password);

		// Check if user exists
		if (userDTO == null) {
			response.put("message", "Invalid username or password");
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
		}

		// Valid credentials, return token and user details
		response.put("token", token);
		response.put("user", userDTO);

		return ResponseEntity.ok(response);
	}

	@PutMapping("/updateUser/{userId}")
	public void updateUser(@PathVariable int userId, @RequestBody UserDTO dto) {
		userService.updateUser(userId, dto);
	}

}
