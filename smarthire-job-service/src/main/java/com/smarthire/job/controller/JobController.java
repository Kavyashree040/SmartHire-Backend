package com.smarthire.job.controller;

import java.util.List;

import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.smarthire.job.dto.JobRequest;
import com.smarthire.job.entity.Job;
import com.smarthire.job.service.JobService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/jobs")
public class JobController {

	
	 private final JobService jobService;

	    public JobController(JobService jobService) {
	        this.jobService = jobService;
	    }



	    @PostMapping("/create")
	    public Job createJob(
	            @RequestBody JobRequest request,
	            HttpServletRequest httpRequest,
	            @RequestHeader("Authorization") String token) {

	        Long userId = (Long) httpRequest.getAttribute("userId");
	        
	        String authToken =
	                httpRequest.getHeader("Authorization");


	        return jobService.createJob(userId, request, token);
	    }

	    @GetMapping
	    public List<Job> getAllJobs() {
	        return jobService.getAllJobs();
	    }

	    @GetMapping("/{id}")
	    public Job getJobById(@PathVariable Long id) {
	        return jobService.getJobById(id);
	    }

	    
	    @PutMapping("/{id}")
	    public Job updateJob(
	            @PathVariable Long id,
	             @RequestBody JobRequest request,
	            HttpServletRequest httpRequest) {

	        Long userId = (Long) httpRequest.getAttribute("userId");

	        return jobService.updateJob(id, userId, request);
	    }
	    
	
	    
	    @DeleteMapping("/{id}")
	    public void deleteJob(
	    		 @PathVariable Long id,
	            HttpServletRequest httpRequest) {

	        Long userId = (Long) httpRequest.getAttribute("userId");

	        jobService.deleteJob(id, userId);
	    }
	    
	    @GetMapping("/my-jobs")
	    public List<Job> getMyJobs(HttpServletRequest request) {

	        Long userId = (Long) request.getAttribute("userId");

	        return jobService.getMyJobs(userId);
	    }
	    
	    @GetMapping("/filter")
	    public List<Job> filterJobs(
	            @RequestParam(required = false) String keyword,
	            @RequestParam(required = false) String location,
	            @RequestParam(required = false) String company) {

	        return jobService.filterJobs(keyword, location, company);
	    }
	    
}
