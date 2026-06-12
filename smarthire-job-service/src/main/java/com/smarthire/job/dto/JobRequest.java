package com.smarthire.job.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class JobRequest {

	@NotBlank(message = "Title is required")
	 private String title;
	
	@NotBlank(message = "Description is required")
	    private String description;
	
	@NotBlank(message = "Company name is required")
	    private String company;
	
	@NotBlank(message = "Location is required")
	    private String location;
	
	@NotBlank(message = "Salary is required")
	    private String salary;
	   
	@NotNull(message = "Experience is required")
	private Integer experience;
	
	 private String employmentType;
	    private String skillsRequired;
	    private String status;
	
	    


		public JobRequest() {
	    }

	    
	    public String getTitle() {
	        return title;
	    }

	    public void setTitle(String title) {
	        this.title = title;
	    }

	    public String getDescription() {
	        return description;
	    }

	    public void setDescription(String description) {
	        this.description = description;
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
	    
	    public Integer getExperience() {
	        return experience;
	    }

	    public void setExperience(Integer experience) {
	        this.experience = experience;
	    }
	    
	    public String getEmploymentType() {
			return employmentType;
		}


		public void setEmploymentType(String employmentType) {
			this.employmentType = employmentType;
		}


		public String getSkillsRequired() {
			return skillsRequired;
		}


		public void setSkillsRequired(String skillsRequired) {
			this.skillsRequired = skillsRequired;
		}


		public String getStatus() {
			return status;
		}


		public void setStatus(String status) {
			this.status = status;
		}


	   
}
