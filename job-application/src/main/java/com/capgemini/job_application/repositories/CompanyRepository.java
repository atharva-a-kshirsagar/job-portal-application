package com.capgemini.job_application.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.capgemini.job_application.entities.Company;

public interface CompanyRepository extends JpaRepository<Company, Long> {
	@Query(value="SELECT * FROM company WHERE user_id = ?1 ", nativeQuery= true)
	Optional<Company> findByUserId(Long userId);

}
