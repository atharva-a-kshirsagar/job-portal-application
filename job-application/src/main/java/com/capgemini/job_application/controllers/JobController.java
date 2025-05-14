package com.capgemini.job_application.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.job_application.entities.Job;
import com.capgemini.job_application.services.JobService;



@CrossOrigin(origins ="*")
@RestController
@RequestMapping("api/jobs")
public class JobController {

	private final JobService jobService;

	@Autowired
	public JobController(JobService jobService) {
		this.jobService = jobService;
	}
	@GetMapping
	public ResponseEntity<List<Job>> getAllJobs() {
		List<Job> jobs = jobService.getAllJobs();
		return ResponseEntity.status(HttpStatus.OK).body(jobs);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Job> getJob(@PathVariable Long id) {
		Job job = jobService.getJobById(id);
		return ResponseEntity.status(HttpStatus.OK).body(job);

		
	}

	@PostMapping
	public ResponseEntity<Job> createJob(@RequestBody Job job) {
		Job saved = jobService.createJob(job);
		return ResponseEntity.status(HttpStatus.CREATED).location(URI.create("/api/bookings/" + saved.getJobId()))
				.body(saved);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Job> updateJob(@PathVariable Long id, @RequestBody Job newJob) {
		Job updated = jobService.updateJob(id, newJob);
		return ResponseEntity.status(HttpStatus.OK).body(updated);
	}

	@PatchMapping("/{id}")
	public ResponseEntity<Job> patchJob(@PathVariable Long id, @RequestBody Job patch) {
		Job updated = jobService.patchJob(id, patch);
		return ResponseEntity.status(HttpStatus.OK).body(updated);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteJob(@PathVariable Long id) {
		jobService.deleteJob(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		
	}
}
