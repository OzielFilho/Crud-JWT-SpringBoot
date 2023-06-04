package com.lum.projetoTJW.response;

public class NewTurmaAlunoResponse {

    private Long idTurma;
    private Long idAluno;

    public NewTurmaAlunoResponse(Long idTurma, Long idAluno) {
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
