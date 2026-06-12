package com.smarthire.job.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.smarthire.job.entity.Job;

public interface JobRepository extends JpaRepository<Job, Long> {
	
	List<Job> findByRecruiterId(Long recruiterId);
	
	List<Job> findByTitleContainingIgnoreCase(String title);

	List<Job> findByLocationContainingIgnoreCase(String location);

	List<Job> findByCompanyContainingIgnoreCase(String company);

}
