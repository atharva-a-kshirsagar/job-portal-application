package com.capgemini.job_application.repositories;

import java.lang.annotation.Native;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.NativeQuery;
import org.springframework.data.jpa.repository.Query;

import com.capgemini.job_application.entities.Job;

public interface JobRepository extends JpaRepository<Job, Long> {

	@Query	(value = "SELECT * FROM job WHERE job_id = ?1", nativeQuery = true)
	List<Job> findByJobId(Long job_id);
	
	@Query(value = "SELECT * FROM job WHERE company_id = ?1", nativeQuery = true)
	List<Job> findByCompanyId(Long companyId);


}
