package com.lum.projetoTJW.controller;

import com.lum.projetoTJW.dto.AlunoDto;
import com.lum.projetoTJW.dto.TurmaDto;
import com.lum.projetoTJW.entity.Aluno;
import com.lum.projetoTJW.entity.Professor;
import com.lum.projetoTJW.entity.Turma;
import com.lum.projetoTJW.repository.IAlunoRepository;
import com.lum.projetoTJW.repository.IProfessorRepository;
import com.lum.projetoTJW.repository.ITurmaRepository;
import com.lum.projetoTJW.response.AlunoResponse;
import com.lum.projetoTJW.response.NewTurmaAlunoResponse;
import com.lum.projetoTJW.response.ProfessorResponse;
import com.lum.projetoTJW.response.TurmaResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value= "api/v1/alunos")
public class AlunoController {
    @Autowired
    private IAlunoRepository repository;

    @Autowired
    private ITurmaRepository repositoryTurma;

    @PostMapping(value = "/create")
    public Long create(@RequestBody AlunoDto aluno){
        Turma findTurma = repositoryTurma.findById(aluno.getTurmaId()).get();
        List<Turma> turmas = new ArrayList<Turma>();
        turmas.add(findTurma);
        Aluno saveAluno = new Aluno();
        saveAluno.setName(aluno.getName());
        saveAluno.setEmail(aluno.getEmail());
        saveAluno.setTurmas(turmas);
        Aluno savedAluno = repository.save(saveAluno);
        return savedAluno.getId();
    }

    @GetMapping
    public List<AlunoResponse> findAllAlunos(){
        List<AlunoResponse> alunosResponse = new ArrayList<AlunoResponse>();
        List<Aluno> alunos = (List<Aluno>) repository.findAll();
        alunos.forEach(aluno -> {
            List<Turma> turmas = aluno.getTurmas();
            List<TurmaResponse> turmasResponse = new ArrayList<TurmaResponse>();
            turmas.forEach(turma -> {
                Professor professor = turma.getProfessor();
                ProfessorResponse professorResponse = new ProfessorResponse(professor.getName(),professor.getEmail(),null);
                TurmaResponse turmaResponse = new TurmaResponse(turma.getName(),turma.getDescription(),professorResponse,null);
                turmasResponse.add(turmaResponse);
            });
            AlunoResponse alunoResponse = new AlunoResponse(aluno.getName(),aluno.getEmail(),turmasResponse);
            alunosResponse.add(alunoResponse);
        });
        return alunosResponse;
    }

    @PostMapping(value = "/addNewTurma")
    public Long addNewTurmaInAluno(@RequestBody NewTurmaAlunoResponse newTurmaAlunoResponse){
        Aluno aluno = repository.findById(newTurmaAlunoResponse.getIdAluno()).get();
        Turma turma = repositoryTurma.findById(newTurmaAlunoResponse.getIdTurma()).get();
        aluno.getTurmas().add(turma);
        Aluno update  = repository.save(aluno);
        return update.getId();
    }

    @GetMapping(value = "/{id}")
    public AlunoResponse findAlunoById(@PathVariable Long id){
        Aluno aluno =  repository.findById(id).get();
        List<Turma> turmas = aluno.getTurmas();
        List<TurmaResponse> turmasResponse = new ArrayList<TurmaResponse>();
        turmas.forEach(turma -> {
            Professor professor = turma.getProfessor();
            ProfessorResponse professorResponse = new ProfessorResponse(professor.getName(),professor.getEmail(),null);
            TurmaResponse turmaResponse = new TurmaResponse(turma.getName(),turma.getDescription(),professorResponse,null);
            turmasResponse.add(turmaResponse);
        });
        AlunoResponse alunoResponse = new AlunoResponse(aluno.getName(),aluno.getEmail(),turmasResponse);
        return alunoResponse;
    }
}
