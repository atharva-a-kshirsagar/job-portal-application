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
}
