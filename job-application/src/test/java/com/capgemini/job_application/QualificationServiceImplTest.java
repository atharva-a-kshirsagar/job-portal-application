package com.capgemini.job_application;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import com.capgemini.job_application.entities.Qualification;
import com.capgemini.job_application.entities.User;
import com.capgemini.job_application.exceptions.UserNotFoundException;
import com.capgemini.job_application.repositories.QualificationRepository;
import com.capgemini.job_application.repositories.UserRepository;
import com.capgemini.job_application.services.QualificationServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class QualificationServiceImplTest {

    @Mock
    private QualificationRepository qualificationRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private QualificationServiceImpl qualificationService;

    private User user;
    private Qualification qualification;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);

        user = new User();
        user.setUserId(1L);

        qualification = new Qualification(1L, user, LocalDate.now(), LocalDate.now().plusYears(1), "Full-time",
                "https://example.com", "Test Institute", "B.Tech");
    }

    @Test
    public void testGetAllQualifications() {
        when(qualificationRepository.findAll()).thenReturn(Arrays.asList(qualification));
        List<Qualification> result = qualificationService.getAllQualifications();
        assertEquals(1, result.size());
    }

    @Test
    public void testFindQualificationById() {
        when(qualificationRepository.findById(1L)).thenReturn(Optional.of(qualification));
        Qualification result = qualificationService.findQualificationById(1L);
        assertNotNull(result);
    }

    @Test
    public void testCreateQualification_UserFound() {
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(qualificationRepository.save(any(Qualification.class))).thenReturn(qualification);

        Qualification result = qualificationService.createQualification(qualification);
        assertEquals("B.Tech", result.getDegree());
    }

    @Test
    public void testCreateQualification_UserNotFound() {
        when(userRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(UserNotFoundException.class, () -> qualificationService.createQualification(qualification));
    }

    @Test
    public void testUpdateQualification() {
        when(qualificationRepository.findById(1L)).thenReturn(Optional.of(qualification));
        when(qualificationRepository.save(any(Qualification.class))).thenReturn(qualification);

        qualification.setDegree("MBA");
        Qualification updated = qualificationService.updateQualification(1L, qualification);
        assertEquals("MBA", updated.getDegree());
    }

    @Test
    public void testDeleteQualification() {
        when(qualificationRepository.existsById(1L)).thenReturn(true);
        doNothing().when(qualificationRepository).deleteById(1L);
        qualificationService.deleteQualification(1L);
        verify(qualificationRepository, times(1)).deleteById(1L);
    }

    @Test
    public void testFindByUserId() {
        when(qualificationRepository.findByUserId(1L)).thenReturn(List.of(qualification));
        List<Qualification> list = qualificationService.findByUserId(1L);
        assertEquals(1, list.size());
    }
}