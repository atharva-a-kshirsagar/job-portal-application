package com.capgemini.job_application.entities;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "job")
public class Job {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "job_id")
	private Long jobId;

	@NotNull(message ="Company ID is required")
	@Column(name = "company_id")
	private Long companyId;

	@NotBlank(message ="Job Title is required")
	@Column(name = "job_title")
	private String jobTitle;

	@NotNull(message ="Salary is required")
	@DecimalMin(value="0.0",inclusive = false ,message ="Salary must be greater than 0")
	@Column(name = "salary")
	private Double salary;

	@NotBlank(message = "Description is required")
	@Size(min = 10, message = "Description must be at least 10 characters")
	@Column(name = "description")
	private String description;

	@NotBlank(message = "Job Location is required")
	@Column(name = "job_location")
	private String jobLocation;

	@PastOrPresent(message = "Posting date cannot be in the future")
	@Column(name = "posting_date")
	private LocalDate postingDate;

	@Future(message = "Deadline date must be in the future")
	@Column(name = "deadline_date")
	private LocalDate deadlineDate;

	public Job() {
	}

	public Job(Long jobId, @NotNull(message = "Company ID is required") Long companyId,
			@NotBlank(message = "Job Title is required") String jobTitle,
			@NotNull(message = "Salary is required") @DecimalMin(value = "0.0", inclusive = false, message = "Salary must be greater than 0") Double salary,
			@NotBlank(message = "Description is required") @Size(min = 10, message = "Description must be at least 10 characters") String description,
			@NotBlank(message = "Job Location is required") String jobLocation,
			@PastOrPresent(message = "Posting date cannot be in the future") LocalDate postingDate,
			@Future(message = "Deadline date must be in the future") LocalDate deadlineDate) {
		this.jobId = jobId;
		this.companyId = companyId;
		this.jobTitle = jobTitle;
		this.salary = salary;
		this.description = description;
		this.jobLocation = jobLocation;
		this.postingDate = postingDate;
		this.deadlineDate = deadlineDate;
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

	public LocalDate getPostingDate() {
		return postingDate;
	}

	public void setPostingDate(LocalDate postingDate) {
		this.postingDate = postingDate;
	}

	public LocalDate getDeadlineDate() {
		return deadlineDate;
	}

	public void setDeadlineDate(LocalDate deadlineDate) {
		this.deadlineDate = deadlineDate;
	}

	@Override
	public String toString() {
		return "Job [jobId=" + jobId + ", companyId=" + companyId + ", jobTitle=" + jobTitle + ", salary=" + salary
				+ ", description=" + description + ", jobLocation=" + jobLocation + ", postingDate=" + postingDate
				+ ", deadlineDate=" + deadlineDate + "]";
	}

	
	
}
