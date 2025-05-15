package com.capgemini.job_application.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.capgemini.job_application.entities.User;

@Service
public interface UserService {
	
	List<User> getAllUsers ();
	
	User getUserById(Long id);
	
	User createUser(User user);
	
	void deleteUser(Long id);
	
	User updateUser(Long id, User user);
	
}
