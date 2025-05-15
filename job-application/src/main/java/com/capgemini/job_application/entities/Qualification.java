package com.capgemini.job_application.entities;

import java.time.LocalDate;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name="qualification")
public class Qualification {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "qualification_id")
	private Long qualificationId;

	@NotNull(message = "UserId must not be blank")
	@Column(name = "user_id")
	private Long userId;

	@NotNull(message = "Start date must not be blank")
	@Column(name = "start_date")
	private LocalDate startDate;

	@NotNull(message = "End date must not be blank")
	@Column(name = "end_date")
	private LocalDate endDate;

	@NotBlank(message = "Qualification type must not be blank")
	@Column(name = "qualification_type")
	private String qualificationType;

	@NotBlank(message = "Institute URL must not be blank")
	@Column(name = "url")
	private String url;

	@NotBlank(message = "Institute Name must not be blank")
	@Column(name = "institute")
	private String institute;

	@NotBlank(message = "Degree must not be blank")
	@Column(name = "degree")
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

	public Qualification() {
		super();
	}

	public Long getQualificationId() {
		return qualificationId;
	}

	public void setQualificationId(Long qualificationId) {
		this.qualificationId = qualificationId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
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

	public String getQualificationType() {
		return qualificationType;
	}

	public void setQualificationType(String qualificationType) {
		this.qualificationType = qualificationType;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getInstitute() {
		return institute;
	}

	public void setInstitute(String institute) {
		this.institute = institute;
	}

	public String getDegree() {
		return degree;
	}

	public void setDegree(String degree) {
		this.degree = degree;
	}

	@Override
	public String toString() {
		return "Qualification [qualificationId=" + qualificationId + ", userId=" + userId + ", startDate=" + startDate
				+ ", endDate=" + endDate + ", qualificationType=" + qualificationType + ", url=" + url + ", institute="
				+ institute + ", degree=" + degree + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(degree, endDate, institute, qualificationId, qualificationType, startDate, url, userId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Qualification other = (Qualification) obj;
		return Objects.equals(degree, other.degree) && Objects.equals(endDate, other.endDate)
				&& Objects.equals(institute, other.institute) && Objects.equals(qualificationId, other.qualificationId)
				&& Objects.equals(qualificationType, other.qualificationType)
				&& Objects.equals(startDate, other.startDate) && Objects.equals(url, other.url)
				&& Objects.equals(userId, other.userId);
	}

}
