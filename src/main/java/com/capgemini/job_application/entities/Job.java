package com.capgemini.job_application.entities;

import java.time.LocalDate;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "job")
@AllArgsConstructor
@NoArgsConstructor
public class Job {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "job_id")
	private Long jobId;

	@ManyToOne
	@JoinColumn(name="company_id" , referencedColumnName = "company_id")
	@JsonBackReference(value = "company-job")
	private Company company;

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
	
	@OneToMany(mappedBy = "job", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonManagedReference(value = "job-application")
	private Set<Application> applications;
 

	public Long getJobId() {
		return jobId;
	}

	public void setJobId(Long jobId) {
		this.jobId = jobId;
	}


	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
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
	
	public Set<Application> getApplications() {
		return applications;
	}

	public void setApplications(Set<Application> applications) {
		this.applications = applications;
	}

	@Override
	public String toString() {
		return "Job [jobId=" + jobId + ", jobTitle=" + jobTitle + ", salary=" + salary
				+ ", description=" + description + ", jobLocation=" + jobLocation + ", postingDate=" + postingDate
				+ ", deadlineDate=" + deadlineDate + "]";
	}
	
}
