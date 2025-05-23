package com.capgemini.job_application;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import com.capgemini.job_application.controllers.SkillController;
import com.capgemini.job_application.entities.Skill;
import com.capgemini.job_application.services.SkillService;

class SkillControllerTest {

    @InjectMocks
    private SkillController skillController;

    @Mock
    private SkillService skillService;

    @Mock
    private BindingResult bindingResult;

    private Skill skill;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        skill = new Skill(1L, "Java");
    }

    @Test
    void testCreateSkill_ValidInput() {
        when(bindingResult.hasErrors()).thenReturn(false);
        when(skillService.saveSkill(any(Skill.class))).thenReturn(skill);

        Skill result = skillController.createSkill(skill, bindingResult);

        assertNotNull(result);
        assertEquals("Java", result.getSkillName());
        verify(skillService, times(1)).saveSkill(any(Skill.class));
    }

    @Test
    void testCreateSkill_InvalidInput() {
        when(bindingResult.hasErrors()).thenReturn(true);
        when(bindingResult.getAllErrors()).thenReturn(new ArrayList<>());

        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            skillController.createSkill(skill, bindingResult);
        });

        assertNotNull(thrown);
        verify(skillService, never()).saveSkill(any(Skill.class));
    }

    @Test
    void testGetSkillById_Found() {
        when(skillService.getSkillById(1L)).thenReturn(skill);

        ResponseEntity<Skill> response = skillController.getSkillById(1L);

        assertEquals(200, response.getStatusCode().value());
        assertEquals("Java", response.getBody().getSkillName());
    }

    @Test
    void testGetSkillById_NotFound() {
        when(skillService.getSkillById(2L)).thenReturn(null);

        ResponseEntity<Skill> response = skillController.getSkillById(2L);

        assertEquals(404, response.getStatusCode().value());
        assertNull(response.getBody());
    }

    @Test
    void testGetAllSkills() {
        List<Skill> skills = Arrays.asList(skill, new Skill(2L, "Python"));
        when(skillService.getAllSkills()).thenReturn(skills);

        List<Skill> result = skillController.getAllSkills();

        assertEquals(2, result.size());
        verify(skillService).getAllSkills();
    }

    @Test
    void testUpdateSkill_ValidInput() {
        when(bindingResult.hasErrors()).thenReturn(false);
        when(skillService.updateSkill(eq(1L), any(Skill.class))).thenReturn(skill);

        ResponseEntity<Skill> response = skillController.updateSkill(1L, skill, bindingResult);

        assertEquals(200, response.getStatusCode().value());
        assertEquals("Java", response.getBody().getSkillName());
    }

    @Test
    void testUpdateSkill_InvalidInput() {
        when(bindingResult.hasErrors()).thenReturn(true);
        when(bindingResult.getFieldErrors()).thenReturn(new ArrayList<>());

        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            skillController.updateSkill(1L, skill, bindingResult);
        });

        assertNotNull(thrown);
        verify(skillService, never()).updateSkill(anyLong(), any(Skill.class));
    }

    @Test
    void testDeleteSkill() {
        doNothing().when(skillService).deleteSkill(1L);

        ResponseEntity<Void> response = skillController.deleteSkill(1L);

        assertEquals(204, response.getStatusCode().value());
        verify(skillService, times(1)).deleteSkill(1L);
    }
}
