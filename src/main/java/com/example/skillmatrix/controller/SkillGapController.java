package com.example.skillmatrix.controller;

import com.example.skillmatrix.dto.SkillGapResponse;
import com.example.skillmatrix.service.SkillGapService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/projects")
public class SkillGapController {

    private final SkillGapService skillGapService;

    public SkillGapController(
            SkillGapService skillGapService) {

        this.skillGapService = skillGapService;
    }

    @GetMapping("/{projectId}/skill-gaps")
    public ResponseEntity<List<SkillGapResponse>> getSkillCoverage(
            @PathVariable Long projectId) {

        return ResponseEntity.ok(
                skillGapService.getSkillCoverage(projectId)
        );
    }
}