package com.employee.service.implement;

import com.employee.dto.UserDTO;
import com.employee.entity.CustomUserDetails;
import com.employee.entity.User;
import com.employee.helper.JwtProvider;
import com.employee.repository.UserRepository;
import com.employee.service.Converter;
import com.employee.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {
    @Autowired
	UserRepository userRepository;

	@Autowired
	Converter converter;

	@Autowired
	RestTemplate restTemplate;

	@Autowired
	JwtProvider jwtProvider;

	private static final String RESERVATION_SERVICE = "http://localhost:8081";

	@Override
	public void addUser(UserDTO userDTO) {
		User user = converter.coverToEntity(userDTO);
		userRepository.save(user);
	}
	@Override
	public void deleteUser(String username) {
		User user = userRepository.findByUsername(username);
		userRepository.delete(user);
		//restTemplate.delete(RESERVATION_SERVICE+"/users/deleteUser/{username}", username);
	}

	@Override
	public void updateUser(String username, UserDTO userDTO) {
	
		Optional<User> existingUserOpt = Optional.ofNullable(userRepository.findByUsername(username));
    
    // If the user is not found, return a "User not found" message
    if (existingUserOpt.isPresent()) {

		User existingUser = existingUserOpt.get();
		existingUser.setFirstName(userDTO.getFirstName());
		existingUser.setLastName(userDTO.getLastName());
		existingUser.setUsername(userDTO.getUsername());
		existingUser.setPassword(userDTO.getPassword());
		existingUser.setEmail(userDTO.getEmail());
		existingUser.setRole(userDTO.getRole());

		userRepository.saveAndFlush(existingUser);
		//entityManager.clear(); // Clear the persistence context
	}
	restTemplate.put(RESERVATION_SERVICE+"/users/updateUser/", userDTO);
		
	}


	@Override
	public String generateToken(String username, String password){
		UserDetails userDetails = this.loadUserByUsername(username);
		if(userDetails == null){
			return null;
		}
		String token = null;
		token = jwtProvider.generateToken(userDetails);
		return token;
	}

	@Override
	public UserDTO getUser(String username) {
		Optional<User> UserOpt = Optional.ofNullable(userRepository.findByUsername(username));
		if (!UserOpt.isPresent()) {
			return null;
		}
		User user = UserOpt.get();
		return converter.coverToDTO(user);
	}

	@Override
	public UserDTO getUserByNameAndPasswords(String username, String password){
		Optional<User> existingUserOpt = Optional.ofNullable(userRepository.findByUsernameAndPassword(username, password));
		if (!existingUserOpt.isPresent()) {
			return null;
		}
		User user = existingUserOpt.get();
		UserDTO dto = converter.coverToDTO(user);
		return dto;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

//		Optional<User> existingUserOpt = Optional.ofNullable(userRepository.findByNameAndPassword(username, password));
		Optional<User> existingUserOpt = Optional.ofNullable(userRepository.findByUsername(username));
		if (!existingUserOpt.isPresent()) {
			return null;
		}
		User user = existingUserOpt.get();

		return new CustomUserDetails(user);
	}
}
