package com.example.skillmatrix.dto;

public class EmployeeRecommendation {

    private Long employeeId;
    private String employeeName;
    private String email;
    private Integer availability;
    private Integer matchedSkills;
    private Integer totalRequiredSkills;
    private Double matchPercentage;

    public EmployeeRecommendation() {
    }

    public EmployeeRecommendation(
            Long employeeId,
            String employeeName,
            String email,
            Integer availability,
            Integer matchedSkills,
            Integer totalRequiredSkills,
            Double matchPercentage) {

        this.employeeId = employeeId;
        this.employeeName = employeeName;
        this.email = email;
        this.availability = availability;
        this.matchedSkills = matchedSkills;
        this.totalRequiredSkills = totalRequiredSkills;
        this.matchPercentage = matchPercentage;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getAvailability() {
        return availability;
    }

    public void setAvailability(Integer availability) {
        this.availability = availability;
    }

    public Integer getMatchedSkills() {
        return matchedSkills;
    }

    public void setMatchedSkills(Integer matchedSkills) {
        this.matchedSkills = matchedSkills;
    }

    public Integer getTotalRequiredSkills() {
        return totalRequiredSkills;
    }

    public void setTotalRequiredSkills(Integer totalRequiredSkills) {
        this.totalRequiredSkills = totalRequiredSkills;
    }

    public Double getMatchPercentage() {
        return matchPercentage;
    }

    public void setMatchPercentage(Double matchPercentage) {
        this.matchPercentage = matchPercentage;
    }
}