package com.lum.projetoTJW.dto;

public class RemoveTurmaAluno {
    private Long idTurma;
    private Long idAluno;

    public RemoveTurmaAluno(Long idTurma, Long idAluno) {
        this.idTurma = idTurma;
        this.idAluno = idAluno;
    }

    public Long getIdAluno() {
        return idAluno;
    }

    public void setIdAluno(Long idAluno) {
        this.idAluno = idAluno;
    }

    public Long getIdTurma() {
        return idTurma;
    }

    public void setIdTurma(Long idTurma) {
        this.idTurma = idTurma;
    }
    
}
