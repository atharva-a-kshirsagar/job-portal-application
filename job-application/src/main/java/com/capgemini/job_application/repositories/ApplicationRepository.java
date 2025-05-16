package com.capgemini.job_application.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.capgemini.job_application.entities.Application;
import com.capgemini.job_application.entities.User;

public interface ApplicationRepository extends JpaRepository<Application, Long> {
	List<Application> findByUser(User user);

}
