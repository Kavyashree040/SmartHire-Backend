package com.smarthire.application.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smarthire.application.client.JobServiceClient;
import com.smarthire.application.client.UserServiceClient;
import com.smarthire.application.dto.ApplicationResponse;
import com.smarthire.application.dto.ApplyRequest;
import com.smarthire.application.dto.JobResponse;
import com.smarthire.application.dto.RecruiterApplicationResponse;
import com.smarthire.application.dto.RecruiterStatsResponse;
import com.smarthire.application.dto.UserResponse;
import com.smarthire.application.entity.Application;
import com.smarthire.application.enums.ApplicationStatus;
import com.smarthire.application.exception.ApplicationNotFoundException;
import com.smarthire.application.exception.CandidateNotFoundException;
import com.smarthire.application.exception.DuplicateApplicationException;
import com.smarthire.application.exception.JobNotFoundException;
import com.smarthire.application.exception.ResumeRequiredException;
import com.smarthire.application.exception.UnauthorizedException;
import com.smarthire.application.repository.ApplicationRepository;

@Service
public class ApplicationServiceImpl implements ApplicationService {
	
	 @Autowired
	    private ApplicationRepository repository;
	 
	 @Autowired
	 private UserServiceClient userClient;

	 @Autowired
	 private JobServiceClient jobClient;

	 @Override
	 public Application applyJob(Long userId,  ApplyRequest request) {
		 
		 UserResponse user;
		    JobResponse job;

	     // check candidate
		    try {
		    	
		    	user = userClient.getCandidateProfile(userId);
		    	System.out.println("TOKEN USER ID = " + userId);
		    	System.out.println("USER RESPONSE ID: " + user.getUserId());
		    	System.out.println("USER RESPONSE EMAIL: " + user.getEmail());
		    } catch (Exception e) {
		    	 e.printStackTrace();
		    	    throw new CandidateNotFoundException(e.getMessage());
		    }
		    
		 // Resume validation
		    if ((user.getResumeUrl() == null || user.getResumeUrl().isBlank())
		            && (request.getResumeUrl() == null || request.getResumeUrl().isBlank())) {

		        throw new ResumeRequiredException(
		                "Please upload a resume before applying");
		    }
		    
		    
	     // check job
		    try {
		        job = jobClient.getJobById(request.getJobId());
		    } catch (Exception e) {
		       // throw new RuntimeException("Job not found");
		    	throw new JobNotFoundException("Job not found");
		    }
		    
		    if ("CLOSED".equalsIgnoreCase(job.getStatus())) {
		        throw new RuntimeException(
		                "This job is no longer accepting applications");
		    }


	     
	     
	  // ✅ 3. Prevent duplicate apply
		    if (repository.findByJobIdAndCandidateId(
		    		request.getJobId(), user.getUserId()).isPresent()) {

		      
		    	throw new DuplicateApplicationException("Already applied to this job");
		    }

	  // ✅ 4. Create application
	     Application application = new Application();

	     application.setJobId(request.getJobId());
	     application.setCandidateId(user.getUserId());

	    
	     if(request.getResumeUrl() != null &&
	    		   !request.getResumeUrl().isBlank()) {

	    		    application.setResumeUrl(
	    		            request.getResumeUrl());

	    		} else {

	    		    application.setResumeUrl(
	    		            user.getResumeUrl());
	    		}

	     application.setCoverLetter(request.getCoverLetter());
	     application.setStatus(ApplicationStatus.APPLIED);
	     application.setAppliedAt(LocalDateTime.now());

	     return repository.save(application);
	 }

	  

	    @Override
	    public List<RecruiterApplicationResponse> getApplicationsByJob(Long jobId) {
	    	 List<Application> applications =
	    	            repository.findByJobId(jobId);
	    	 System.out.println("APPLICATIONS FOUND: " + applications.size());


	    	    return applications.stream().map(app -> {
	    	    	
	    	    	 System.out.println("APPLICATION CANDIDATE ID: " + app.getCandidateId());

	    	        UserResponse user =
	    	                userClient.getCandidateProfile(app.getCandidateId());

	    	        RecruiterApplicationResponse res =
	    	                new RecruiterApplicationResponse();

	    	        res.setApplicationId(app.getId());
	    	        res.setCandidateId(app.getCandidateId());
	    	         
	    	        res.setCandidateName(user.getName());
	    	        res.setEmail(user.getEmail());

	    	        res.setPhone(user.getPhone());
	    	        res.setSkills(user.getSkills());
	    	        res.setExperience(user.getExperience());
	    	        res.setEducation(user.getEducation());
	    	        res.setLocation(user.getLocation());

	    	        res.setResumeUrl(user.getResumeUrl());

	    	        res.setStatus(app.getStatus().name());
	    	        res.setAppliedAt(app.getAppliedAt());
	    	        System.out.println("JOB ID RECEIVED: " + jobId);
	    	        return res;

	    	        
	    	    }).toList();
	    	    
	    	    
	    }
	    
	    @Override
	    public List<ApplicationResponse> getApplicationsByEmail(String email) {

	        //  Get candidate using email
	        UserResponse candidate = userClient.getCandidateByEmail(email);

	        List<Application> applications =
	                repository.findByCandidateId(candidate.getUserId());

	        return applications.stream().map(app -> {

	            JobResponse job =
	                    jobClient.getJobById(app.getJobId());

	            ApplicationResponse response =
	                    new ApplicationResponse();

	            response.setApplicationId(app.getId());

	            response.setJobId(app.getJobId());

	            response.setJobTitle(job.getTitle());
	            
	            response.setCompany(job.getCompany());

	            response.setLocation(job.getLocation());

	            response.setSalary(job.getSalary());

	            response.setStatus(app.getStatus().name());

	            response.setAppliedAt(app.getAppliedAt());

	            return response;

	        }).toList();
	    }

	    @Override
	    public Application updateStatus(Long applicationId,
                ApplicationStatus status,
                String email) {

	    	Application app = repository.findById(applicationId)
	    	        .orElseThrow(() -> new ApplicationNotFoundException("Application not found"));
            
	    	

	    	app.setStatus(status);
	    	return repository.save(app);
	    }
     
	    @Override
	    public void withdrawApplication(Long applicationId, String email) {

	        Application app = repository.findById(applicationId)
	                .orElseThrow(() -> new ApplicationNotFoundException("Application not found"));

	        UserResponse candidate = userClient.getCandidateByEmail(email);

	        if (!app.getCandidateId().equals(candidate.getUserId())) {
	        	throw new UnauthorizedException("You cannot delete this application");
	        }

	        repository.deleteById(applicationId);
	    }
	    
	    
	    @Override
	    public RecruiterStatsResponse getStats(Long jobId) {

	        RecruiterStatsResponse stats =
	                new RecruiterStatsResponse();

	        stats.setTotalApplications(
	                repository.countByJobId(jobId));

	        stats.setShortlisted(
	                repository.countByJobIdAndStatus(
	                        jobId,
	                        ApplicationStatus.SHORTLISTED));

	        stats.setInterview(
	                repository.countByJobIdAndStatus(
	                        jobId,
	                        ApplicationStatus.INTERVIEW));

	        stats.setRejected(
	                repository.countByJobIdAndStatus(
	                        jobId,
	                        ApplicationStatus.REJECTED));

	        stats.setHired(
	                repository.countByJobIdAndStatus(
	                        jobId,
	                        ApplicationStatus.HIRED));

	        return stats;
	    }

}
