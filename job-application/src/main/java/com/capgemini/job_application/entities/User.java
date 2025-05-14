package com.capgemini.job_application.entities;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "user")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private Long userId;
	
	@Column(name = "user_name")
	private String userName;
	
	@Column(name = "user_email")
	private String userEmail;
	
	@Column(name = "phone")
	private String phone;
	
	@Column(name = "password")
	private String password;
	
	@Column(name = "address")
	private String address;
	
	@Column(name = "user_type")
	private String userType;
	
	@Column(name = "age")
	private Integer age;
	
	@Column(name = "gender")
	private String gender;

	public User(Long userId, String userName, String userEmail, String phone, String password, String address,
			String userType, Integer age, String gender) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.userEmail = userEmail;
		this.phone = phone;
		this.password = password;
		this.address = address;
		this.userType = userType;
		this.age = age;
		this.gender = gender;
	}

	public User() {
		super();
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", userName=" + userName + ", userEmail=" + userEmail + ", phone=" + phone
				+ ", password=" + password + ", address=" + address + ", userType=" + userType + ", age=" + age
				+ ", gender=" + gender + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(address, age, gender, password, phone, userEmail, userId, userName, userType);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return Objects.equals(address, other.address) && Objects.equals(age, other.age)
				&& Objects.equals(gender, other.gender) && Objects.equals(password, other.password)
				&& Objects.equals(phone, other.phone) && Objects.equals(userEmail, other.userEmail)
				&& Objects.equals(userId, other.userId) && Objects.equals(userName, other.userName)
				&& Objects.equals(userType, other.userType);
	}
	
	
	
}
