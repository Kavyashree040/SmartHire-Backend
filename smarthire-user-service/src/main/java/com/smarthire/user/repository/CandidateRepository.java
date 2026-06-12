package com.smarthire.user.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.smarthire.user.entity.CandidateProfile;

public interface CandidateRepository extends JpaRepository<CandidateProfile, Long>{

	Optional<CandidateProfile> findByUserId(Long userId);
	 Optional<CandidateProfile> findByEmail(String email);

}
