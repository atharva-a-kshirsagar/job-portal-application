package com.capgemini.job_application;

import com.capgemini.job_application.controllers.QualificationController;
import com.capgemini.job_application.entities.Qualification;
import com.capgemini.job_application.entities.User;
import com.capgemini.job_application.services.QualificationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class QualificationControllerTest {

    @Mock
    private QualificationService qualificationService;

    @InjectMocks
    private QualificationController qualificationController;

    private Qualification qualification;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        User user = new User();
        user.setUserId(100L);
        qualification = new Qualification();
        qualification.setQualificationId(1L);
        qualification.setUser(user);
        qualification.setDegree("B.Tech");
        qualification.setInstitute("ABC Institute");
        qualification.setQualificationType("Graduation");
        qualification.setStartDate(LocalDate.of(2020, 1, 1));
        qualification.setEndDate(LocalDate.of(2024, 1, 1));
        qualification.setUrl("http://example.com");
    }

    @Test
    void testGetAllQualifications() {
        List<Qualification> list = Arrays.asList(qualification);
        when(qualificationService.getAllQualifications()).thenReturn(list);

        ResponseEntity<List<Qualification>> response = qualificationController.getAllQualifications();

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).hasSize(1);
        assertThat(response.getBody().get(0).getDegree()).isEqualTo("B.Tech");
    }

    @Test
    void testFindQualificationById() {
        when(qualificationService.findQualificationById(1L)).thenReturn(qualification);

        ResponseEntity<Qualification> response = qualificationController.findQualificationById(1L);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getQualificationId()).isEqualTo(1L);
        assertThat(response.getBody().getDegree()).isEqualTo("B.Tech");
    }

    @Test
    void testCreateQualification() {
        when(qualificationService.createQualification(any(Qualification.class))).thenReturn(qualification);

        // Mock BindingResult with no errors
        BindingResult bindingResult = mock(BindingResult.class);
        when(bindingResult.hasErrors()).thenReturn(false);

        ResponseEntity<Qualification> response = qualificationController.createQualification(qualification, bindingResult);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getDegree()).isEqualTo("B.Tech");
    }

    @Test
    void testUpdateQualification() {
        Qualification updated = new Qualification();
        updated.setQualificationId(1L);
        updated.setDegree("M.Tech");
        updated.setInstitute("XYZ Institute");
        updated.setQualificationType("Post-Graduation");
        updated.setStartDate(LocalDate.of(2024, 1, 1));
        updated.setEndDate(LocalDate.of(2026, 1, 1));
        updated.setUrl("http://xyz.com");
        updated.setUser(new User());

        when(qualificationService.updateQualification(eq(1L), any(Qualification.class))).thenReturn(updated);

        BindingResult bindingResult = mock(BindingResult.class);
        when(bindingResult.hasErrors()).thenReturn(false);

        ResponseEntity<Qualification> response = qualificationController.updateQualification(1L, updated, bindingResult);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getDegree()).isEqualTo("M.Tech");
        assertThat(response.getBody().getInstitute()).isEqualTo("XYZ Institute");
    }

    @Test
    void testPatchQualification() {
        Qualification patched = new Qualification();
        patched.setQualificationId(1L);
        patched.setDegree("PhD");
        patched.setInstitute("IIT");
        patched.setQualificationType("Doctorate");
        patched.setStartDate(LocalDate.of(2026, 1, 1));
        patched.setEndDate(LocalDate.of(2030, 1, 1));
        patched.setUrl("http://iit.com");
        patched.setUser(new User());

        when(qualificationService.patchQualification(eq(1L), any(Qualification.class))).thenReturn(patched);

        BindingResult bindingResult = mock(BindingResult.class);
        when(bindingResult.hasErrors()).thenReturn(false);

        ResponseEntity<Qualification> response = qualificationController.patchQualification(1L, patched, bindingResult);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getDegree()).isEqualTo("PhD");
        assertThat(response.getBody().getInstitute()).isEqualTo("IIT");
    }

    @Test
    void testDeleteQualification() {
        doNothing().when(qualificationService).deleteQualification(1L);

        ResponseEntity<Void> response = qualificationController.deleteQualification(1L);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    }

    @Test
    void testGetQualificationsByUserId() {
        List<Qualification> list = Arrays.asList(qualification);
        when(qualificationService.findByUserId(100L)).thenReturn(list);

        ResponseEntity<List<Qualification>> response = qualificationController.getQualificationsByUserId(100L);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).hasSize(1);
        assertThat(response.getBody().get(0).getUser().getUserId()).isEqualTo(100L);
    }

    @Test
    void testDeleteQualificationsByUserId() {
        doNothing().when(qualificationService).deleteByUserId(100L);

        ResponseEntity<String> response = qualificationController.deleteQualificationsByUserId(100L);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).contains("All qualifications deleted for userId: 100");
    }
}