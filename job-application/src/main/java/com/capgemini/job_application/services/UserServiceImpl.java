package com.capgemini.job_application.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.job_application.entities.User;
import com.capgemini.job_application.repositories.UserRepository;
@Service
public class UserServiceImpl implements UserService{
	
	private UserRepository userRepository;
	
	
	@Autowired
	public UserServiceImpl(UserRepository userRepository) {
		super();
		this.userRepository = userRepository;
	}

	@Override
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	@Override
	public User getUserById(Long id) {
		return userRepository.findById(id)
			    .orElseThrow(() -> new RuntimeException("No user found with ID: " + id));
	}

	@Override
	public User createUser(User user) {
		return userRepository.save(user);
	}

	@Override
	public void deleteUser(Long id) {
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

		return userRepository.save(newUser);
	}
	
}
