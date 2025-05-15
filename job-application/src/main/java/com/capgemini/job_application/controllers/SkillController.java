package com.capgemini.job_application.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.job_application.entities.Skill;
import com.capgemini.job_application.services.SkillService;

@RestController
@RequestMapping("/api/skills")
public class SkillController {
    private SkillService skillService;

    @Autowired
	public SkillController(SkillService skillService) {
		this.skillService = skillService;
	}
    
    @PostMapping
    public Skill createSkill(@RequestBody Skill skill) {
        return skillService.saveSkill(skill);
    }
    
    @GetMapping("/{skillId}")
    public ResponseEntity<Skill> getSkillById(@PathVariable Long skillId) {
        Skill skill = skillService.getSkillById(skillId);
        if (skill == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(skill);
    }
    
    @GetMapping
    public List<Skill> getAllSkills() {
        return skillService.getAllSkills();
    }
    
    @PutMapping("/{skillId}")
    public ResponseEntity<Skill> updateSkill(@PathVariable Long skillId, @RequestBody Skill skill) {
        Skill updatedSkill = skillService.updateSkill(skillId, skill);
        if (updatedSkill == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedSkill);
    }

    @DeleteMapping("/{skillId}")
    public ResponseEntity<Void> deleteSkill(@PathVariable Long skillId) {
        skillService.deleteSkill(skillId);
        return ResponseEntity.noContent().build();
    }
    
}
