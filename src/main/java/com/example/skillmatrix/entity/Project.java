package com.example.skillmatrix.entity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import java.util.HashMap;
import java.util.Map;

@Entity
@Table(name = "projects")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Project name is required")
    @Column(nullable = false, unique = true)
    private String name;

    @NotBlank(message = "Project description is required")
    @Column(nullable = false, length = 1000)
    private String description;

    /*
     * Key   = Skill ID
     * Value = Required proficiency
     *
     * Example:
     * 1 -> 4
     * 2 -> 4
     * 3 -> 3
     *
     * This means:
     * Skill ID 1 requires proficiency 4
     * Skill ID 2 requires proficiency 4
     * Skill ID 3 requires proficiency 3
     */
    @ElementCollection
    @CollectionTable(
            name = "project_required_skills",
            joinColumns = @JoinColumn(name = "project_id")
    )
    @MapKeyColumn(name = "skill_id")
    @Column(name = "required_proficiency")
    private Map<Long, Integer> requiredSkills = new HashMap<>();

    public Project() {
    }

    public Project(String name, String description,
                   Map<Long, Integer> requiredSkills) {
        this.name = name;
        this.description = description;
        this.requiredSkills = requiredSkills;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Map<Long, Integer> getRequiredSkills() {
        return requiredSkills;
    }
    
    public void setRequiredSkills(Map<Long, Integer> requiredSkills) {
        this.requiredSkills = requiredSkills;
    }
}