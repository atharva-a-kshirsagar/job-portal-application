package com.capgemini.job_application;
import com.capgemini.job_application.controllers.JobController;
import com.capgemini.job_application.entities.Company;
import com.capgemini.job_application.entities.Job;
import com.capgemini.job_application.services.JobService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.time.LocalDate;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class JobControllerTest {

    @Mock
    private JobService jobService;

    @InjectMocks
    private JobController jobController;

    private Job job;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        Company company = new Company();
        company.setCompanyId(1L);

        job = new Job();
        job.setJobId(1L);
        job.setCompany(company);
        job.setJobTitle("Software Engineer");
        job.setDescription("Full-stack Java Developer position");
        job.setJobLocation("Pune");
        job.setSalary(75000.0);
        job.setPostingDate(LocalDate.now());
        job.setDeadlineDate(LocalDate.now().plusDays(30));
    }

    @Test
    void testGetAllJobs() {
        List<Job> jobs = Arrays.asList(job);
        when(jobService.getAllJobs()).thenReturn(jobs);

        ResponseEntity<List<Job>> response = jobController.getAllJobs();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().size());
        verify(jobService, times(1)).getAllJobs();
    }

    @Test
    void testGetJobById() {
        when(jobService.getJobById(1L)).thenReturn(job);

        ResponseEntity<Job> response = jobController.getJob(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Software Engineer", response.getBody().getJobTitle());
        verify(jobService, times(1)).getJobById(1L);
    }

    @Test
    void testCreateJob() {
        when(jobService.createJob(any(Job.class))).thenReturn(job);

        ResponseEntity<Job> response = jobController.createJob(job, Mockito.mock(org.springframework.validation.BindingResult.class));

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(job.getJobId(), response.getBody().getJobId());
        verify(jobService, times(1)).createJob(any(Job.class));
    }

    @Test
    void testUpdateJob() {
        when(jobService.updateJob(eq(1L), any(Job.class))).thenReturn(job);

        ResponseEntity<Job> response = jobController.updateJob(1L, job, Mockito.mock(org.springframework.validation.BindingResult.class));

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Software Engineer", response.getBody().getJobTitle());
        verify(jobService, times(1)).updateJob(eq(1L), any(Job.class));
    }

    @Test
    void testDeleteJob() {
        doNothing().when(jobService).deleteJob(1L);

        ResponseEntity<Void> response = jobController.deleteJob(1L);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(jobService, times(1)).deleteJob(1L);
    }

    @Test
    void testGetJobsByCompanyId() {
        List<Job> jobs = List.of(job);
        when(jobService.getJobsByCompanyId(1L)).thenReturn(jobs);

        ResponseEntity<List<Job>> response = jobController.getJobsByCompany(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().size());
        verify(jobService, times(1)).getJobsByCompanyId(1L);
    }

    @Test
    void testGetTop5JobsBySalary() {
        List<Job> topJobs = List.of(job);
        when(jobService.getTop5JobsBySalary()).thenReturn(topJobs);

        List<Job> result = jobController.getTop5JobsBySalary();

        assertEquals(1, result.size());
        verify(jobService, times(1)).getTop5JobsBySalary();
    }
}