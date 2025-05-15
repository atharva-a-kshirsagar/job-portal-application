package com.capgemini.job_application.entities;

import java.time.LocalDate;

import java.util.Objects;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
@Entity
@Table(name = "experience")
public class Experience {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "experience_id")
	private Long experienceId;
	@Column(name = "user_id")
	private Long userId;
	
	@Column(name = "role")
	private String role;
	@Column(name = "company_name")
	private String companyName;
	@Column(name = "start_date")
	private LocalDate startDate;
	@Column(name = "end_date")
	private LocalDate endDate;
	public Experience(Long experienceId, Long userId, String role, String companyName, LocalDate startDate,
			LocalDate endDate) {
		super();
		this.experienceId = experienceId;
		this.userId = userId;
		this.role = role;
		this.companyName = companyName;
		this.startDate = startDate;
		this.endDate = endDate;
	}
	public Experience(Long userId, String role, String companyName, LocalDate startDate, LocalDate endDate) {
		super();
		this.userId = userId;
		this.role = role;
		this.companyName = companyName;
		this.startDate = startDate;
		this.endDate = endDate;
	}
	public Experience() {
		super();
	}
	public Long getExperienceId() {
		return experienceId;
	}
	public void setExperienceId(Long experienceId) {
		this.experienceId = experienceId;
	}
//	 public User getUser() {
//	        return user;
//	    }
//
//	    public void setUser(User user) {
//	        this.user = user;
//	    }
	
	public String getRole() {
		return role;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public LocalDate getStartDate() {
		return startDate;
	}
	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}
	public LocalDate getEndDate() {
		return endDate;
	}
	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}
	@Override
	public int hashCode() {
		return Objects.hash(companyName, endDate, experienceId, role, startDate, userId);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Experience other = (Experience) obj;
		return Objects.equals(companyName, other.companyName) && Objects.equals(endDate, other.endDate)
				&& Objects.equals(experienceId, other.experienceId) && Objects.equals(role, other.role)
				&& Objects.equals(startDate, other.startDate) && Objects.equals(userId, other.userId);
	}
	@Override
	public String toString() {
		return "Experience [experienceId=" + experienceId + ", userId=" + userId + ", role=" + role + ", companyName="
				+ companyName + ", startDate=" + startDate + ", endDate=" + endDate + "]";
	}
	
	
}
