package com.smarthire.application.entity;

import java.time.LocalDateTime;

import com.smarthire.application.enums.ApplicationStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

@Entity

public class Application {

	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

    private Long jobId;

    private Long candidateId;

    private String resumeUrl;

    private String coverLetter;

    @Enumerated(EnumType.STRING)
    private ApplicationStatus status;
    
    private LocalDateTime appliedAt;


    public void setStatus(ApplicationStatus status) {
		this.status = status;
	}

    public ApplicationStatus getStatus() {
        return status;
    }

    
	
    public Application() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getJobId() {
        return jobId;
    }

    public void setJobId(Long jobId) {
        this.jobId = jobId;
    }

    public Long getCandidateId() {
        return candidateId;
    }

    public void setCandidateId(Long candidateId) {
        this.candidateId = candidateId;
    }

    public String getResumeUrl() {
        return resumeUrl;
    }

    public void setResumeUrl(String resumeUrl) {
        this.resumeUrl = resumeUrl;
    }

    public String getCoverLetter() {
        return coverLetter;
    }

    public void setCoverLetter(String coverLetter) {
        this.coverLetter = coverLetter;
    }

    
    public LocalDateTime getAppliedAt() {
        return appliedAt;
    }

    public void setAppliedAt(LocalDateTime appliedAt) {
        this.appliedAt = appliedAt;
    }
}
