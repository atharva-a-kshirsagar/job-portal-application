package com.capgemini.job_application.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.capgemini.job_application.entities.Job;

public interface JobRepository extends JpaRepository<Job, Long> {

	@Query	(value = "SELECT * FROM job WHERE job_id = ?1", nativeQuery = true)
	List<Job> findByJobId(Long job_id);

	List<Job> findByCompanyCompanyId(Long companyId);
	
    @Query("SELECT j.company.companyDomain, COUNT(j) FROM Job j WHERE j.company.user.userId = :userId GROUP BY j.company.companyDomain")
    List<Object[]> countJobsByCompanyDomainForUser(Long userId);

}
