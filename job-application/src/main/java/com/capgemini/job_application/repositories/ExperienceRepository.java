package com.capgemini.job_application.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.capgemini.job_application.entities.Experience;

public interface ExperienceRepository extends JpaRepository<Experience, Long> {
	List<Experience> findByUserId(Long userId);
    void deleteByUserId(Long userId);
}
