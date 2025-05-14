package com.capgemini.job_application;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.capgemini.job_application.controllers.ApplicationController;
import com.capgemini.job_application.entities.Application;
import com.capgemini.job_application.services.ApplicationService;

public class ApplicationControllerTest {
	 @Mock
	    private ApplicationService applicationService;

	    @InjectMocks
	    private ApplicationController applicationController;

	    private Application sampleApplication;

	    @BeforeEach
	    void setUp() {
	        MockitoAnnotations.openMocks(this);

	        sampleApplication = new Application();
	        sampleApplication.setApplicationId(1L);
	        sampleApplication.setJobId(101L);
	        sampleApplication.setUserId(501L);
	        sampleApplication.setAppliedDate(LocalDate.now());
	        sampleApplication.setStatus("Pending");
	    }

	    @Test
	    void testCreateUser() {
	        when(applicationService.createApplication(any(Application.class))).thenReturn(sampleApplication);

	        ResponseEntity<Application> response = applicationController.createUser(sampleApplication);

	        assertEquals(HttpStatus.CREATED, response.getStatusCode());
	        assertEquals(sampleApplication, response.getBody());
	    }

	    @Test
	    void testGetAllApplications() {
	        List<Application> apps = Arrays.asList(sampleApplication);
	        when(applicationService.getAllApplication()).thenReturn(apps);

	        ResponseEntity<List<Application>> response = applicationController.getApplicant();

	        assertEquals(HttpStatus.FOUND, response.getStatusCode());
	        assertEquals(1, response.getBody().size());
	    }

	    @Test
	    void testGetUserById() {
	        when(applicationService.getApplicationById(1L)).thenReturn(sampleApplication);

	        ResponseEntity<Application> response = applicationController.getUserById(1L);

	        assertEquals(HttpStatus.FOUND, response.getStatusCode());
	        assertEquals(sampleApplication, response.getBody());
	    }

	    @Test
	    void testUpdateUser() {
	        when(applicationService.updateApplication(eq(1L), any(Application.class))).thenReturn(sampleApplication);

	        ResponseEntity<Application> response = applicationController.updateUser(1L, sampleApplication);

	        assertEquals(HttpStatus.OK, response.getStatusCode());
	        assertEquals(sampleApplication, response.getBody());
	    }

	    @Test
	    void testPatchUser() {
	        when(applicationService.patchApplication(eq(1L), any(Application.class))).thenReturn(sampleApplication);

	        ResponseEntity<Application> response = applicationController.patchUser(1L, sampleApplication);

	        assertEquals(HttpStatus.OK, response.getStatusCode());
	        assertEquals(sampleApplication, response.getBody());
	    }

	    @Test
	    void testDeleteUser() {
	        doNothing().when(applicationService).deleteApplication(1L);

	        ResponseEntity<Void> response = applicationController.deleteUser(1L);

	        assertEquals(HttpStatus.OK, response.getStatusCode());
	        assertNull(response.getBody());
	    }
}
