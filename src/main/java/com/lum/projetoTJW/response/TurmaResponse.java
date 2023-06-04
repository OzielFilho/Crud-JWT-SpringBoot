package com.lum.projetoTJW.response;


import java.util.List;

public class TurmaResponse {
    private String name;
    private String description;
    private ProfessorResponse professor;
    private List<AlunoResponse> alunos;
    public TurmaResponse(String name, String description, ProfessorResponse professor,List<AlunoResponse> alunos) {
        this.name = name;
        this.description = description;
        this.professor = professor;
        this.alunos = alunos;
    }
    public List<AlunoResponse> getAlunos() {
        return alunos;
    }

    public void setAlunos(List<AlunoResponse> alunos) {
        this.alunos = alunos;
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
