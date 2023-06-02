package com.lum.projetoTJW.responses;

import java.util.List;

public class TurmaResponse {
    private String name;
    private String professor;
    private String details;

    public TurmaResponse(String name, String professor, String details) {
        this.name = name;
        this.professor = professor;
        this.details = details;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProfessor() {
        return professor;
    }

    public void setProfessor(String professor) {
        this.professor = professor;
    }

}
