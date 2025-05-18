package com.capgemini.job_application;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.http.HttpStatus.*;
import java.time.LocalDate;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import com.capgemini.job_application.controllers.ApplicationController;
import com.capgemini.job_application.dtos.ApplicationViewDto;
import com.capgemini.job_application.entities.Application;
import com.capgemini.job_application.entities.Job;
import com.capgemini.job_application.entities.User;
import com.capgemini.job_application.services.ApplicationService;

public class ApplicationControllerTest {

    @Mock
    private ApplicationService applicationService;

    @InjectMocks
    private ApplicationController applicationController;

    @Mock
    private BindingResult bindingResult;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    private Application createSampleApplication() {
        User user = new User();
        user.setUserId(1L);
        Job job = new Job();
        job.setJobId(10L);
        return new Application(100L, user, job, LocalDate.now(), "Pending");
    }

    @Test
    void testCreateApplication_Success() {
        Application application = createSampleApplication();
        when(bindingResult.hasErrors()).thenReturn(false);
        when(applicationService.createApplication(application)).thenReturn(application);

        ResponseEntity<Application> response = applicationController.createApplication(application, bindingResult);

        assertEquals(CREATED, response.getStatusCode());
        assertEquals(application, response.getBody());
    }

    @Test
    void testCreateApplication_ValidationError() {
        Application application = createSampleApplication();
        when(bindingResult.hasErrors()).thenReturn(true);
        when(bindingResult.getFieldErrors()).thenReturn(
            List.of(new FieldError("application", "status", "Status is mandatory"))
        );

        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () ->
            applicationController.createApplication(application, bindingResult)
        );

        assertTrue(thrown.getMessage().contains("Status is mandatory"));
    }

    @Test
    void testGetAllApplications() {
        List<Application> apps = List.of(createSampleApplication());
        when(applicationService.getAllApplication()).thenReturn(apps);

        ResponseEntity<List<Application>> response = applicationController.getAllApplications();

        assertEquals(OK, response.getStatusCode());
        assertEquals(apps, response.getBody());
    }

    @Test
    void testGetApplicationById() {
        Application app = createSampleApplication();
        when(applicationService.getApplicationById(100L)).thenReturn(app);

        ResponseEntity<Application> response = applicationController.getApplicationById(100L);

        assertEquals(OK, response.getStatusCode());
        assertEquals(app, response.getBody());
    }

    @Test
    void testUpdateApplication() {
        Application application = createSampleApplication();
        when(bindingResult.hasErrors()).thenReturn(false);
        when(applicationService.updateApplication(100L, application)).thenReturn(application);

        ResponseEntity<Application> response = applicationController.updateApplication(100L, application, bindingResult);

        assertEquals(OK, response.getStatusCode());
        assertEquals(application, response.getBody());
    }

    @Test
    void testPatchApplication() {
        Application application = createSampleApplication();
        when(bindingResult.hasErrors()).thenReturn(false);
        when(applicationService.patchApplication(100L, application)).thenReturn(application);

        ResponseEntity<Application> response = applicationController.patchApplication(100L, application, bindingResult);

        assertEquals(OK, response.getStatusCode());
        assertEquals(application, response.getBody());
    }

    @Test
    void testDeleteApplication() {
        doNothing().when(applicationService).deleteApplication(100L);

        ResponseEntity<Void> response = applicationController.deleteApplication(100L);

        assertEquals(OK, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    void testFindUserUserId() {
        List<Application> apps = List.of(createSampleApplication());
        when(applicationService.findUserUserId(1L)).thenReturn(apps);

        ResponseEntity<List<Application>> response = applicationController.findUserUserId(1L);

        assertEquals(OK, response.getStatusCode());
        assertEquals(apps, response.getBody());
    }

    @Test
    void testFindApplicationsByUserId() {
        ApplicationViewDto dto = new ApplicationViewDto();
        dto.setJobTitle("Java Developer");
        dto.setStatus("Pending");

        List<ApplicationViewDto> dtos = List.of(dto);
        when(applicationService.findApplicationsByUserId(1L)).thenReturn(dtos);

        ResponseEntity<List<ApplicationViewDto>> response = applicationController.findApplicationsByUserId(1L);

        assertEquals(OK, response.getStatusCode());
        assertEquals(dtos, response.getBody());
    }
}