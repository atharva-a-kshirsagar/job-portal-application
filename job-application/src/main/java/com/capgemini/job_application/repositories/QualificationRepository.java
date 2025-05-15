package com.capgemini.job_application.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.capgemini.job_application.entities.Qualification;

public interface QualificationRepository extends JpaRepository<Qualification, Long>{

}
