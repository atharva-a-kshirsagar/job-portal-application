package com.capgemini.job_application;
import com.capgemini.job_application.controllers.ExperienceController;
import com.capgemini.job_application.entities.Experience;
import com.capgemini.job_application.entities.User;
import com.capgemini.job_application.services.ExperienceService;
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

class ExperienceControllerTest {

    @Mock
    private ExperienceService experienceService;

    @InjectMocks
    private ExperienceController experienceController;

    private Experience experience;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        User user = new User();
        user.setUserId(100L);
        experience = new Experience();
        experience.setExperienceId(1L);
        experience.setUser(user);
        experience.setRole("Developer");
        experience.setCompanyName("TechCorp");
        experience.setStartDate(LocalDate.of(2020, 1, 1));
        experience.setEndDate(LocalDate.of(2022, 1, 1));
    }

    @Test
    void testGetExperienceByUserId() {
        List<Experience> experiences = Arrays.asList(experience);
        when(experienceService.getExperienceByUserId(100L)).thenReturn(experiences);

        ResponseEntity<List<Experience>> response = experienceController.getExperienceByUserId(100L);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).hasSize(1);
        assertThat(response.getBody().get(0).getRole()).isEqualTo("Developer");
    }

    @Test
    void testGetExperiences() {
        List<Experience> experiences = Arrays.asList(experience);
        when(experienceService.getExpriences()).thenReturn(experiences);

        ResponseEntity<List<Experience>> response = experienceController.getExperiences();

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).hasSize(1);
        assertThat(response.getBody().get(0).getCompanyName()).isEqualTo("TechCorp");
    }

    @Test
    void testCreateExperience() {
        when(experienceService.createExperience(any(Experience.class))).thenReturn(experience);

        BindingResult bindingResult = mock(BindingResult.class);
        when(bindingResult.hasErrors()).thenReturn(false);

        ResponseEntity<Experience> response = experienceController.createExperience(experience, bindingResult);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getExperienceId()).isEqualTo(1L);
    }

    @Test
    void testDeleteExperienceById() {
        doNothing().when(experienceService).deleteExperienceById(1L);

        ResponseEntity<Void> response = experienceController.deleteExperienceById(1L);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    }

    @Test
    void testDeleteAllExperiences() {
        doNothing().when(experienceService).deleteAllExperiencesByUserId(101L);

        ResponseEntity<Void> response = experienceController.deleteAllExperiences(101L);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    }

    @Test
    void testUpdateExperience() {
        User user = new User();
        user.setUserId(100L);
        Experience updatedInput = new Experience();
        updatedInput.setUser(user);
        updatedInput.setRole("Senior Developer");
        updatedInput.setCompanyName("NewTech");
        updatedInput.setStartDate(LocalDate.of(2020, 1, 1));
        updatedInput.setEndDate(LocalDate.of(2024, 1, 1));

        Experience updatedOutput = new Experience();
        updatedOutput.setExperienceId(1L);
        updatedOutput.setUser(user);
        updatedOutput.setRole("Senior Developer");
        updatedOutput.setCompanyName("NewTech");
        updatedOutput.setStartDate(LocalDate.of(2020, 1, 1));
        updatedOutput.setEndDate(LocalDate.of(2024, 1, 1));

        when(experienceService.updateExperience(eq(1L), eq(100L), any(Experience.class)))
                .thenReturn(updatedOutput);

        BindingResult bindingResult = mock(BindingResult.class);
        when(bindingResult.hasErrors()).thenReturn(false);

        ResponseEntity<Experience> response = experienceController.updateExperience(1L, 100L, updatedInput, bindingResult);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getRole()).isEqualTo("Senior Developer");
        assertThat(response.getBody().getCompanyName()).isEqualTo("NewTech");
    }
}