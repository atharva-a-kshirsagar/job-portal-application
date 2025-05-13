package com.capgemini.job_application.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


public class Company {

	private Long companyId;
	private Long userId;
	private Long companyName;
	private String headOffice;
}
