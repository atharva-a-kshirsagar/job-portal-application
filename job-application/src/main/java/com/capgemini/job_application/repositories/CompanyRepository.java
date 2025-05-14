package com.capgemini.job_application.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.capgemini.job_application.entities.Company;

public interface CompanyRepository extends JpaRepository<Company, Long> {

}
