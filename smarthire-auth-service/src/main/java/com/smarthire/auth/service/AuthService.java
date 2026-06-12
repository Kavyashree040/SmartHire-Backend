package com.smarthire.auth.service;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.smarthire.auth.dto.AuthResponse;
import com.smarthire.auth.dto.LoginRequest;
import com.smarthire.auth.dto.RegisterRequest;
import com.smarthire.auth.entity.User;
import com.smarthire.auth.repository.UserRepository;
import com.smarthire.auth.security.JwtUtil;

@Service
public class AuthService {

	 @Autowired
	    private UserRepository userRepository;
	 
	 

	    public User registerUser(RegisterRequest request) {
	    	 User user = new User(); 
	    	 
	    	user.setName(request.getName());
	        user.setEmail(request.getEmail());

	        user.setPassword(passwordEncoder.encode(request.getPassword()));


	        user.setRole(request.getRole());
	        
	        return userRepository.save(user);
	    }
	    
	    @Autowired
	    private JwtUtil jwtUtil;
	    
	    @Autowired
	    private PasswordEncoder passwordEncoder;
	    
	    public AuthResponse login(LoginRequest request) {

	        User user = userRepository.findByEmail(request.getEmail());

	        if (user != null &&
	            passwordEncoder.matches(
	                    request.getPassword(),
	                    user.getPassword())) {

	            String token = jwtUtil.generateToken(user);

	            return new AuthResponse(
	                    token,
	                    user.getRole()
	            );
	        }

	        throw new RuntimeException("Invalid Credentials");
	    }
	    
	    
	   
	    
	   
}
