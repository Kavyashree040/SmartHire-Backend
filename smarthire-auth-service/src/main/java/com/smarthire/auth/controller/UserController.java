package com.smarthire.auth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.smarthire.auth.dto.AuthResponse;
import com.smarthire.auth.dto.LoginRequest;
import com.smarthire.auth.dto.RegisterRequest;
import com.smarthire.auth.entity.User;
import com.smarthire.auth.service.AuthService;

@RestController
@RequestMapping("/auth")
public class UserController {

	 @Autowired
	    private AuthService authService;

	    @PostMapping("/register")
	    public User registerUser(@RequestBody RegisterRequest request) {

	        return authService.registerUser(request);
	    }
	    
	    
	    @PostMapping("/login")
	    public AuthResponse login(@RequestBody LoginRequest request) {

	        return authService.login(request);

	    }
	    
	    @GetMapping("/test")
	    public String test() {
	        return "JWT is working";
	    }
}
