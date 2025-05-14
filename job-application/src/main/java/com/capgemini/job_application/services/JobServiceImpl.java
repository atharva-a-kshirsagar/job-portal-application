package com.capgemini.job_application.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.job_application.entities.Job;
import com.capgemini.job_application.repositories.JobRepository;



@Service
public class JobServiceImpl implements JobService{

	private final JobRepository jobRepository;

	@Autowired
	public JobServiceImpl(JobRepository jobRepository) {
		this.jobRepository = jobRepository;
	}
	

	@Override
	public List<Job> getAllJobs() {
		return jobRepository.findAll();
	}
	
	@Override
	public Job getJobById(Long id) {
		return jobRepository.findById(id).orElseThrow(()->new RuntimeException("Job not found with id: "+id));
	}
	
	@Override
	public Job createJob(Job job) {
		return jobRepository.save(job);
	}

	@Override
	public Job updateJob(Long id, Job updated) {
		Job existing = jobRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Job not found with Id:" + id));
		existing.setCompanyId(updated.getCompanyId());
		existing.setJobTitle(updated.getJobTitle());
		existing.setDescription(updated.getDescription());
		existing.setSalary(updated.getSalary());
		existing.setJobLocation(updated.getJobLocation());
		return jobRepository.save(existing);

	}

	@Override
	public Job patchJob(Long id, Job patch) {
		Job existing = jobRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Booking not found with Id:" + id));
		;

		if (patch.getCompanyId() != null) {
			existing.setCompanyId(patch.getCompanyId());
			
		}
		if (patch.getJobTitle() != null) {
			existing.setJobTitle(patch.getJobTitle());
		}
		if (patch.getDescription() != null) {
			existing.setDescription(patch.getDescription());
		}
		if (patch.getSalary() != null) {
			existing.setSalary(patch.getSalary());
		}
		if (patch.getJobLocation() != null) {
			existing.setJobLocation(patch.getJobLocation());
		}
		return jobRepository.save(existing);
	}

	@Override
	public void deleteJob(Long id) {
		if (!jobRepository.existsById(id)) {
			throw new RuntimeException("Cannot Delete.Booking not found with ID:" + id);
		}
		jobRepository.deleteById(id);
	}
}
