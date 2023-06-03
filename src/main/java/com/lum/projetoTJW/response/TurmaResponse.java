package com.lum.projetoTJW.response;

public class TurmaResponse {
    private String name;
    private String description;
    private ProfessorResponse professor;
    public TurmaResponse(String name, String description, ProfessorResponse professor) {
        this.name = name;
        this.description = description;
        this.professor = professor;
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

    public ProfessorResponse getProfessor() {
        return professor;
    }

    public void setProfessor(ProfessorResponse professor) {
        this.professor = professor;
    }


}
