package com.capgemini.job_application.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.capgemini.job_application.entities.Application;
import com.capgemini.job_application.entities.User;

public interface ApplicationRepository extends JpaRepository<Application, Long> {
	List<Application> findByUser(User user);
	
	 // 1. Applications by Location
	@Query("SELECT a.job.jobLocation, COUNT(a) FROM Application a WHERE a.user.userId = :userId GROUP BY a.job.jobLocation")
	List<Object[]> countApplicationsByLocationForUser(Long userId);


	@Query("SELECT a.job.jobTitle, COUNT(a) FROM Application a WHERE a.user.userId = :userId GROUP BY a.job.jobTitle")
	List<Object[]> countApplicationsByJobTitleForUser(Long userId);


}
