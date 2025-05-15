package com.capgemini.job_application.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "job")
public class Job {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "job_id")
	private Long jobId;
	
	@Column(name = "company_id")
	private Long companyId;
	
	@Column(name = "job_title")
	private String jobTitle;
	
	@Column(name = "salary")
	private Double salary;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "job_location")
	private String jobLocation;

	public Job() {
	}

	public Job(Long jobId, Long companyId, String jobTitle, Double salary, String description, String jobLocation) {
		this.jobId = jobId;
		this.companyId = companyId;
		this.jobTitle = jobTitle;
		this.salary = salary;
		this.description = description;
		this.jobLocation = jobLocation;
	}

	public Long getJobId() {
		return jobId;
	}

	public void setJobId(Long jobId) {
		this.jobId = jobId;
	}

	public Long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}

	public String getJobTitle() {
		return jobTitle;
	}

	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}

	public Double getSalary() {
		return salary;
	}

	public void setSalary(Double salary) {
		this.salary = salary;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getJobLocation() {
		return jobLocation;
	}

	public void setJobLocation(String jobLocation) {
		this.jobLocation = jobLocation;
	}

	@Override
	public String toString() {
		return "Job [jobId=" + jobId + ", companyId=" + companyId + ", jobTitle=" + jobTitle + ", salary=" + salary
				+ ", description=" + description + ", jobLocation=" + jobLocation + "]";
	}
	
	
}
