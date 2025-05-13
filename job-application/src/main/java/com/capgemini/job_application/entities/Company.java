package com.capgemini.job_application.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Company {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long companyId;

	private String compName;
	private String compLocation;
	private String compEmail;

	public Company() {
	}

	public Company(long companyId, String compName, String compLocation, String compEmail) {
		this.companyId = companyId;
		this.compName = compName;
		this.compLocation = compLocation;
		this.compEmail = compEmail;
	}

	public long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(long companyId) {
		this.companyId = companyId;
	}

	public String getCompName() {
		return compName;
	}

	public void setCompName(String compName) {
		this.compName = compName;
	}

	public String getCompLocation() {
		return compLocation;
	}

	public void setCompLocation(String compLocation) {
		this.compLocation = compLocation;
	}

	public String getCompEmail() {
		return compEmail;
	}

	public void setCompEmail(String compEmail) {
		this.compEmail = compEmail;
	}

	@Override
	public String toString() {
		return "Company [companyId=" + companyId + ", compName=" + compName + ", compLocation=" + compLocation
				+ ", compEmail=" + compEmail + "]";
	}

}
