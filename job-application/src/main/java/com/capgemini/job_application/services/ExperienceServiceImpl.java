package com.capgemini.job_application.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.job_application.entities.Experience;
import com.capgemini.job_application.repositories.ExperienceRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ExperienceServiceImpl implements ExperienceService {
	ExperienceRepository expRepo;

	@Autowired
	public ExperienceServiceImpl(ExperienceRepository expRepo) {
		super();
		this.expRepo = expRepo;
	}

	@Override
	public List<Experience> getExperienceByUser_id(Long userId) {
        log.info("Fetching all experiences for user ID: {}", userId);
        List<Experience> experiences = expRepo.findByUserId(userId);
        log.debug("Found {} experiences for user ID: {}", experiences.size(), userId);
        return experiences;
    }

	@Override
	public Experience createExperience(Experience experience) {
        log.info("Creating new experience for user ID: {}", experience.getUserId());
        Experience savedExperience = expRepo.save(experience);
        log.debug("Experience created with ID: {}", savedExperience.getExperienceId());
        return savedExperience;
    }

	@Override
	public void deleteExperienceById(Long experienceId) {
        log.info("Deleting experience with ID: {}", experienceId);
        expRepo.deleteById(experienceId);
        log.debug("Experience with ID {} deleted successfully", experienceId);
    }

	@Override
	public void deleteAllExperiencesByUserId(Long userId) {
        log.info("Deleting all experiences for user ID: {}", userId);
        expRepo.deleteByUserId(userId);
        log.debug("All experiences for user ID {} deleted", userId);
    }

	@Override
	public Experience updateExperience(Long expId, Long userId, Experience updatedExperience) {
        log.info("Updating experience ID: {} for user ID: {}", expId, userId);
        Experience existingExp = expRepo.findById(expId)
            .orElseThrow(() -> {
                log.warn("Experience not found with ID: {}", expId);
                return new RuntimeException("Experience with that ID does not exist");
            });

        existingExp.setStartDate(updatedExperience.getStartDate());
        existingExp.setEndDate(updatedExperience.getEndDate());
        existingExp.setCompanyName(updatedExperience.getCompanyName());
        existingExp.setRole(updatedExperience.getRole());

        Experience saved = expRepo.save(existingExp);
        log.debug("Experience with ID {} updated successfully", saved.getExperienceId());
        return saved;
    }

	@Override
	 public List<Experience> getExpriences() {
        log.info("Fetching all experiences from the repository");
        List<Experience> experiences = expRepo.findAll();
        log.debug("Found {} total experiences", experiences.size());
        return experiences;
    }
}
