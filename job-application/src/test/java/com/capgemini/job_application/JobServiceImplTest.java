package com.capgemini.job_application;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import com.capgemini.job_application.entities.Company;
import com.capgemini.job_application.entities.Job;
import com.capgemini.job_application.exceptions.JobNotFoundException;
import com.capgemini.job_application.repositories.JobRepository;
import com.capgemini.job_application.services.JobServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class JobServiceImplTest {

    @Mock
    private JobRepository jobRepository;

    @InjectMocks
    private JobServiceImpl jobService;

    private Job job1;
    private Job job2;
    private Company company;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        company = new Company();
        company.setCompanyId(1L);
        
        job1 = new Job();
        job1.setJobId(1L);
        job1.setCompany(company);
        job1.setJobTitle("Software Engineer");
        job1.setDescription("Job Description 1");
        job1.setSalary(50000.0);
        job1.setJobLocation("Pune");
        job1.setPostingDate(LocalDate.now());
        job1.setDeadlineDate(LocalDate.now().plusDays(10));

        job2 = new Job();
        job2.setJobId(2L);
        job2.setCompany(company);
        job2.setJobTitle("Senior Developer");
        job2.setDescription("Job Description 2");
        job2.setSalary(75000.0);
        job2.setJobLocation("Mumbai");
        job2.setPostingDate(LocalDate.now());
        job2.setDeadlineDate(LocalDate.now().plusDays(20));
    }

    @Test
    void testGetAllJobs() {
        when(jobRepository.findAll()).thenReturn(Arrays.asList(job1, job2));
        List<Job> jobs = jobService.getAllJobs();
        assertEquals(2, jobs.size());
        verify(jobRepository, times(1)).findAll();
    }

    @Test
    void testGetJobById_Success() {
        when(jobRepository.findById(1L)).thenReturn(Optional.of(job1));
        Job result = jobService.getJobById(1L);
        assertNotNull(result);
        assertEquals("Software Engineer", result.getJobTitle());
    }

    @Test
    void testGetJobById_NotFound() {
        when(jobRepository.findById(3L)).thenReturn(Optional.empty());
        assertThrows(JobNotFoundException.class, () -> jobService.getJobById(3L));
    }

    @Test
    void testCreateJob() {
        when(jobRepository.save(any(Job.class))).thenReturn(job1);
        Job created = jobService.createJob(job1);
        assertNotNull(created);
        verify(jobRepository, times(1)).save(any(Job.class));
    }

    @Test
    void testUpdateJob_Success() {
        when(jobRepository.findById(1L)).thenReturn(Optional.of(job1));
        when(jobRepository.save(any(Job.class))).thenReturn(job1);

        job1.setJobTitle("Updated Title");
        Job updated = jobService.updateJob(1L, job1);
        assertEquals("Updated Title", updated.getJobTitle());
    }

    @Test
    void testUpdateJob_NotFound() {
        when(jobRepository.findById(99L)).thenReturn(Optional.empty());
        assertThrows(JobNotFoundException.class, () -> jobService.updateJob(99L, job1));
    }

    @Test
    void testDeleteJob_Success() {
        when(jobRepository.existsById(1L)).thenReturn(true);
        jobService.deleteJob(1L);
        verify(jobRepository, times(1)).deleteById(1L);
    }

    @Test
    void testDeleteJob_NotFound() {
        when(jobRepository.existsById(2L)).thenReturn(false);
        assertThrows(JobNotFoundException.class, () -> jobService.deleteJob(2L));
    }

    @Test
    void testGetJobsByCompanyId() {
        when(jobRepository.findByCompanyCompanyId(1L)).thenReturn(Arrays.asList(job1, job2));
        List<Job> jobs = jobService.getJobsByCompanyId(1L);
        assertEquals(2, jobs.size());
    }

    @Test
    void testGetTop5JobsBySalary() {
        when(jobRepository.findTop5ByOrderBySalaryDesc()).thenReturn(Arrays.asList(job2, job1));
        List<Job> topJobs = jobService.getTop5JobsBySalary();
        assertEquals(2, topJobs.size());
        assertTrue(topJobs.get(0).getSalary() >= topJobs.get(1).getSalary());
    }
}