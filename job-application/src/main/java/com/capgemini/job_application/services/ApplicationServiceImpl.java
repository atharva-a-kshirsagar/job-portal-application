package com.capgemini.job_application.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.job_application.entities.Application;
import com.capgemini.job_application.repositories.ApplicationRepository;

@Service
public class ApplicationServiceImpl implements ApplicationService {

	ApplicationRepository applicationRepository;
	
	@Autowired
	public ApplicationServiceImpl(ApplicationRepository applicationRepository) {
		super();
		this.applicationRepository = applicationRepository;
	}

	@Override
	public List<Application> getAllApplication() {
		// TODO Auto-generated method stub
		return applicationRepository.findAll();
	}

	@Override
	public Application getApplicationById(Long id) {
		// TODO Auto-generated method stub
		return applicationRepository.findById(id).orElseThrow(() -> new RuntimeException("Applicant not Found with id : " + id));
	}

	@Override
	public Application createApplication(Application applicant) {
		// TODO Auto-generated method stub
		return applicationRepository.save(applicant);
	}

	@Override
	public Application updateApplication(Long id, Application applicant) {
		// TODO Auto-generated method stub
		Application existing = applicationRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("User not Found with id : " + id));
		existing.setJobId(applicant.getJobId());
		existing.setAppliedDate(applicant.getAppliedDate());
		existing.setStatus(applicant.getStatus());
		existing.setUserId(applicant.getUserId());
		return applicationRepository.save(existing);
	}

	@Override
	public Application patchApplication(Long id, Application applicant) {
		// TODO Auto-generated method stub
		Application existing = applicationRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("User not Found with id : " + id));

		if (applicant.getJobId() != null) {
			existing.setJobId(applicant.getJobId());
		}
		if (applicant.getAppliedDate() != null) {
			existing.setAppliedDate(applicant.getAppliedDate());
		}
		if (applicant.getStatus() != null) {
			existing.setStatus(applicant.getStatus());
		}
		if (applicant.getUserId() != null) {
			existing.setUserId(applicant.getUserId());
		} 

		return applicationRepository.save(existing);
	}

	@Override
	public void deleteApplication(Long id) {
		// TODO Auto-generated method stub
		if (!applicationRepository.existsById(id)) {
			throw new RuntimeException("Applicant not Found with id : " + id);
		}
		applicationRepository.deleteById(id);
	}
	
}
