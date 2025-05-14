package com.capgemini.job_application.entities;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Experience {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long experienceId;
	private Long userId;
	private String role;
	private String companyName;
	private LocalDate startDate;
	private LocalDate endDate;
	
}
