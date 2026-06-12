package com.smarthire.application.dto;

import java.time.LocalDateTime;

public class RecruiterApplicationResponse {
	
	 private Long applicationId;
	 
	 private Long candidateId;

	 private String candidateName;

	    private String email;

	    private String phone;

	    private String skills;

	    private Integer experience;

	    private String education;

	    private String location;

	    private String resumeUrl;

	    private String status;

	    private LocalDateTime appliedAt;
	    

		public Long getApplicationId() {
			return applicationId;
		}

		public void setApplicationId(Long applicationId) {
			this.applicationId = applicationId;
		}

		public Long getCandidateId() {
			return candidateId;
		}

		public void setCandidateId(Long candidateId) {
			this.candidateId = candidateId;
		}

		public String getCandidateName() {
			return candidateName;
		}

		public void setCandidateName(String candidateName) {
			this.candidateName = candidateName;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public String getPhone() {
			return phone;
		}

		public void setPhone(String phone) {
			this.phone = phone;
		}

		public String getSkills() {
			return skills;
		}

		public void setSkills(String skills) {
			this.skills = skills;
		}

		public Integer getExperience() {
			return experience;
		}

		public void setExperience(Integer experience) {
			this.experience = experience;
		}

		public String getEducation() {
			return education;
		}

		public void setEducation(String education) {
			this.education = education;
		}

		public String getLocation() {
			return location;
		}

		public void setLocation(String location) {
			this.location = location;
		}

		public String getResumeUrl() {
			return resumeUrl;
		}

		public void setResumeUrl(String resumeUrl) {
			this.resumeUrl = resumeUrl;
		}

		public String getStatus() {
			return status;
		}

		public void setStatus(String status) {
			this.status = status;
		}

		public LocalDateTime getAppliedAt() {
			return appliedAt;
		}

		public void setAppliedAt(LocalDateTime appliedAt) {
			this.appliedAt = appliedAt;
		}

	    
		

}
