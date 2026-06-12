package com.smarthire.application.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.smarthire.application.dto.ApplicationResponse;
import com.smarthire.application.dto.ApplyRequest;
import com.smarthire.application.dto.RecruiterApplicationResponse;
import com.smarthire.application.dto.RecruiterStatsResponse;
import com.smarthire.application.dto.StatusUpdateRequest;
import com.smarthire.application.entity.Application;
import com.smarthire.application.service.ApplicationService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/applications")
public class ApplicationController {
	
	 @Autowired
	    private ApplicationService service;


	    
	    @PostMapping("/apply")
	    public Application applyJob(HttpServletRequest request,
	                               @RequestBody ApplyRequest req) {

	    	Long userId = (Long) request.getAttribute("userId");

	    	return service.applyJob(userId, req);
	        
	    }
	    
	    @GetMapping("/my")
	    public List<ApplicationResponse> getMyApplications(HttpServletRequest request) {

	        String email = (String) request.getAttribute("email");

	        return service.getApplicationsByEmail(email);
	    }
	    
	   

	    

	    @GetMapping("/job/{jobId}")
	    public List<RecruiterApplicationResponse> getByJob(@PathVariable Long jobId) {
	        return service.getApplicationsByJob(jobId);
	    }
	    
	   
	    
	    @DeleteMapping("/{applicationId}")
	    public void withdraw(HttpServletRequest request,
	                         @PathVariable Long applicationId) {

	        String email = (String) request.getAttribute("email");

	        service.withdrawApplication(applicationId, email);
	    }
	    
	    @PutMapping("/{applicationId}/status")
	    public Application updateStatus(HttpServletRequest httpRequest,
	                                    @PathVariable Long applicationId,
	                                    @RequestBody StatusUpdateRequest request) {

	        String email = (String) httpRequest.getAttribute("email");

	        return service.updateStatus(applicationId, request.getStatus(), email);
	    }
	    
	    
	    @GetMapping("/stats/{jobId}")
	    public RecruiterStatsResponse getStats(

	    	@PathVariable Long jobId) {

	    	    return service.getStats(jobId);
	    	}
	    

}
