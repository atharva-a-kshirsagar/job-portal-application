package com.capgemini.job_application.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.job_application.entities.Application;
import com.capgemini.job_application.services.ApplicationService;

@RestController
@RequestMapping("/api/application")
public class ApplicationController {
	ApplicationService applicationService;

	@Autowired
	public ApplicationController(ApplicationService applicationService) {
		super();
		this.applicationService = applicationService;
	}
	
	@PostMapping
	public ResponseEntity<Application> createUser(@RequestBody Application user){
		return ResponseEntity.status(HttpStatus.CREATED).body(applicationService.createApplication(user));
	}
	
	@GetMapping
	public ResponseEntity<List<Application>> getApplicant(){
		return ResponseEntity.status(HttpStatus.FOUND).body(applicationService.getAllApplication());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Application> getUserById(@PathVariable Long id){
		return ResponseEntity.status(HttpStatus.FOUND).body(applicationService.getApplicationById(id));
	}
	
	@PutMapping("/{userId}")
	public ResponseEntity<Application> updateUser(@PathVariable Long userId , @RequestBody Application newUser){
		
		return ResponseEntity.status(HttpStatus.OK).body(applicationService.updateApplication(userId, newUser));
	}
	
	@PatchMapping("/{userId}")
	public ResponseEntity<Application> patchUser(@PathVariable Long userId , @RequestBody Application patch){
		return ResponseEntity.status(HttpStatus.OK).body(applicationService.patchApplication(userId, patch));
	}
	
	
	@DeleteMapping("/{userId}")
	public ResponseEntity<Void> deleteUser(@PathVariable Long userId){
		applicationService.deleteApplication(userId);
		return ResponseEntity.status(HttpStatus.OK).build();
	}
}
