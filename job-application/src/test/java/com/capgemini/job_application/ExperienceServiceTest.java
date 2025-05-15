package com.capgemini.job_application;

import com.capgemini.job_application.entities.Experience;
import com.capgemini.job_application.repositories.ExperienceRepository;
import com.capgemini.job_application.services.ExperienceServiceImpl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ExperienceServiceTest {

    @Mock
    private ExperienceRepository expRepo;

    @InjectMocks
    private ExperienceServiceImpl experienceService;

    private Experience exp;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        exp = new Experience(1L, 100L, "Developer", "TechCorp",
                LocalDate.of(2020, 1, 1), LocalDate.of(2022, 1, 1));
    }

    @Test
    void testGetExperienceByUserId() {
        List<Experience> experiences = Arrays.asList(exp);
        when(expRepo.findByUserId(100L)).thenReturn(experiences);

        List<Experience> result = experienceService.getExperienceByUser_id(100L);
        assertEquals(1, result.size());
        assertEquals("Developer", result.get(0).getRole());
    }

    @Test
    void testCreateExperience() {
        when(expRepo.save(any(Experience.class))).thenReturn(exp);

        Experience saved = experienceService.createExperience(exp);
        assertNotNull(saved);
        assertEquals("TechCorp", saved.getCompanyName());
    }

    @Test
    void testDeleteExperienceById() {
        doNothing().when(expRepo).deleteById(1L);
        experienceService.deleteExperienceById(1L);
        verify(expRepo, times(1)).deleteById(1L);
    }

    @Test
    void testDeleteAllExperiencesByUserId() {
        doNothing().when(expRepo).deleteByUserId(100L);
        experienceService.deleteAllExperiencesByUserId(100L);
        verify(expRepo, times(1)).deleteByUserId(100L);
    }

    @Test
    void testUpdateExperience() {
        Experience updated = new Experience(100L, "Senior Developer", "NewTech",
                LocalDate.of(2020, 1, 1), LocalDate.of(2024, 1, 1));

        when(expRepo.findById(1L)).thenReturn(Optional.of(exp));
        when(expRepo.save(any(Experience.class))).thenReturn(updated);

        Experience result = experienceService.updateExperience(1L, 100L, updated);

        assertEquals("Senior Developer", result.getRole());
        assertEquals("NewTech", result.getCompanyName());
        assertEquals(LocalDate.of(2024, 1, 1), result.getEndDate());
    }

    @Test
    void testUpdateExperienceNotFound() {
        when(expRepo.findById(99L)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () ->
                experienceService.updateExperience(99L, 100L, exp));

        assertEquals("Experience with that id not exist", exception.getMessage());
    }
}
