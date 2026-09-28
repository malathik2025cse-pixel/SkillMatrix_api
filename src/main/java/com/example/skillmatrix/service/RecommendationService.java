package com.example.skillmatrix.service;

import com.example.skillmatrix.dto.EmployeeRecommendation;
import com.example.skillmatrix.entity.Employee;
import com.example.skillmatrix.entity.Project;
import com.example.skillmatrix.repository.EmployeeRepository;
import com.example.skillmatrix.repository.ProjectRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

@Service
public class RecommendationService {

    private final EmployeeRepository employeeRepository;
    private final ProjectRepository projectRepository;

    public RecommendationService(
            EmployeeRepository employeeRepository,
            ProjectRepository projectRepository) {

        this.employeeRepository = employeeRepository;
        this.projectRepository = projectRepository;
    }

    public List<EmployeeRecommendation> recommendEmployees(
            Long projectId) {

        Project project = projectRepository.findById(projectId)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Project not found with id: " + projectId));

        Map<Long, Integer> requiredSkills =
                project.getRequiredSkills();

        List<EmployeeRecommendation> recommendations =
                new ArrayList<>();

        List<Employee> employees =
                employeeRepository.findAll();

        for (Employee employee : employees) {

            // Employee with no availability cannot be recommended
            if (employee.getAvailability() == null ||
                    employee.getAvailability() <= 0) {
                continue;
            }

            int matchedSkills = 0;

            for (Map.Entry<Long, Integer> requiredSkill
                    : requiredSkills.entrySet()) {

                Long skillId = requiredSkill.getKey();
                Integer requiredProficiency =
                        requiredSkill.getValue();

                Integer employeeProficiency =
                        employee.getSkillProficiencies()
                                .get(skillId);

                if (employeeProficiency != null &&
                        employeeProficiency >= requiredProficiency) {

                    matchedSkills++;
                }
            }

            int totalRequiredSkills = requiredSkills.size();

            double matchPercentage = 0.0;

            if (totalRequiredSkills > 0) {
                matchPercentage =
                        ((double) matchedSkills /
                                totalRequiredSkills) * 100;
            }

            // Only recommend employees with at least
            // one matching required skill
            if (matchedSkills > 0) {

                EmployeeRecommendation recommendation =
                        new EmployeeRecommendation(
                                employee.getId(),
                                employee.getName(),
                                employee.getEmail(),
                                employee.getAvailability(),
                                matchedSkills,
                                totalRequiredSkills,
                                matchPercentage
                        );

                recommendations.add(recommendation);
            }
        }

        /*
         * Sort recommendations:
         *
         * 1. Higher skill match first
         * 2. Higher availability next
         */
        recommendations.sort(
                Comparator
                        .comparing(
                                EmployeeRecommendation
                                        ::getMatchPercentage)
                        .reversed()
                        .thenComparing(
                                EmployeeRecommendation
                                        ::getAvailability)
                        .reversed()
        );

        return recommendations;
    }
}