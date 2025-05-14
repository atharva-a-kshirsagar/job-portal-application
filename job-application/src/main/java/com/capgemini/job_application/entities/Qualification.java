package com.capgemini.job_application.entities;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Qualification {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long qualificationId;
	
	private Long userId;
	private LocalDate startDate;
	private LocalDate endDate;
	private String qualificationType;
	private String url;
	private String institute;
	private String degree;
	public Qualification(Long qualificationId, Long userId, LocalDate startDate, LocalDate endDate,
			String qualificationType, String url, String institute, String degree) {
		super();
		this.qualificationId = qualificationId;
		this.userId = userId;
		this.startDate = startDate;
		this.endDate = endDate;
		this.qualificationType = qualificationType;
		this.url = url;
		this.institute = institute;
		this.degree = degree;
	}
	
	
}

