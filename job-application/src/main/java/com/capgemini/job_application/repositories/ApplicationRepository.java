package com.capgemini.job_application.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.capgemini.job_application.entities.Application;

public interface ApplicationRepository extends JpaRepository<Application, Long> {

}
