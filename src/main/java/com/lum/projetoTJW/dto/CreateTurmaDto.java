package com.lum.projetoTJW.dto;

import com.lum.projetoTJW.models.Turma;

public class CreateTurmaDto {
    public String name;
    public String details;
    public Long professorId;
    public CreateTurmaDto(String name, String description, Long professorId) {
        this.name = name;
        this.details = description;
        this.professorId = professorId;
    }


}
