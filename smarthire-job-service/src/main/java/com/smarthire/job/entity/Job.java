package com.smarthire.job.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Job {

	
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;
	 private String title;

	    private String company;

	    private String location;

	    private String description;

	    private String salary;

	    private Integer experience;


	    private Long recruiterId;
	    
	    private String employmentType;

	    private String skillsRequired;

	    private String status;

	    private LocalDateTime createdAt;

	    

		public Job() {
	    }

	    public Job(Long id, String title, String description,
	            String company, String location,
	            String salary, Integer experience,
	            Long recruiterId, String employmentType, String skillsRequired, String status, LocalDateTime createdAt ) {

	     this.id = id;
	     this.title = title;
	     this.description = description;
	     this.company = company;
	     this.location = location;
	     this.salary = salary;
	     this.experience = experience;
	     this.recruiterId = recruiterId;
	     this.skillsRequired = skillsRequired;
	     this.employmentType = employmentType;
	     this.status = status;
	     this.createdAt = createdAt;
	 }

	    public Long getId() {
	        return id;
	    } 

	    public void setId(Long id) {
	        this.id = id;
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
	    
	    public Long getRecruiterId() {
	        return recruiterId;
	    }

	    public void setRecruiterId(Long recruiterId) {
	        this.recruiterId = recruiterId;
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

		public LocalDateTime getCreatedAt() {
			return createdAt;
		}

		public void setCreatedAt(LocalDateTime createdAt) {
			this.createdAt = createdAt;
		}

}
