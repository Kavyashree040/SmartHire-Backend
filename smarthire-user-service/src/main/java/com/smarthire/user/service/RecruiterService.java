package com.smarthire.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smarthire.user.entity.RecruiterProfile;
import com.smarthire.user.repository.RecruiterRepository;

@Service
public class RecruiterService {

	 @Autowired
	 private RecruiterRepository repository;
		 
		 public RecruiterProfile createProfile(String email, Long userId, RecruiterProfile request) {

			 RecruiterProfile profile = new RecruiterProfile();
			 profile.setEmail(email);
			 profile.setUserId(userId);
			 
		        profile.setCompanyName(request.getCompanyName());
		        profile.setRecruiterName(request.getRecruiterName());
		        profile.setCompanyWebsite(request.getCompanyWebsite());
		        profile.setCompanyDescription(request.getCompanyDescription());
		        profile.setCompanyLocation(request.getCompanyLocation());
		        profile.setPhone(request.getPhone());

	  return repository.save(profile);

	 }
	 
	
		 
	    // GET profile
	    public RecruiterProfile getProfileByEmail(String email) {
	        return repository.findByEmail(email)
	                .orElseThrow(() -> new RuntimeException("Recruiter not found"));
	    }
	    
	    
	    
	        
	        public RecruiterProfile updateProfileByEmail(String email, RecruiterProfile request) {

	            RecruiterProfile profile = repository.findByEmail(email)
	                    .orElseThrow(() -> new RuntimeException("Recruiter not found"));

	        if(request.getCompanyName() != null){
	            profile.setCompanyName(request.getCompanyName());
	        }

	        if(request.getRecruiterName() != null) {
	        	profile.setRecruiterName(request.getRecruiterName());
	        }
	        
	        if(request.getCompanyWebsite() != null){
	            profile.setCompanyWebsite(request.getCompanyWebsite());
	        }

	        if(request.getCompanyDescription() != null){
	            profile.setCompanyDescription(request.getCompanyDescription());
	        }

	        if(request.getCompanyLocation() != null){
	            profile.setCompanyLocation(request.getCompanyLocation());
	        }

	        if(request.getPhone() != null){
	            profile.setPhone(request.getPhone());
	        }

	       
	        return repository.save(profile);
	    }


	    
	 // DELETE
	    public void deleteByEmail(String email) {

	        RecruiterProfile profile = repository.findByEmail(email)
	                .orElseThrow(() -> new RuntimeException("Recruiter not found"));

	        repository.delete(profile);
	    }
}
