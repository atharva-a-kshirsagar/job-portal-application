package com.capgemini.job_application.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.job_application.entities.User;
import com.capgemini.job_application.repositories.UserRepository;

import lombok.extern.slf4j.Slf4j;
@Service
@Slf4j 
public class UserServiceImpl implements UserService{
	
	private UserRepository userRepository;
	
	
	@Autowired
	public UserServiceImpl(UserRepository userRepository) {
		super();
		this.userRepository = userRepository;
	}

	@Override
	public List<User> getAllUsers() {
		log.debug("Fetching all users from the repository"); 
		return userRepository.findAll();
	}

	@Override
	public User getUserById(Long id) {
	    log.debug("Fetching user by ID: {}", id);
	    return userRepository.findById(id)
	        .orElseThrow(() -> {
	            log.warn("User not found with ID: {}", id);
	            return new RuntimeException("No user found with ID: " + id);
	        });
	}


	@Override
	public User createUser(User user) {
		log.debug("Saving new user to the repository"); 
		return userRepository.save(user);
	}

	@Override
	public void deleteUser(Long id) {
		log.debug("Deleting user by ID: {}", id);
	    User user = userRepository.findById(id)
	        .orElseThrow(() -> new RuntimeException("No user found with user ID: " + id));
	    userRepository.delete(user);
	}


	
	

	@Override
	public User updateUser(Long id, User user) {
		User newUser = userRepository.findById(id).orElseThrow(() -> new RuntimeException("No user found with ID: " + id));
;
		newUser.setUserName(user.getUserName());
		newUser.setPhone(user.getPhone());
		newUser.setPassword(user.getPassword());
		newUser.setAddress(user.getAddress());
		newUser.setUserType(user.getUserType());
		newUser.setAge(user.getAge());
		newUser.setGender(user.getGender());
		log.debug("Saving updated user to the repository"); 
		return userRepository.save(newUser);
	}
	
}
