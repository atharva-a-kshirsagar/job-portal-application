package com.capgemini.job_application.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.capgemini.job_application.entities.Job;

public interface JobRepository extends JpaRepository<Job, Long> {

}
