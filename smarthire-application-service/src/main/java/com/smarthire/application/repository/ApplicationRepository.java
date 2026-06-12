package com.smarthire.application.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.smarthire.application.entity.Application;
import com.smarthire.application.enums.ApplicationStatus;

public interface ApplicationRepository extends JpaRepository<Application, Long> {
	
	List<Application> findByCandidateId(Long candidateId);

    List<Application> findByJobId(Long jobId);

    Optional<Application> findByJobIdAndCandidateId(Long jobId, Long candidateId);
    
    long countByJobId(Long jobId);
    
    long countByJobIdAndStatus(
            Long jobId,
            ApplicationStatus status);

    

}
