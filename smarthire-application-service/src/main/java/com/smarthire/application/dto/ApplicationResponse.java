package com.smarthire.application.dto;

import java.time.LocalDateTime;

public class ApplicationResponse {

	 private Long applicationId;

	    private Long jobId;

	    private String jobTitle;

	    private String status;
	    
	    private String company;

	    private String location;

	    private String salary;

	    private LocalDateTime appliedAt;

	    public Long getApplicationId() {
	        return applicationId;
	    }

	    public void setApplicationId(Long applicationId) {
	        this.applicationId = applicationId;
	    }

	    public Long getJobId() {
	        return jobId;
	    }

	    public void setJobId(Long jobId) {
	        this.jobId = jobId;
	    }

	    public String getJobTitle() {
	        return jobTitle;
	    }

	    public void setJobTitle(String jobTitle) {
	        this.jobTitle = jobTitle;
	    }

	    public String getCompany() {
			return company;
		}

		public void setCompany(String company) {
			this.company = company;
		}

		public String getLocation() {
			return location;
		}

		public void setLocation(String location) {
			this.location = location;
		}

		public String getSalary() {
			return salary;
		}

		public void setSalary(String salary) {
			this.salary = salary;
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
