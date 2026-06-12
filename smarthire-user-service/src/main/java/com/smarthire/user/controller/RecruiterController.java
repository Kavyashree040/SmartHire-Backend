package com.smarthire.user.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.smarthire.user.entity.RecruiterProfile;
import com.smarthire.user.service.RecruiterService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/recruiter")
public class RecruiterController {

	@Autowired
	 private RecruiterService service;

	 
	// ✅ CREATE PROFILE
	    @PostMapping("/profile")
	    public RecruiterProfile createProfile(
	            @RequestBody RecruiterProfile request,
	            HttpServletRequest httpRequest) {

	        String email = (String) httpRequest.getAttribute("email");
	        Long userId = (Long) httpRequest.getAttribute("userId");

	        return service.createProfile(email, userId, request);
	    }
	    

	    
	    // ✅ GET PROFILE
	    @GetMapping("/profile")
	    public RecruiterProfile getProfile(HttpServletRequest request) {

	        String email = (String) request.getAttribute("email");

	        return service.getProfileByEmail(email);
	    }
	
	    
	    // ✅ UPDATE PROFILE
	    @PutMapping("/profile")
	    public RecruiterProfile updateProfile(
	            @RequestBody RecruiterProfile request,
	            HttpServletRequest httpRequest) {

	        String email = (String) httpRequest.getAttribute("email");

	        return service.updateProfileByEmail(email, request);
	    }

//	    
	    
	    // ✅ DELETE PROFILE
	    @DeleteMapping("/profile")
	    public String deleteProfile(HttpServletRequest request) {

	        String email = (String) request.getAttribute("email");

	        service.deleteByEmail(email);

	        return "Recruiter profile deleted successfully";
	    }
	    
	   
}	