package com.smarthire.application.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.smarthire.application.config.FeignConfig;
import com.smarthire.application.dto.JobResponse;

@FeignClient(name = "smarthire-job-service",
configuration = FeignConfig.class)

public interface JobServiceClient {
	@GetMapping("/jobs/{jobId}")
    JobResponse getJobById(@PathVariable("jobId") Long jobId);


}
