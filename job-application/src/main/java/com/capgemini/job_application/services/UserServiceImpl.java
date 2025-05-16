package com.capgemini.job_application.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.job_application.entities.User;
import com.capgemini.job_application.exceptions.SkillNotFoundException;
import com.capgemini.job_application.exceptions.UserNotFoundException;
import com.capgemini.job_application.repositories.SkillRepository;
import com.capgemini.job_application.repositories.UserRepository;

import lombok.extern.slf4j.Slf4j;
@Service
@Slf4j 
public class UserServiceImpl implements UserService{
	
	private final UserRepository userRepository;
	private final SkillRepository skillRepository;
	
	
	@Autowired
	public UserServiceImpl(UserRepository userRepository, SkillRepository skillRepository) {
		super();
		this.userRepository = userRepository;
		this.skillRepository = skillRepository;
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

	@Override
	public User findByUserEmail(String email) {
		// TODO Auto-generated method stub
		return userRepository.findByUserEmail(email).orElseThrow( ()-> new UserNotFoundException("user this email id not found: "+email));
	}

	@Override
	public User findByUserNameOrUserEmail(String name, String email) {
		// TODO Auto-generated method stub
		return userRepository.findByUserNameOrUserEmail(name,email).orElseThrow( ()-> new UserNotFoundException("user this email id not found: "+email));
	}

	@Override
	public boolean existsByUserName(String name) {
		// TODO Auto-generated method stub
		return userRepository.existsByUserName(name);
	}

	@Override
	public boolean existsByUserEmail(String email) {
		// TODO Auto-generated method stub
		return userRepository.existsByUserEmail(email);
	}

	@Override
	public User setUserSkill(Long userId ,Long skillId) {
		// TODO Auto-generated method stub
		User user  = userRepository.findById(userId).orElseThrow( ()-> new UserNotFoundException("user this email id not found: "+userId));
		user.getSkills().add(skillRepository.findById(skillId).orElseThrow(()-> new SkillNotFoundException("Skill with this id not found "+skillId)));
		
		return userRepository.save(user);
	}
	
}
