package com.capgemini.job_application.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.job_application.entities.Qualification;
import com.capgemini.job_application.entities.User;
import com.capgemini.job_application.exceptions.UserNotFoundException;
import com.capgemini.job_application.repositories.QualificationRepository;
import com.capgemini.job_application.repositories.UserRepository;

@Service
public class QualificationServiceImpl implements QualificationService{

	private final QualificationRepository qualificationRepository;
	private final UserRepository userRepository;
	
	@Autowired
	public QualificationServiceImpl(QualificationRepository qualificationRepository, UserRepository userRepository) {
		this.qualificationRepository = qualificationRepository;
		this.userRepository = userRepository;
	}

	@Override
	public List<Qualification> getAllQualifications() {
		return qualificationRepository.findAll();
	}

	@Override
	public Qualification findQualificationById(Long id) {
		return qualificationRepository.findById(id).orElseThrow();
	}

	@Override
	public Qualification createQualification(Qualification qualification) {
//		if(qualification.getUser()==null) {
//			throw new RuntimeException("Users is null");
//		}
		System.out.println("User ID: " + qualification.getUser().getUserId());
		System.out.println("Qualification: " + qualification);

		System.err.println(qualification.getUser());
		Long userId = qualification.getUser().getUserId();
		User user = userRepository.findById(userId).orElseThrow(()-> new UserNotFoundException("User with given Id not found"));
		qualification.setUser(user);
		
		return qualificationRepository.save(qualification);
	}

	@Override
	public Qualification updateQualification(Long id, Qualification qualification) {
		Qualification dbQualification = qualificationRepository.findById(id)
				.orElseThrow(()-> new RuntimeException("Qualification not found with id: " + id));
		dbQualification.setDegree(qualification.getDegree());
		dbQualification.setInstitute(qualification.getInstitute());
		dbQualification.setQualificationType(qualification.getQualificationType());
		dbQualification.setStartDate(qualification.getStartDate());
		dbQualification.setEndDate(qualification.getEndDate());
		dbQualification.setUrl(qualification.getUrl());
		
		return qualificationRepository.save(dbQualification);
	}

	@Override
	public Qualification patchQualification(Long id, Qualification qualification) {
		Qualification existing = findQualificationById(id);
				if(qualification.getDegree() != null) {
					existing.setDegree(qualification.getDegree());
				}
				if(qualification.getInstitute() != null) {
					existing.setInstitute(qualification.getInstitute());
				}
				if(qualification.getQualificationType() != null) {
					existing.setQualificationType(qualification.getQualificationType());
				}
				if(qualification.getStartDate() != null) {
					existing.setStartDate(qualification.getStartDate());
				}
				if(qualification.getEndDate() != null) {
					existing.setEndDate(qualification.getEndDate());
				}
				if(qualification.getUrl() != null) {
					existing.setUrl(qualification.getUrl());
				}
				return qualificationRepository.save(existing);
			}

	@Override
	public void deleteQualification(Long id) {
		if(!qualificationRepository.existsById(id)) {
			throw new RuntimeException("Qualification not found with Id:" + id);
		}
		qualificationRepository.deleteById(id);
	}

}
