package com.smarthire.user.controller;

import java.io.IOException;



import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.smarthire.user.dto.CandidateProfileRequest;
import com.smarthire.user.dto.CandidateResponse;
import com.smarthire.user.entity.CandidateProfile;
import com.smarthire.user.service.CandidateService;

import jakarta.servlet.http.HttpServletRequest;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;



@RestController
@RequestMapping("/candidate")
public class CandidateController {

	 @Autowired
	 private CandidateService service;


	 
	 @PostMapping("/profile")
	 public CandidateProfile createProfile(
	         @RequestBody CandidateProfileRequest request,
	         HttpServletRequest httpRequest) {

	     String email = (String) httpRequest.getAttribute("email");
	     Long userId = (Long) httpRequest.getAttribute("userId");

	     return service.createProfile(email, userId, request);
	 }
	 
	// UPLOAD RESUME (MULTIPART)
	 @PostMapping("/upload-resume")
	 public CandidateProfile uploadResume(
	         @RequestParam("file") MultipartFile file,
	         HttpServletRequest request) {

	     String email = (String) request.getAttribute("email");

	     return service.uploadResumeByEmail(email, file);
	 }
	 
	 
	 @GetMapping("/resume/{fileName}")
	 public ResponseEntity<Resource> getResume(
	         @PathVariable String fileName) throws Exception {
		 
		 System.out.println("RESUME REQUESTED: " + fileName);


	     Path path = Paths.get("uploads").resolve(fileName);

	     Resource resource = new UrlResource(path.toUri());

	     return ResponseEntity.ok()
	             .contentType(MediaType.APPLICATION_PDF)
	             .body(resource);
	 }
	 
	// UPDATE PROFILE
	 
	    @PutMapping("/profile")
	    public CandidateProfile updateProfile(
	            @RequestBody CandidateProfileRequest request,
	            HttpServletRequest httpRequest) {

	        String email = (String) httpRequest.getAttribute("email");
	        return service.updateProfileByEmail(email, request);
	    }
	 
	    // GET PROFILE

	 
	    @GetMapping("/profile")
	    public CandidateProfile getProfile(HttpServletRequest request) {
	        String email = (String) request.getAttribute("email");
	        return service.findByEmail(email);
	    }
	    
	    @GetMapping("/profile/{userId}")
	    public CandidateResponse getProfileByUserId(
	            @PathVariable Long userId) {
	    	 CandidateProfile profile = service.getProfile(userId);

	    	    CandidateResponse response = new CandidateResponse();
	    	    
	    	    response.setUserId(profile.getUserId());
	    	    response.setName(profile.getName());
	    	    response.setEmail(profile.getEmail());
	    	    
	    	    response.setPhone(profile.getPhone());
	    	    response.setSkills(profile.getSkills());
	    	    response.setExperience(profile.getExperience());
	    	    response.setEducation(profile.getEducation());
	    	    response.setLocation(profile.getLocation());
	    	    
	    	    response.setResumeUrl(profile.getResumeUrl());

	    	    return response;
	    }
	    
	    @GetMapping("/email/{email}")
	    public CandidateProfile getByEmail(@PathVariable String email) {
	        return service.findByEmail(email);
	    }
	 
	 // DELETE PROFILE
	    @DeleteMapping("/profile")
	    public String deleteProfile(HttpServletRequest request) {

	        String email = (String) request.getAttribute("email");
	        service.deleteByEmail(email);

	        return "Profile deleted successfully";
	    }
	 
}
