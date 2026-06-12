package com.smarthire.application.dto;

import com.smarthire.application.enums.ApplicationStatus;

public class StatusUpdateRequest {

	
	 private ApplicationStatus status;

	    public ApplicationStatus getStatus() {
	        return status;
	    }

	    public void setStatus(ApplicationStatus status) {
	        this.status = status;
	    }
}
