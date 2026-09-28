package com.example.skillmatrix.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

import java.util.HashMap;
import java.util.Map;

@Entity
@Table(name = "employees")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Employee name is required")
    @Column(nullable = false)
    private String name;

    @NotBlank(message = "Employee email is required")
    @Email(message = "Enter a valid email")
    @Column(nullable = false, unique = true)
    private String email;

    @Min(value = 1, message = "Total capacity must be at least 1")
    @Max(value = 100, message = "Total capacity cannot be greater than 100")
    @Column(name = "total_capacity", nullable = false)
    private Integer totalCapacity;

    @Min(value = 0, message = "Availability cannot be less than 0")
    @Max(value = 100, message = "Availability cannot be greater than 100")
    @Column(nullable = false)
    private Integer availability;

    /*
     * Employee skills and proficiency.
     *
     * Key   = Skill ID
     * Value = Proficiency level
     *
     * Example:
     * 1 -> 5   (Java proficiency 5)
     * 2 -> 4   (Spring Boot proficiency 4)
     * 3 -> 3   (MySQL proficiency 3)
     */
    @ElementCollection
    @CollectionTable(
            name = "employee_skills",
            joinColumns = @JoinColumn(name = "employee_id")
    )
    @MapKeyColumn(name = "skill_id")
    @Column(name = "proficiency")
    private Map<Long, Integer> skillProficiencies = new HashMap<>();

    public Employee() {
    }

    public Employee(String name, String email,
                    Integer totalCapacity, Integer availability) {
        this.name = name;
        this.email = email;
        this.totalCapacity = totalCapacity;
        this.availability = availability;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getTotalCapacity() {
        return totalCapacity;
    }

    public void setTotalCapacity(Integer totalCapacity) {
        this.totalCapacity = totalCapacity;
    }

    public Integer getAvailability() {
        return availability;
    }

    public void setAvailability(Integer availability) {
        this.availability = availability;
    }

    public Map<Long, Integer> getSkillProficiencies() {
        return skillProficiencies;
    }

    public void setSkillProficiencies(
            Map<Long, Integer> skillProficiencies) {
        this.skillProficiencies = skillProficiencies;
    }
}