package com.example.skillmatrix.service;

import com.example.skillmatrix.dto.SkillGapResponse;
import com.example.skillmatrix.entity.Allocation;
import com.example.skillmatrix.entity.Employee;
import com.example.skillmatrix.entity.Project;
import com.example.skillmatrix.repository.AllocationRepository;
import com.example.skillmatrix.repository.ProjectRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class SkillGapService {

    private final ProjectRepository projectRepository;
    private final AllocationRepository allocationRepository;

    public SkillGapService(
            ProjectRepository projectRepository,
            AllocationRepository allocationRepository) {

        this.projectRepository = projectRepository;
        this.allocationRepository = allocationRepository;
    }

    public List<SkillGapResponse> getSkillCoverage(Long projectId) {

        Project project = projectRepository.findById(projectId)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Project not found with id: " + projectId));

        Map<Long, Integer> requiredSkills =
                project.getRequiredSkills();

        List<Allocation> allocations =
                allocationRepository.findByProjectId(projectId);

        List<SkillGapResponse> result =
                new ArrayList<>();

        for (Map.Entry<Long, Integer> requiredSkill
                : requiredSkills.entrySet()) {

            Long skillId = requiredSkill.getKey();

            Integer requiredProficiency =
                    requiredSkill.getValue();

            int teamProficiency = 0;

            for (Allocation allocation : allocations) {

                Employee employee =
                        allocation.getEmployee();

                Integer employeeProficiency =
                        employee.getSkillProficiencies()
                                .get(skillId);

                if (employeeProficiency != null &&
                        employeeProficiency > teamProficiency) {

                    teamProficiency = employeeProficiency;
                }
            }

            String status;

            if (teamProficiency >= requiredProficiency) {
                status = "COVERED";
            } else {
                status = "GAP";
            }

            SkillGapResponse response =
                    new SkillGapResponse(
                            skillId,
                            requiredProficiency,
                            teamProficiency,
                            status
                    );

            result.add(response);
        }

        return result;
    }
}