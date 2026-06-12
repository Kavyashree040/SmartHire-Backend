package com.smarthire.job.service;

import java.time.LocalDateTime;
import java.util.List;


import org.springframework.stereotype.Service;

import com.smarthire.job.client.UserServiceClient;
import com.smarthire.job.dto.JobRequest;
import com.smarthire.job.dto.UserResponse;
import com.smarthire.job.entity.Job;
import com.smarthire.job.exception.JobNotFoundException;
import com.smarthire.job.exception.UnauthorizedException;
import com.smarthire.job.repository.JobRepository;

@Service
public class JobService {

	
	 private final JobRepository jobRepository;
	 private final  UserServiceClient userClient;

	    public JobService(JobRepository jobRepository, UserServiceClient userClient) {
	        this.jobRepository = jobRepository;
	        this.userClient = userClient;
	    }

	    public Job createJob(Long userId, JobRequest request, String authToken) {
	    	
	    	
	        
	        // call user service
	        UserResponse recruiter = userClient.getRecruiterProfile(authToken);

	        if (recruiter == null) {
	            throw new RuntimeException("Recruiter not found");
	        }



	        Job job = new Job();
	        
	     // ✅ from JWT
	        job.setRecruiterId(userId);


	        job.setTitle(request.getTitle());
	        job.setDescription(request.getDescription());
	        job.setCompany(request.getCompany());
	        job.setLocation(request.getLocation());
	        job.setSalary(request.getSalary());
	        job.setExperience(request.getExperience());
	       
	        job.setEmploymentType(request.getEmploymentType());
	        job.setSkillsRequired(request.getSkillsRequired());

	        // Default status
	        job.setStatus("OPEN");

	        job.setCreatedAt(LocalDateTime.now());

	        

	        return jobRepository.save(job);
	    }

	    public List<Job> getAllJobs() {
	        return jobRepository.findAll();
	    }

	    public Job getJobById(Long id) {
	        return jobRepository.findById(id)
	        		.orElseThrow(() -> new JobNotFoundException("Job not found"));
	    }

	 //  UPDATE JOB (ownership check)
	    public Job updateJob(Long jobId, Long userId, JobRequest request) {

	        Job job = jobRepository.findById(jobId)
	                .orElseThrow(() -> new JobNotFoundException("Job not found"));

	        //  security check
	        if (!job.getRecruiterId().equals(userId)) {
	            throw new UnauthorizedException("Unauthorized");
	        }

	        // update only non-null fields

	        if (request.getTitle() != null) {
	            job.setTitle(request.getTitle());
	        }

	        if (request.getDescription() != null) {
	            job.setDescription(request.getDescription());
	        }

	        if (request.getCompany() != null) {
	            job.setCompany(request.getCompany());
	        }

	        if (request.getLocation() != null) {
	            job.setLocation(request.getLocation());
	        }

	        if (request.getSalary() != null) {
	            job.setSalary(request.getSalary());
	        }

	        if (request.getExperience() != null) {
	            job.setExperience(request.getExperience());
	            
	        }
	        
	        if (request.getEmploymentType() != null) {
	            job.setEmploymentType(request.getEmploymentType());
	        }

	        if (request.getSkillsRequired() != null) {
	            job.setSkillsRequired(request.getSkillsRequired());
	        }

	        if (request.getStatus() != null) {
	            job.setStatus(request.getStatus());
	        }
	        return jobRepository.save(job);
	    }


	    
	 //  DELETE JOB (ownership check)
	    public void deleteJob(Long jobId, Long userId) {

	        Job job = jobRepository.findById(jobId)
	                .orElseThrow(() -> new JobNotFoundException("Job not found"));

	        // security check
	        if (!job.getRecruiterId().equals(userId)) {
	            throw new UnauthorizedException("Unauthorized");
	        }

	        jobRepository.deleteById(jobId);
	    }
	    
	    //get my-jobs
	    public List<Job> getMyJobs(Long userId) {
	        return jobRepository.findByRecruiterId(userId);
	    }
	    
	    public List<Job> filterJobs(
	            String keyword,
	            String location,
	            String company) {

	        List<Job> jobs = jobRepository.findAll();

	        // Multiple title search
	        if (keyword != null && !keyword.isBlank()) {

	            String[] keywords = keyword.toLowerCase().split(",");

	            jobs = jobs.stream()
	                    .filter(job -> {

	                        if (job.getTitle() == null) {
	                            return false;
	                        }

	                        String title = job.getTitle().toLowerCase();

	                        for (String word : keywords) {
	                            if (title.contains(word.trim())) {
	                                return true;
	                            }
	                        }

	                        return false;
	                    })
	                    .toList();
	        }

	        
	        if (location != null && !location.isBlank()) {

	            String[] locations = location.toLowerCase().split(",");

	            jobs = jobs.stream()
	                    .filter(job -> {

	                        if (job.getLocation() == null) {
	                            return false;
	                        }

	                        String jobLocation =
	                                job.getLocation().toLowerCase();

	                        for (String loc : locations) {
	                            if (jobLocation.contains(loc.trim())) {
	                                return true;
	                            }
	                        }

	                        return false;
	                    })
	                    .toList();
	        }

	        // Company filter
	        if (company != null && !company.isBlank()) {

	            String[] companies = company.toLowerCase().split(",");

	            jobs = jobs.stream()
	                    .filter(job -> {

	                        if (job.getCompany() == null) {
	                            return false;
	                        }

	                        String jobCompany =
	                                job.getCompany().toLowerCase();

	                        for (String comp : companies) {
	                            if (jobCompany.contains(comp.trim())) {
	                                return true;
	                            }
	                        }

	                        return false;
	                    })
	                    .toList();
	        }

	        return jobs;
	    }
}
	    

