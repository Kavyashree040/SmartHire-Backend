package com.smarthire.user.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="recruiter_profiles")
public class RecruiterProfile {
	@Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 private Long id;

	 private Long userId;

	 
	 private String companyName;
	 private String recruiterName;
	 

	 private String companyWebsite;
	 private String companyDescription;
	 private String companyLocation;
	 private String phone;
	 private String email;
	 
	 
	 public String getRecruiterName() {
			return recruiterName;
		}
		 public void setRecruiterName(String recruiterName) {
			 this.recruiterName = recruiterName;
		 }
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

	 public String getCompanyName() { return companyName; }
	 public void setCompanyName(String companyName) { this.companyName = companyName; }

	 public String getCompanyWebsite() { return companyWebsite; }
	 public void setCompanyWebsite(String companyWebsite) { this.companyWebsite = companyWebsite; }

	 public String getCompanyDescription() { return companyDescription; }
	 public void setCompanyDescription(String companyDescription) { this.companyDescription = companyDescription; }

	 public String getCompanyLocation() { return companyLocation; }
	 public void setCompanyLocation(String companyLocation) { this.companyLocation = companyLocation; }

	 public String getPhone() { return phone; }
	 public void setPhone(String phone) { this.phone = phone; }
	 
	 private String role = "RECRUITER";
}

