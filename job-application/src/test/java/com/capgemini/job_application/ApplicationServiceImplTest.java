package com.capgemini.job_application;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.capgemini.job_application.entities.Application;
import com.capgemini.job_application.repositories.ApplicationRepository;
import com.capgemini.job_application.services.ApplicationServiceImpl;

class ApplicationServiceImplTest {

    @Mock
    private ApplicationRepository applicationRepository;

    @InjectMocks
    private ApplicationServiceImpl applicationService;

    private Application application;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        application = new Application();
        application.setApplicationId(1L);
        application.setUserId(101L);
        application.setJobId(201L);
        application.setStatus("Pending");
        application.setAppliedDate(LocalDate.now());
    }

    @Test
    void testGetAllApplication() {
        List<Application> list = List.of(application);
        when(applicationRepository.findAll()).thenReturn(list);

        List<Application> result = applicationService.getAllApplication();

        assertEquals(1, result.size());
        assertEquals(application, result.get(0));
    }

    @Test
    void testGetApplicationById() {
        when(applicationRepository.findById(1L)).thenReturn(Optional.of(application));

        Application result = applicationService.getApplicationById(1L);

        assertEquals(1L, result.getApplicationId());
    }

    @Test
    void testCreateApplication() {
        when(applicationRepository.save(application)).thenReturn(application);

        Application result = applicationService.createApplication(application);

        assertNotNull(result);
        assertEquals(application.getUserId(), result.getUserId());
    }

    @Test
    void testUpdateApplication() {
        when(applicationRepository.findById(1L)).thenReturn(Optional.of(application));
        when(applicationRepository.save(any(Application.class))).thenReturn(application);

        Application updated = new Application();
        updated.setUserId(999L);
        updated.setJobId(888L);
        updated.setStatus("Approved");
        updated.setAppliedDate(LocalDate.now());

        Application result = applicationService.updateApplication(1L, updated);

        assertEquals("Approved", result.getStatus());
        assertEquals(999L, result.getUserId());
    }

    @Test
    void testPatchApplication() {
        when(applicationRepository.findById(1L)).thenReturn(Optional.of(application));
        when(applicationRepository.save(any(Application.class))).thenReturn(application);

        Application patch = new Application();
        patch.setStatus("Rejected");

        Application result = applicationService.patchApplication(1L, patch);

        assertEquals("Rejected", result.getStatus());
    }

    @Test
    void testDeleteApplication() {
        when(applicationRepository.existsById(1L)).thenReturn(true);
        doNothing().when(applicationRepository).deleteById(1L);

        assertDoesNotThrow(() -> applicationService.deleteApplication(1L));
        verify(applicationRepository, times(1)).deleteById(1L);
    }
}
