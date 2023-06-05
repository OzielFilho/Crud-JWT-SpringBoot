package com.lum.projetoTJW.dto;

public class NewTurmaProfessorDto {
    private Long idTurma;
    private Long idProfessor;

    public NewTurmaProfessorDto(Long idTurma, Long idProfessor) {
        this.idTurma = idTurma;
        this.idProfessor = idProfessor;
    }

    public Long getIdProfessor() {
        return idProfessor;
    }

    public void setIdProfessor(Long idProfessor) {
        this.idProfessor = idProfessor;
    }

    public Long getIdTurma() {
        return idTurma;
    }

    public void setIdTurma(Long idTurma) {
        this.idTurma = idTurma;
    }
}
