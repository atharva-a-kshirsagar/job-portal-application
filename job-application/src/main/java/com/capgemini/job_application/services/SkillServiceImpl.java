package com.capgemini.job_application.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.job_application.entities.Skill;
import com.capgemini.job_application.repositories.SkillRepository;

@Service
public class SkillServiceImpl implements SkillService {
	private SkillRepository skillRepository;

	@Autowired
	public SkillServiceImpl(SkillRepository skillRepository) {

		this.skillRepository = skillRepository;
	}

	@Override
	public Skill saveSkill(Skill skill) {
		if (skillRepository.existsBySkillName(skill.getSkillName())) {
	        throw new IllegalArgumentException("Skill with this name already exists.");
	    }
	    return skillRepository.save(skill);
	}

	@Override
	public Skill getSkillById(Long skillId) {
		Optional<Skill> optionalSkill = skillRepository.findById(skillId);
		return optionalSkill.orElse(null);
	}

	@Override
	public List<Skill> getAllSkills() {
		return skillRepository.findAll();
	}

	@Override
	public Skill updateSkill(Long skillId, Skill skill) {

		Skill skills = skillRepository.findById(skillId).orElseThrow(() -> new RuntimeException("Skill not found"));
		skills.setSkillName(skill.getSkillName());
		return skillRepository.save(skills);
	}

	@Override
	public void deleteSkill(Long skillId) {
		skillRepository.deleteById(skillId);
	}

}
