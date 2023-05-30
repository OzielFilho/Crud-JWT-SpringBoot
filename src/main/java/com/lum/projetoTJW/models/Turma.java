package com.lum.projetoTJW.models;

import javax.persistence.*;
import java.util.List;
@Entity
@Table(name = "tb_turma")
public class Turma {    @Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;

    @Column(name = "nome_turma")
    private String nomeTurma;

    @ManyToMany(mappedBy = "turmas")
    private List<Aluno> alunos;

    @ManyToOne
    @JoinColumn(name = "professor_id")
    private Professor professor;

    public Turma(){

    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeTurma() {
        return nomeTurma;
    }

    public void setNomeTurma(String nomeTurma) {
        this.nomeTurma = nomeTurma;
    }

    public List<Aluno> getAlunos() {
        return alunos;
    }

    public void setAlunos(List<Aluno> alunos) {
        this.alunos = alunos;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }


}
