package com.capgemini.job_application.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.job_application.dtos.ApplicationInfoDto;
import com.capgemini.job_application.dtos.ApplicationViewDto;
import com.capgemini.job_application.entities.Application;
import com.capgemini.job_application.repositories.ApplicationRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ApplicationServiceImpl implements ApplicationService {

	private final ApplicationRepository applicationRepository;

	@Autowired
	public ApplicationServiceImpl(ApplicationRepository applicationRepository) {
		this.applicationRepository = applicationRepository;
	}

	@Override
	public List<Application> getAllApplication() {
		log.info("Fetching all applications");
		return applicationRepository.findAll();
	}

	@Override
	public Application getApplicationById(Long id) {
		log.info("Fetching application with ID: {}", id);
		return applicationRepository.findById(id).orElseThrow(() -> {
			log.error("Application not found with ID: {}", id);
			return new RuntimeException("Applicant not found with ID: " + id);
		});
	}

	@Override
	public Application createApplication(Application applicant) {
		log.info("Creating application for userId: {}", applicant.getUser());
		return applicationRepository.save(applicant);
	}

	@Override
	public Application updateApplication(Long id, Application applicant) {
		log.info("Updating application with ID: {}", id);
		Application existing = applicationRepository.findById(id).orElseThrow(() -> {
			log.error("Application not found with ID: {}", id);
			return new RuntimeException("User not found with ID: " + id);
		});

		existing.setJob(applicant.getJob());
		existing.setAppliedDate(existing.getAppliedDate());
		existing.setStatus(applicant.getStatus());
		existing.setUser(applicant.getUser());

		return applicationRepository.save(existing);
	}

	@Override
	public Application patchApplication(Long id, Application applicant) {
		log.info("Patching application with ID: {}", id);
		Application existing = applicationRepository.findById(id).orElseThrow(() -> {
			log.error("Application not found with ID: {}", id);
			return new RuntimeException("User not found with ID: " + id);
		});

		if (applicant.getJob() != null) {
			existing.setJob(applicant.getJob());
		}
		if (applicant.getAppliedDate() != null) {
			existing.setAppliedDate(applicant.getAppliedDate());
		}
		if (applicant.getStatus() != null) {
			existing.setStatus(applicant.getStatus());
		}
		if (applicant.getUser() != null) {
			existing.setUser(applicant.getUser());
		}

		return applicationRepository.save(existing);
	}

	@Override
	public void deleteApplication(Long id) {
		log.info("Deleting application with ID: {}", id);
		if (!applicationRepository.existsById(id)) {
			log.error("Cannot delete. Application not found with ID: {}", id);
			throw new RuntimeException("Applicant not found with ID: " + id);
		}
		applicationRepository.deleteById(id);
		log.info("Application deleted with ID: {}", id);
	}

	@Override
	public List<Application> findUserUserId(Long userId) {
		return applicationRepository.findUserUserId(userId);
	}

	@Override
	public List<ApplicationViewDto> findApplicationsByUserId(Long userId) {
		return applicationRepository.findApplicationsByUserId(userId);
	}

	@Override
	public List<ApplicationInfoDto> getAllApplicationInfo() {
		return applicationRepository.getAllApplicationDetails();
	}
}