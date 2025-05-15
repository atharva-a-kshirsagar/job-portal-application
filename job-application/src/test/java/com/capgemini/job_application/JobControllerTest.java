package com.capgemini.job_application;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Arrays;

import com.capgemini.job_application.controllers.JobController;
import com.capgemini.job_application.entities.Job;
import com.capgemini.job_application.services.JobService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

public class JobControllerTest {

	   private MockMvc mockMvc;

	    @Mock
	    private JobService jobService;

	    @InjectMocks
	    private JobController jobController;

	    private Job job;
	    private ObjectMapper objectMapper;

	    @BeforeEach
	    void setUp() {
	        MockitoAnnotations.openMocks(this);
	        mockMvc = MockMvcBuilders.standaloneSetup(jobController).build();
	        objectMapper = new ObjectMapper();

	        job = new Job(1L, 101L, "Developer", 80000.0, "Java Job", "Bangalore");
	    }

	    @Test
	    void testGetAllJobs() throws Exception {
	        when(jobService.getAllJobs()).thenReturn(Arrays.asList(job));

	        mockMvc.perform(get("/api/jobs"))
	                .andExpect(status().isOk())
	                .andExpect(jsonPath("$[0].jobTitle").value("Developer"));
	    }

	    @Test
	    void testGetJobById() throws Exception {
	        when(jobService.getJobById(1L)).thenReturn(job);

	        mockMvc.perform(get("/api/jobs/1"))
	                .andExpect(status().isOk())
	                .andExpect(jsonPath("$.jobTitle").value("Developer"));
	    }

	    @Test
	    void testCreateJob() throws Exception {
	        when(jobService.createJob(any(Job.class))).thenReturn(job);

	        mockMvc.perform(post("/api/jobs")
	                .contentType(MediaType.APPLICATION_JSON)
	                .content(objectMapper.writeValueAsString(job)))
	                .andExpect(status().isCreated())
	                .andExpect(jsonPath("$.jobTitle").value("Developer"));
	    }

	    @Test
	    void testUpdateJob() throws Exception {
	        when(jobService.updateJob(eq(1L), any(Job.class))).thenReturn(job);

	        mockMvc.perform(put("/api/jobs/1")
	                .contentType(MediaType.APPLICATION_JSON)
	                .content(objectMapper.writeValueAsString(job)))
	                .andExpect(status().isOk())
	                .andExpect(jsonPath("$.jobTitle").value("Developer"));
	    }

	    @Test
	    void testPatchJob() throws Exception {
	        when(jobService.patchJob(eq(1L), any(Job.class))).thenReturn(job);

	        mockMvc.perform(patch("/api/jobs/1")
	                .contentType(MediaType.APPLICATION_JSON)
	                .content(objectMapper.writeValueAsString(job)))
	                .andExpect(status().isOk())
	                .andExpect(jsonPath("$.jobTitle").value("Developer"));
	    }

	    @Test
	    void testDeleteJob() throws Exception {
	        doNothing().when(jobService).deleteJob(1L);

	        mockMvc.perform(delete("/api/jobs/1"))
	                .andExpect(status().isNoContent());
	    }
}
