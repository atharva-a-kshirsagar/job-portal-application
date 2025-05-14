package com.capgemini.job_application.entities;

import java.time.LocalDate;

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

}