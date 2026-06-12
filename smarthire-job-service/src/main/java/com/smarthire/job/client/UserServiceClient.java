package com.smarthire.job.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

import com.smarthire.job.config.FeignConfig;
import com.smarthire.job.dto.UserResponse;

@FeignClient
(name = "smarthire-user-service")


public interface UserServiceClient {

	 @GetMapping("/recruiter/profile")
	   
	 UserResponse getRecruiterProfile(
		       
		        @RequestHeader("Authorization") String token);
		        
		    
}
