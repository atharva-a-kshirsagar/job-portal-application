package com.capgemini.job_application.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "company")
public class Company {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "company_id")
	private Long companyId;
	
	@Column(name = "user_id")
	private Long userId;
	
	@Column(name = "company_name")
	private String companyName;
	
	@Column(name = "company_domain")
	private String companyDomain;
	
	@Column(name = "head_office")
	private String headOffice;
}
