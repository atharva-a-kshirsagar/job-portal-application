package com.capgemini.job_application.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.job_application.entities.Experience;
import com.capgemini.job_application.repositories.ExperienceRepository;

@Service
public class ExperienceServiceImpl implements ExperienceService {
	ExperienceRepository expRepo;

	@Autowired
	public ExperienceServiceImpl(ExperienceRepository expRepo) {
		super();
		this.expRepo = expRepo;
	}

	@Override
	public List<Experience> getExperienceByUser_id(Long user_id) {
		return expRepo.findByUserId(user_id);
	}

	@Override
	public Experience createExperience(Experience experience) {
		return expRepo.save(experience);
	}

	@Override
	public void deleteExperienceById(Long experienceId) {
		expRepo.deleteById(experienceId);

	}

	@Override
	public void deleteAllExperiencesByUserId(Long userId) {
		expRepo.deleteByUserId(userId);

	}

	@Override
	public Experience updateExperience(Long expId, Long userId, Experience updatedExperience) {
		Experience existingExp = expRepo.findById(expId)
				.orElseThrow(() -> new RuntimeException("Experience with that id not exist"));

		existingExp.setStartDate(updatedExperience.getStartDate());
		existingExp.setEndDate(updatedExperience.getEndDate());
		existingExp.setCompanyName(updatedExperience.getCompanyName());
		existingExp.setRole(updatedExperience.getRole());
		return expRepo.save(existingExp);
	}

	@Override
	public List<Experience> getExpriences() {
		return expRepo.findAll();
	}
}
