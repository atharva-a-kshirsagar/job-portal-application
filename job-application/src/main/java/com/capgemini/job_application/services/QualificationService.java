package com.capgemini.job_application.services;

import java.util.List;

import com.capgemini.job_application.entities.Qualification;

public interface QualificationService {

	List<Qualification> getAllQualifications();
	
	Qualification findQualificationById(Long id);
	Qualification createQualification(Qualification qualification);
	Qualification updateQualification(Long id, Qualification qualification);
	Qualification patchQualification(Long id, Qualification qualification);
	void deleteQualification(Long id);
}
