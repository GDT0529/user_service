package com.employee.controller;


import com.employee.dto.UserDTO;
import com.employee.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
	UserService userService;

	@GetMapping("/healthy")
	public String healthy() {
		return "healthy";
	}

	@GetMapping("/getUser/{username}")
	public UserDTO getUser(@PathVariable String username) {
		return userService.getUser(username);
	}

	@PostMapping("/addUser")
	public void addUser(@RequestBody UserDTO dto) {
		userService.addUser(dto);
	}

	@DeleteMapping("/deleteUser/{username}")
	public void deleteUser(@PathVariable String username) {
		userService.deleteUser(username);
	}

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

	@PutMapping("/updateUser/{username}")
	public void updateUser(@PathVariable String username, @RequestBody UserDTO dto) {
		userService.updateUser(username, dto);
	}

}
