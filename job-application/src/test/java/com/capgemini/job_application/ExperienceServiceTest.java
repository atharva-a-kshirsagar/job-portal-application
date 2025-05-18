package com.capgemini.job_application;
import com.capgemini.job_application.entities.Experience;
import com.capgemini.job_application.entities.User;
import com.capgemini.job_application.repositories.ExperienceRepository;
import com.capgemini.job_application.repositories.UserRepository;
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
import static org.mockito.Mockito.*;

public class ExperienceServiceTest {

    @Mock
    private ExperienceRepository experienceRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private ExperienceServiceImpl experienceService;

    private User testUser;
    private Experience exp1, exp2;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        Experience newExp = new Experience(
        	    null, // experienceId
        	    testUser, // user
        	    "Analyst", // role
        	    "PQR Inc", // companyName
        	    LocalDate.now().minusYears(2), // startDate
        	    LocalDate.now() // endDate
        	);

        exp1 = new Experience(101L, testUser, "Developer", "ABC Corp", LocalDate.now().minusYears(3), LocalDate.now().minusYears(1));
        exp2 = new Experience(102L, testUser, "Senior Developer", "XYZ Ltd", LocalDate.now().minusYears(1), LocalDate.now());
    }

    @Test
    void getExperienceByUserId() {
        when(experienceRepository.findByUserId(1L)).thenReturn(Arrays.asList(exp1, exp2));

        List<Experience> experiences = experienceService.getExperienceByUserId(1L);

        assertNotNull(experiences);
        assertEquals(2, experiences.size());
        assertEquals("Developer", experiences.get(0).getRole());
        assertEquals("Senior Developer", experiences.get(1).getRole());
        verify(experienceRepository, times(1)).findByUserId(1L);
    }

    @Test
    void getExperienceByUserId_WhenNoExperiencesFoundForUser() {
        when(experienceRepository.findByUserId(2L)).thenReturn(List.of());

        List<Experience> experiences = experienceService.getExperienceByUserId(2L);

        assertNotNull(experiences);
        assertTrue(experiences.isEmpty());
        verify(experienceRepository, times(1)).findByUserId(2L);
    }

   
    @Test
    void createExperience_ShouldThrowException_WhenUserDoesNotExist() {
        User nonExistingUser = new User(99L, "unknown", "pass", "Unknown", "User", "unknown@example.com", null, null, null, null, null, null, null, null);
        Experience newExp = new Experience(
                null,
                nonExistingUser,
                "Analyst",
                "PQR Inc",
                LocalDate.now().minusYears(2),
                LocalDate.now().minusYears(0)
        );
        when(userRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> experienceService.createExperience(newExp));
        verify(userRepository, times(1)).findById(99L);
        verify(experienceRepository, never()).save(any(Experience.class));
    }

    @Test
    void deleteExperienceById() {
        doNothing().when(experienceRepository).deleteById(101L);

        experienceService.deleteExperienceById(101L);

        verify(experienceRepository, times(1)).deleteById(101L);
    }

    @Test
    void deleteAllExperiencesByUserId() {
        when(experienceRepository.findByUserId(1L)).thenReturn(Arrays.asList(exp1, exp2));
        doNothing().when(experienceRepository).deleteAll(Arrays.asList(exp1, exp2));

        experienceService.deleteAllExperiencesByUserId(1L);

        verify(experienceRepository, times(1)).findByUserId(1L);
        verify(experienceRepository, times(1)).deleteAll(Arrays.asList(exp1, exp2));
    }

   


    @Test
    void updateExperience() {
        Experience updatedExp = new Experience(null, testUser, "Lead Developer", "New Horizons", LocalDate.now().minusYears(4), LocalDate.now().minusYears(2));
        when(experienceRepository.findById(999L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> experienceService.updateExperience(999L, 1L, updatedExp));
        verify(experienceRepository, times(1)).findById(999L);
        verify(experienceRepository, never()).save(any(Experience.class));
    }

    @Test
    void getExpriences() {
        when(experienceRepository.findAll()).thenReturn(Arrays.asList(exp1, exp2));

        List<Experience> allExperiences = experienceService.getExpriences();

        assertNotNull(allExperiences);
        assertEquals(2, allExperiences.size());
        verify(experienceRepository, times(1)).findAll();
    }
}