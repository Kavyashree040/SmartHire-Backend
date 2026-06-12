package com.smarthire.application.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.smarthire.application.config.FeignConfig;
import com.smarthire.application.dto.UserResponse;

@FeignClient(name = "smarthire-user-service",
configuration = FeignConfig.class)
public interface UserServiceClient {

	@GetMapping("/candidate/profile/{userId}")
    UserResponse  getCandidateProfile(@PathVariable Long userId);

	

    
    @GetMapping("/candidate/email/{email}")
    UserResponse getCandidateByEmail(@PathVariable String email);
}
