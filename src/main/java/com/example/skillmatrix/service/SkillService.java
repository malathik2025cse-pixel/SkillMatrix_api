package com.example.skillmatrix.service;

import com.example.skillmatrix.entity.Skill;
import com.example.skillmatrix.repository.SkillRepository;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SkillService {

    private final SkillRepository skillRepository;

    public SkillService(SkillRepository skillRepository) {
        this.skillRepository = skillRepository;
    }

    public Skill createSkill(Skill skill) {
        return skillRepository.save(skill);
    }

    public List<Skill> getAllSkills() {
        return skillRepository.findAll();
    }

    public Skill getSkillById(Long id) {

        return skillRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Skill not found with id: " + id
                        ));
    }

    public Skill updateSkill(Long id, Skill updatedSkill) {

        Skill existingSkill =
                skillRepository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Skill not found with id: " + id
                                ));

        existingSkill.setName(updatedSkill.getName());

        return skillRepository.save(existingSkill);
    }

    public void deleteSkill(Long id) {

        Skill skill =
                skillRepository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Skill not found with id: " + id
                                ));

        skillRepository.delete(skill);
    }
}