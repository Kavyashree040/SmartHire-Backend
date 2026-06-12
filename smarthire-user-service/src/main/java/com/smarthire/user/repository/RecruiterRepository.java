package com.smarthire.user.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.smarthire.user.entity.RecruiterProfile;

public interface RecruiterRepository  extends JpaRepository<RecruiterProfile, Long>{

	 RecruiterProfile findByUserId(Long userId);
	 
	 Optional<RecruiterProfile> findByEmail(String email);


}
