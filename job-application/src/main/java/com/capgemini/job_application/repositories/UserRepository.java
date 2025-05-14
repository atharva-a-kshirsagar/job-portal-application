package com.capgemini.job_application.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.capgemini.job_application.entities.User;

public interface UserRepository extends JpaRepository<User, Long>{

}
