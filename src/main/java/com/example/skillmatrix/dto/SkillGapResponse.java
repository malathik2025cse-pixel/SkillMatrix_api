package com.example.skillmatrix.dto;

public class SkillGapResponse {

    private Long skillId;
    private Integer requiredProficiency;
    private Integer teamProficiency;
    private String status;

    public SkillGapResponse() {
    }

    public SkillGapResponse(
            Long skillId,
            Integer requiredProficiency,
            Integer teamProficiency,
            String status) {

        this.skillId = skillId;
        this.requiredProficiency = requiredProficiency;
        this.teamProficiency = teamProficiency;
        this.status = status;
    }

    public Long getSkillId() {
        return skillId;
    }

    public void setSkillId(Long skillId) {
        this.skillId = skillId;
    }

    public Integer getRequiredProficiency() {
        return requiredProficiency;
    }

    public void setRequiredProficiency(Integer requiredProficiency) {
        this.requiredProficiency = requiredProficiency;
    }

    public Integer getTeamProficiency() {
        return teamProficiency;
    }

    public void setTeamProficiency(Integer teamProficiency) {
        this.teamProficiency = teamProficiency;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}