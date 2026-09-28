package com.example.skillmatrix.service;

import com.example.skillmatrix.entity.Project;
import com.example.skillmatrix.repository.ProjectRepository;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService {

    private final ProjectRepository projectRepository;

    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public Project createProject(Project project) {
        return projectRepository.save(project);
    }

    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    public Project getProjectById(Long id) {

        return projectRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Project not found with id: " + id
                        ));
    }

    public Project updateProject(Long id, Project updatedProject) {

        Project existingProject =
                projectRepository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Project not found with id: " + id
                                ));

        existingProject.setName(updatedProject.getName());
        existingProject.setDescription(
                updatedProject.getDescription()
        );
        existingProject.setRequiredSkills(
                updatedProject.getRequiredSkills()
        );

        return projectRepository.save(existingProject);
    }

    public void deleteProject(Long id) {

        Project project =
                projectRepository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Project not found with id: " + id
                                ));

        projectRepository.delete(project);
    }
}