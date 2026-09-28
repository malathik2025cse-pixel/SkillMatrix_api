package com.example.skillmatrix.controller;

import com.example.skillmatrix.entity.Skill;
import com.example.skillmatrix.service.SkillService;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/skills")
public class SkillController {

    private final SkillService skillService;

    public SkillController(SkillService skillService) {
        this.skillService = skillService;
    }

    @PostMapping
    public ResponseEntity<Skill> createSkill(
            @Valid @RequestBody Skill skill) {

        Skill savedSkill = skillService.createSkill(skill);

        return new ResponseEntity<>(
                savedSkill,
                HttpStatus.CREATED
        );
    }

    @GetMapping
    public ResponseEntity<List<Skill>> getAllSkills() {

        return ResponseEntity.ok(
                skillService.getAllSkills()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<Skill> getSkillById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                skillService.getSkillById(id)
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<Skill> updateSkill(
            @PathVariable Long id,
            @Valid @RequestBody Skill skill) {

        return ResponseEntity.ok(
                skillService.updateSkill(id, skill)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteSkill(
            @PathVariable Long id) {

        skillService.deleteSkill(id);

        return ResponseEntity.ok(
                "Skill deleted successfully"
        );
    }
}