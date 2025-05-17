package com.capgemini.job_application.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.capgemini.job_application.entities.Job;
import com.capgemini.job_application.repositories.JobRepository;


public interface JobService {
	
	List<Job> getAllJobs();

	Job getJobById(Long id);

	Job createJob(Job job);

	Job updateJob(Long id, Job job);

	void deleteJob(Long id);
	
	public List<Job> getTop5JobsBySalary();
}
