package com.example.skillmatrix.controller;

import com.example.skillmatrix.dto.EmployeeRecommendation;
import com.example.skillmatrix.service.RecommendationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/recommendations")
public class RecommendationController {

    private final RecommendationService recommendationService;

    public RecommendationController(
            RecommendationService recommendationService) {

        this.recommendationService = recommendationService;
    }

    @GetMapping("/project/{projectId}")
    public ResponseEntity<List<EmployeeRecommendation>>
    recommendEmployees(
            @PathVariable Long projectId) {

        return ResponseEntity.ok(
                recommendationService
                        .recommendEmployees(projectId)
        );
    }
}