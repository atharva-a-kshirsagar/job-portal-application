package com.capgemini.job_application.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.capgemini.job_application.entities.User;

public interface UserRepository extends JpaRepository<User, Long>{

	Optional<User> findByUserEmail(String email);

	Optional<User> findByUserNameOrUserEmail(String name, String email);

	Optional<User> findByUserName(String name);


	boolean existsByUserName(String name);

	boolean existsByUserEmail(String email);
}
