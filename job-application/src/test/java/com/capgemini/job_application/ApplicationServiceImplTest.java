package com.capgemini.job_application;
import com.capgemini.job_application.entities.Application;
import com.capgemini.job_application.entities.Job;
import com.capgemini.job_application.entities.User;
import com.capgemini.job_application.repositories.ApplicationRepository;
import com.capgemini.job_application.services.ApplicationServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.time.LocalDate;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ApplicationServiceImplTest {

    private ApplicationRepository applicationRepository;
    private ApplicationServiceImpl applicationService;

    private Application application;

    @BeforeEach
    void setup() {
        applicationRepository = mock(ApplicationRepository.class);
        applicationService = new ApplicationServiceImpl(applicationRepository);

        User user = new User();
        user.setUserId(1L);

        Job job = new Job();
        job.setJobId(1L);

        application = new Application(1L, user, job, LocalDate.now(), "Pending");
    }

    @Test
    void testCreateApplication() {
        when(applicationRepository.save(application)).thenReturn(application);
        Application created = applicationService.createApplication(application);
        assertEquals("Pending", created.getStatus());
    }

    @Test
    void testGetAllApplications() {
        when(applicationRepository.findAll()).thenReturn(List.of(application));
        List<Application> list = applicationService.getAllApplication();
        assertEquals(1, list.size());
    }

    @Test
    void testGetApplicationById() {
        when(applicationRepository.findById(1L)).thenReturn(Optional.of(application));
        Application found = applicationService.getApplicationById(1L);
        assertNotNull(found);
        assertEquals(1L, found.getApplicationId());
    }

    @Test
    void testUpdateApplication() {
        when(applicationRepository.findById(1L)).thenReturn(Optional.of(application));
        when(applicationRepository.save(application)).thenReturn(application);

        Application updated = applicationService.updateApplication(1L, application);
        assertEquals("Pending", updated.getStatus());
    }

    @Test
    void testDeleteApplication() {
        when(applicationRepository.existsById(1L)).thenReturn(true);
        doNothing().when(applicationRepository).deleteById(1L);

        assertDoesNotThrow(() -> applicationService.deleteApplication(1L));
        verify(applicationRepository, times(1)).deleteById(1L);
    }

    @Test
    void testPatchApplication() {
        Application patchData = new Application();
        patchData.setStatus("Selected");

        when(applicationRepository.findById(1L)).thenReturn(Optional.of(application));
        when(applicationRepository.save(Mockito.any())).thenReturn(application);

        Application patched = applicationService.patchApplication(1L, patchData);
        assertEquals("Selected", patched.getStatus());
    }
}