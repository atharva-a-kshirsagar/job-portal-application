package com.capgemini.job_application.entities;

import java.time.LocalDate;
import java.util.Objects;

import jakarta.annotation.Generated;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "application")
public class Application{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "application_id")
	private Long applicationId;
	
	@Column(name = "user_id")
	private Long userId;
	
	@Column(name = "job_id")
	private Long jobId;
	
	@Column(name = "applied_date")
	private LocalDate appliedDate;
	
	@Column(name = "status")
	private String status;

	public Application() {
		super();
	}

	public Application(Long applicationId, Long userId, Long jobId, LocalDate appliedDate, String status) {
		super();
		this.applicationId = applicationId;
		this.userId = userId;
		this.jobId = jobId;
		this.appliedDate = appliedDate;
		this.status = status;
	}

	public Long getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(Long applicationId) {
		this.applicationId = applicationId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getJobId() {
		return jobId;
	}

	public void setJobId(Long jobId) {
		this.jobId = jobId;
	}

	public LocalDate getAppliedDate() {
		return appliedDate;
	}

	public void setAppliedDate(LocalDate appliedDate) {
		this.appliedDate = appliedDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Application [applicationId=" + applicationId + ", userId=" + userId + ", jobId=" + jobId
				+ ", appliedDate=" + appliedDate + ", status=" + status + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(applicationId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Application other = (Application) obj;
		return Objects.equals(applicationId, other.applicationId);
	}
	
}