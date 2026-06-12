package com.smarthire.application.service;

import java.util.List;

import com.smarthire.application.dto.ApplicationResponse;
import com.smarthire.application.dto.ApplyRequest;
import com.smarthire.application.dto.RecruiterApplicationResponse;
import com.smarthire.application.dto.RecruiterStatsResponse;
import com.smarthire.application.entity.Application;
import com.smarthire.application.enums.ApplicationStatus;

public interface ApplicationService {

Application applyJob(Long userId, ApplyRequest request);



List<RecruiterApplicationResponse> getApplicationsByJob(Long jobId);

List<ApplicationResponse> getApplicationsByEmail(String email);

Application updateStatus(Long applicationId, ApplicationStatus status, String email);

void withdrawApplication(Long applicationId, String email);

RecruiterStatsResponse getStats(Long jobId);
}