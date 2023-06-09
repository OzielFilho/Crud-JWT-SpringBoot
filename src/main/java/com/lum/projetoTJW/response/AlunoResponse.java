package com.lum.projetoTJW.response;

import java.util.List;

public class AlunoResponse {

    private String name;
    private String email;
    private List<TurmaResponse> turmas;

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

    public List<TurmaResponse> getTurmas() {
        return turmas;
    }

    public void setTurmas(List<TurmaResponse> turmas) {
        this.turmas = turmas;
    }

    public AlunoResponse(String name, String email, List<TurmaResponse> turmas) {
        this.name = name;
        this.email = email;
        this.turmas = turmas;
    }



}
