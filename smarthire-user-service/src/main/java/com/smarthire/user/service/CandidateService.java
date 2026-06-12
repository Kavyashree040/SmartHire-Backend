package com.smarthire.user.service;

import java.io.File;
import java.io.IOException;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.smarthire.user.dto.CandidateProfileRequest;
import com.smarthire.user.entity.CandidateProfile;
import com.smarthire.user.repository.CandidateRepository;



@Service
public class CandidateService {

	 @Autowired
	 private CandidateRepository repository;
	
	 public CandidateProfile createProfile(String email,  Long userId, CandidateProfileRequest request) {


	        CandidateProfile profile = new CandidateProfile();
	        profile.setEmail(email);
	        profile.setUserId(userId);
	       
	        profile.setName(request.getName());
	        profile.setPhone(request.getPhone());
	        profile.setSkills(request.getSkills());
	        profile.setExperience(request.getExperience());
	        profile.setEducation(request.getEducation());
	        profile.setLocation(request.getLocation());
	       
	        return repository.save(profile);
	    }
	       

		    public CandidateProfile uploadResumeByEmail(String email, MultipartFile file) {

		        CandidateProfile profile = repository.findByEmail(email)
		                .orElseThrow(() -> new RuntimeException("Candidate not found"));

		    try {
		        String uploadDir = System.getProperty("user.dir") + "/uploads/";
		        
		       

		        File folder = new File(uploadDir);
		        if (!folder.exists()) {
		            folder.mkdirs();
		        }

		        // ✅ unique file name
		        String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename().replaceAll("[^a-zA-Z0-9._-]", "_");

		        file.transferTo(new File(uploadDir + fileName));

		        // ✅ save path
		        profile.setResumeUrl("uploads/" + fileName);

		        return repository.save(profile);

		    } catch (Exception e) {
		        throw new RuntimeException("File upload failed");
		    }
		}
	 
	 
		    
		    public CandidateProfile updateProfileByEmail(String email, CandidateProfileRequest request) {

		        CandidateProfile profile = repository.findByEmail(email)
		                .orElseThrow(() -> new RuntimeException("Candidate not found"));

		    if(request.getName() != null){
		        profile.setName(request.getName());
		    }

		    if(request.getSkills() != null){
		        profile.setSkills(request.getSkills());
		    }

		    if(request.getEducation() != null){
		        profile.setEducation(request.getEducation());
		    }

		    if(request.getLocation() != null){
		        profile.setLocation(request.getLocation());
		    }

		    if(request.getPhone() != null){
		        profile.setPhone(request.getPhone());
		    }

		    if(request.getExperience() != 0){
		        profile.setExperience(request.getExperience());
		    }

		    return repository.save(profile);
		}
	 
	
	 
		    
		    public CandidateProfile getProfile(Long userId) {

		        return repository.findByUserId(userId)
		                .orElseThrow(() ->
		                        new RuntimeException("Candidate not found"));
		    }
	 
	 public CandidateProfile getProfileByEmail(String email) {
		    return repository.findByEmail(email)
		            .orElseThrow(() -> new RuntimeException("Candidate not found"));
		}
	 
	 
	 

	 
	 public void deleteByEmail(String email){
		    CandidateProfile profile = repository.findByEmail(email)
		            .orElseThrow(() -> new RuntimeException("Candidate not found"));

		    repository.delete(profile);
		}
	 
	 
	 public CandidateProfile findByEmail(String email) {

		    return repository.findByEmail(email)
		            .orElseThrow(() -> new RuntimeException("Candidate not found"));
		}
}
