package com.capgemini.job_application.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.job_application.entities.Qualification;
import com.capgemini.job_application.repositories.QualificationRepository;

@Service
public class QualificationServiceImpl implements QualificationService{

	private final QualificationRepository qualificationRepository;
	
	@Autowired
	public QualificationServiceImpl(QualificationRepository qualificationRepository) {
		super();
		this.qualificationRepository = qualificationRepository;
	}

	@Override
	public List<Qualification> getAllQualifications() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Qualification findQualificationById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Qualification createQualification(Qualification qualification) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Qualification updateQualification(Long id, Qualification qualification) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Qualification patchQualification(Long id, Qualification qualification) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteQualification(Long id) {
		// TODO Auto-generated method stub
		
	}

}
