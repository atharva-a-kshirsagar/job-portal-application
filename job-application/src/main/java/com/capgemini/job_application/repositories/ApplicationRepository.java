package com.capgemini.job_application.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.NativeQuery;
import org.springframework.data.jpa.repository.Query;

import com.capgemini.job_application.dtos.ApplicationViewDto;
import com.capgemini.job_application.entities.Application;
import com.capgemini.job_application.entities.User;

public interface ApplicationRepository extends JpaRepository<Application, Long> {
	List<Application> findByUser(User user);
	
	@Query(value = "SELECT * FROM application a WHERE a.user_id = ?1", nativeQuery = true)
	List<Application> findUserUserId(Long userId);
	@Query(value = "SELECT a.application_id AS applicationId, a.status AS status, a.applied_date AS appliedDate, j.job_id AS jobId, j.job_title AS jobTitle, c.company_name AS companyName, j.job_location AS jobLocation, j.salary AS salary FROM application a JOIN job j ON a.job_id = j.job_id JOIN company c ON j.company_id = c.company_id WHERE a.user_id = ?1", nativeQuery = true)
	List<ApplicationViewDto> findApplicationsByUserId(Long userId);
	
	@Query(value = "SELECT a.* FROM application a JOIN job j ON a.job_id = j.job_id WHERE j.company_id = ?1", nativeQuery = true)
	List<Application> findByCompanyId(Long companyId);


//	@NativeQuery("select a.job_id ,COUNT(a.job_id) from application join job b where j.company_id = ?1 a group by a.job_id ")
//	List<Object[]> findApplicationByJob(Long companyId);
	
	@Query(value = "SELECT a.job_id, COUNT(a.job_id) FROM application a JOIN job j ON a.job_id = j.job_id WHERE j.company_id = ?1 GROUP BY a.job_id", nativeQuery = true)
	List<Object[]> findApplicationByJob(Long companyId);

	
	@Query(value = "SELECT u.gender, COUNT(*) FROM application a JOIN job j ON a.job_id = j.job_id JOIN user u ON a.user_id = u.user_id WHERE j.company_id = ?1 GROUP BY u.gender", nativeQuery = true)
	List<Object[]> getGenderCounts(Long companyId);


}
