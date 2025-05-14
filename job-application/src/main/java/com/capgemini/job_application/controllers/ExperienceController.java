package com.capgemini.job_application.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.job_application.entities.Experience;
import com.capgemini.job_application.services.ExperienceService;

@CrossOrigin(origins = "*")
@RequestMapping("/api/experiences")
@RestController
public class ExperienceController {
	ExperienceService expService;

	@Autowired
	public ExperienceController(ExperienceService expService) {
		super();
		this.expService = expService;
	}
	
	@GetMapping("/{userId}")
	public ResponseEntity<List<Experience>> getExperienceByUserId(@PathVariable Long userId){
		List<Experience> experiences = expService.getExperienceByUser_id(userId);
		return ResponseEntity.status(HttpStatus.OK).body(experiences);
	}
	@PostMapping
	public ResponseEntity<Experience> createExperience(@RequestBody Experience exp){
		Experience experience = expService.createExperience(exp);
		return ResponseEntity.status(HttpStatus.CREATED).body(experience);
	}
	
	@DeleteMapping("/delete/experience/{expId}")
	public ResponseEntity<Void> deleteExperienceById(@PathVariable Long expId){
		expService.deleteExperienceById(expId);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
	
	@DeleteMapping("/delete/{userId}")
	public ResponseEntity<Void> deleteAllExperiences(@PathVariable Long userId){
		expService.deleteAllExperiencesByUserId(userId);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
	
	@PutMapping("/{expId}/update/{userId}")
	public ResponseEntity<Experience> updateExperience(@PathVariable Long expId,@PathVariable Long userId,@RequestBody Experience updatedExp){
		Experience experience = expService.updateExperience(expId, userId, updatedExp);
		return ResponseEntity.status(HttpStatus.OK).body(experience);
	}
	
}
