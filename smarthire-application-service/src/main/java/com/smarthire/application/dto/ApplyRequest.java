package com.smarthire.application.dto;

public class ApplyRequest {

	 private Long jobId;
	   
	    private String coverLetter;
	    
	    private String resumeUrl;
	    
	    

	    public String getResumeUrl() {
			return resumeUrl;
		}

		public void setResumeUrl(String resumeUrl) {
			this.resumeUrl = resumeUrl;
		}

		public Long getJobId() {
	        return jobId;
	    }

	    public void setJobId(Long jobId) {
	        this.jobId = jobId;
	    }

	   

	    

	    public String getCoverLetter() {
	        return coverLetter;
	    }

	    public void setCoverLetter(String coverLetter) {
	        this.coverLetter = coverLetter;
	    }
}
