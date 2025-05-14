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

import com.capgemini.job_application.entities.Qualification;
import com.capgemini.job_application.services.QualificationService;

@RestController
@RequestMapping("/api/qualifications")
public class QualificationController {

	public final QualificationService qualificationService;

	@Autowired
	public QualificationController(QualificationService qualificationService) {
		this.qualificationService = qualificationService;
	}
	
	@GetMapping
	public ResponseEntity<List<Qualification>> getAllQualifications(){
		return ResponseEntity.status(HttpStatus.OK).body(qualificationService.getAllQualifications());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Qualification> findQualificationById(@PathVariable Long id){
		return ResponseEntity.status(HttpStatus.OK).body(qualificationService.findQualificationById(id));
	}
	
	@PostMapping
	public ResponseEntity<Qualification> createQualification(@RequestBody Qualification qualification){
		return ResponseEntity.status(HttpStatus.CREATED).body(qualificationService.createQualification(qualification));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Qualification> updateQualification(@PathVariable Long id, @RequestBody Qualification qualification){
		return ResponseEntity.status(HttpStatus.OK).body(qualificationService.updateQualification(id, qualification));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteQualification(@PathVariable Long id){
		qualificationService.deleteQualification(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
	
	@PatchMapping("/{id}")
	public ResponseEntity<Qualification> patchQualification(@PathVariable Long id, @RequestBody Qualification qualification){
		return ResponseEntity.status(HttpStatus.OK).body(qualificationService.patchQualification(id, qualification));
	}
	
}
