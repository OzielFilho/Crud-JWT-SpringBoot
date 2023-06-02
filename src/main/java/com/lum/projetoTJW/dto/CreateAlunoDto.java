package com.lum.projetoTJW.dto;

public class CreateAlunoDto {

    public String name;
    public String email;
    public Long turma_id;
    public CreateAlunoDto(String name, String email, Long turmaId) {
        this.name = name;
        this.email = email;
        this.turma_id = turmaId;
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

    public Long getTurma_id() {
        return turma_id;
    }

    public void setTurma_id(Long turma_id) {
        this.turma_id = turma_id;
    }

}
