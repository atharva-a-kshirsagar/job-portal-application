package com.capgemini.job_application.entities;

import java.util.Objects;

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

	public Company() {
	}

	public Company(Long companyId, Long userId, String companyName, String companyDomain, String headOffice) {
		this.companyId = companyId;
		this.userId = userId;
		this.companyName = companyName;
		this.companyDomain = companyDomain;
		this.headOffice = headOffice;
	}

	public Long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getCompanyDomain() {
		return companyDomain;
	}

	public void setCompanyDomain(String companyDomain) {
		this.companyDomain = companyDomain;
	}

	public String getHeadOffice() {
		return headOffice;
	}

	public void setHeadOffice(String headOffice) {
		this.headOffice = headOffice;
	}

	@Override
	public String toString() {
		return "Company [companyId=" + companyId + ", userId=" + userId + ", companyName=" + companyName
				+ ", companyDomain=" + companyDomain + ", headOffice=" + headOffice + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(companyDomain, companyId, companyName, headOffice, userId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Company other = (Company) obj;
		return Objects.equals(companyDomain, other.companyDomain) && Objects.equals(companyId, other.companyId)
				&& Objects.equals(companyName, other.companyName) && Objects.equals(headOffice, other.headOffice)
				&& Objects.equals(userId, other.userId);
	}

}
