package com.lum.projetoTJW.controller;

import com.lum.projetoTJW.dto.NewTurmaProfessorDto;
import com.lum.projetoTJW.entity.Aluno;
import com.lum.projetoTJW.entity.Professor;
import com.lum.projetoTJW.entity.Turma;
import com.lum.projetoTJW.repository.IProfessorRepository;
import com.lum.projetoTJW.repository.ITurmaRepository;
import com.lum.projetoTJW.response.AlunoResponse;
import com.lum.projetoTJW.response.ProfessorResponse;
import com.lum.projetoTJW.response.TurmaResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "api/v1/professores")
public class ProfessorController {
    @Autowired
    private IProfessorRepository repository;
    @Autowired
    private ITurmaRepository repositoryTurma;

    @PostMapping(value = "/create")
    public Professor create(@RequestBody Professor professor) {
        return repository.save(professor);
    }

    @GetMapping
    public List<ProfessorResponse> findAllProfessores() {
        List<Professor> professores = (List<Professor>) repository.findAll();
        List<ProfessorResponse> professoresResponse = new ArrayList<ProfessorResponse>();
        professores.forEach(professor -> {
            List<Turma> turmas = professor.getTurmas();
            List<TurmaResponse> turmasResponse = new ArrayList<TurmaResponse>();
            turmas.forEach(turma -> {
                List<Aluno> alunos = turma.getAlunos();
                List<AlunoResponse> alunosResponse = new ArrayList<AlunoResponse>();
                alunos.forEach(aluno1 -> {
                    AlunoResponse alunoResponse = new AlunoResponse(aluno1.getName(), aluno1.getEmail(), null);
                    alunosResponse.add(alunoResponse);
                });
                TurmaResponse turmaResponse = new TurmaResponse(turma.getName(), turma.getDescription(), null,
                        alunosResponse);
                turmasResponse.add(turmaResponse);
            });

            ProfessorResponse professorResponse = new ProfessorResponse(professor.getName(), professor.getEmail(),
                    turmasResponse);
            professoresResponse.add(professorResponse);
        });
        return professoresResponse;
    }

    @GetMapping(value = "/{id}")
    public ProfessorResponse findProfessorById(@PathVariable Long id) {
        Professor professor = repository.findById(id).get();
        List<Turma> turmas = professor.getTurmas();
        List<TurmaResponse> turmasResponse = new ArrayList<TurmaResponse>();
        turmas.forEach(turma -> {
            List<Aluno> alunos = turma.getAlunos();
            List<AlunoResponse> alunosResponse = new ArrayList<AlunoResponse>();
            alunos.forEach(aluno1 -> {
                AlunoResponse alunoResponse = new AlunoResponse(aluno1.getName(), aluno1.getEmail(), null);
                alunosResponse.add(alunoResponse);
            });
            TurmaResponse turmaResponse = new TurmaResponse(turma.getName(), turma.getDescription(), null,
                    alunosResponse);
            turmasResponse.add(turmaResponse);
        });

        ProfessorResponse professorResponse = new ProfessorResponse(professor.getName(), professor.getEmail(),
                turmasResponse);
        return professorResponse;
    }

    @PostMapping(value = "/addNewTurma")
    public Long addNewTurmaInProfessor(@RequestBody NewTurmaProfessorDto newTurmaAlunoDto) {
        Professor professor = repository.findById(newTurmaAlunoDto.getIdProfessor()).get();
        Turma turma = repositoryTurma.findById(newTurmaAlunoDto.getIdTurma()).get();
        professor.getTurmas().add(turma);
        Professor update = repository.save(professor);
        return update.getId();
    }
}
