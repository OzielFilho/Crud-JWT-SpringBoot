package com.lum.projetoTJW.dto;

public class TurmaDto {
    private String name;
    private String description;
    private Long professorId;
    public TurmaDto(String name, String description, Long professorId) {
        this.name = name;
        this.description = description;
        this.professorId = professorId;
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

    public Long getProfessorId() {
        return professorId;
    }

    public void setProfessorId(Long professorId) {
        this.professorId = professorId;
    }



}
