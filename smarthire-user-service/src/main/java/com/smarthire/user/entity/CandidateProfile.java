package com.smarthire.user.entity;

import jakarta.persistence.Entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="candidate_profiles")
public class CandidateProfile {

	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 private Long id;

	 private Long userId;

	 private String name;
	 private String phone;
	 private String skills;
	 private Integer experience;
	 private String education;
	 private String location;
	 private String email;
	 private String resumeUrl;

	 public String getEmail() {
		return email;
	}
	 public void setEmail(String email) {
		 this.email = email;
	 }
	

	 public Long getId() { return id; }
	 public void setId(Long id) { this.id = id; }

	 public Long getUserId() { return userId; }
	 public void setUserId(Long userId) { this.userId = userId; }

	 public String getName() { return name; }
	 public void setName(String name) { this.name = name; }

	 public String getPhone() { return phone; }
	 public void setPhone(String phone) { this.phone = phone; }

	 public String getSkills() { return skills; }
	 public void setSkills(String skills) { this.skills = skills; }

	 public int getExperience() { return experience; }
	 public void setExperience(int experience) { this.experience = experience; }

	 public String getEducation() { return education; }
	 public void setEducation(String education) { this.education = education; }

	 public String getLocation() { return location; }
	 public void setLocation(String location) { this.location = location; }

	 public String getResumeUrl() { return resumeUrl; }
	 public void setResumeUrl(String resumeUrl) { this.resumeUrl = resumeUrl; }
	 
	 
	 private String role = "CANDIDATE";
	}

