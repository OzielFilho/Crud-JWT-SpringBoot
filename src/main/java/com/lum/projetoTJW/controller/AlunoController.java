package com.lum.projetoTJW.controller;

import com.lum.projetoTJW.dto.AlunoDto;
import com.lum.projetoTJW.entity.Aluno;
import com.lum.projetoTJW.entity.Professor;
import com.lum.projetoTJW.entity.Turma;
import com.lum.projetoTJW.repository.IAlunoRepository;
import com.lum.projetoTJW.repository.ITurmaRepository;
import com.lum.projetoTJW.response.AlunoResponse;
import com.lum.projetoTJW.dto.NewTurmaAlunoDto;
import com.lum.projetoTJW.response.ProfessorResponse;
import com.lum.projetoTJW.response.TurmaResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value= "api/v1/alunos")
public class AlunoController {
    @Autowired
    private IAlunoRepository repository;

    @Autowired
    private ITurmaRepository repositoryTurma;

    @PostMapping(value = "/create")
    public ResponseEntity<Object> create(@RequestBody AlunoDto aluno){
        Optional findTurma = repositoryTurma.findById(aluno.getTurmaId());
        if(!(findTurma.isPresent())){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Turma não encontrada");
        }
        List<Turma> turmas = new ArrayList<Turma>();
        turmas.add((Turma) findTurma.get());
        Aluno saveAluno = new Aluno();
        saveAluno.setName(aluno.getName());
        saveAluno.setEmail(aluno.getEmail());
        saveAluno.setTurmas(turmas);
        Aluno savedAluno = repository.save(saveAluno);
        return ResponseEntity.status(HttpStatus.CREATED).body("Aluno "+ savedAluno.getName() +" Criado");
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
    public ResponseEntity<Object> addNewTurmaInAluno(@RequestBody NewTurmaAlunoDto newTurmaAlunoDto){
        Optional aluno = repository.findById(newTurmaAlunoDto.getIdAluno());
        if(!(aluno.isPresent())){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Aluno não encontrado");
        }
        Optional turma = repositoryTurma.findById(newTurmaAlunoDto.getIdTurma());
        if(!(turma.isPresent())){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Turma não encontrada");
        }
        Aluno alunoGet = (Aluno) aluno.get();
        Turma turmaGet = (Turma) turma.get();
        alunoGet.getTurmas().add(turmaGet);
        repository.save(alunoGet);
        return ResponseEntity.ok("Nova Turma adicionada");
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Object> findAlunoById(@PathVariable Long id){
        Optional aluno =  repository.findById(id);
        if(!(aluno.isPresent())){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Aluno não encontrado");
        }
        Aluno alunoGet = (Aluno) aluno.get();
        List<Turma> turmas = alunoGet.getTurmas();
        List<TurmaResponse> turmasResponse = new ArrayList<TurmaResponse>();
        turmas.forEach(turma -> {
            Professor professor = turma.getProfessor();
            ProfessorResponse professorResponse = new ProfessorResponse(professor.getName(),professor.getEmail(),null);
            TurmaResponse turmaResponse = new TurmaResponse(turma.getName(),turma.getDescription(),professorResponse,null);
            turmasResponse.add(turmaResponse);
        });
        AlunoResponse alunoResponse = new AlunoResponse(alunoGet.getName(),alunoGet.getEmail(),turmasResponse);
        return ResponseEntity.status(HttpStatus.FOUND).body(alunoResponse);
    }
}
