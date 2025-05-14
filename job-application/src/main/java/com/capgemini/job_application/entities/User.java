package com.capgemini.job_application.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long userId;
	
	private String userName;
	private String userEmail;
	private String phone;
	private String password;
	private String address;
	private String userType;
	private Integer age;
	private String gender;
	private String location;
	
}
