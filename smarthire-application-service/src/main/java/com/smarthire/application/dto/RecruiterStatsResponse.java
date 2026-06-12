package com.smarthire.application.dto;

public class RecruiterStatsResponse {
	
	 private long totalApplications;
	    private long shortlisted;
	    private long interview;
	    private long rejected;
	    private long hired;

	    public long getTotalApplications() {
	        return totalApplications;
	    }

	    public void setTotalApplications(long totalApplications) {
	        this.totalApplications = totalApplications;
	    }

	    public long getShortlisted() {
	        return shortlisted;
	    }

	    public void setShortlisted(long shortlisted) {
	        this.shortlisted = shortlisted;
	    }

	    public long getInterview() {
	        return interview;
	    }

	    public void setInterview(long interview) {
	        this.interview = interview;
	    }

	    public long getRejected() {
	        return rejected;
	    }

	    public void setRejected(long rejected) {
	        this.rejected = rejected;
	    }

	    public long getHired() {
	        return hired;
	    }

	    public void setHired(long hired) {
	        this.hired = hired;
	    }

}
