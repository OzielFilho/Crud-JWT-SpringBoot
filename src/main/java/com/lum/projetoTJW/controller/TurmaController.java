package com.lum.projetoTJW.controller;

import com.lum.projetoTJW.dto.TurmaDto;
import com.lum.projetoTJW.entity.Aluno;
import com.lum.projetoTJW.entity.Professor;
import com.lum.projetoTJW.entity.Turma;
import com.lum.projetoTJW.repository.IProfessorRepository;
import com.lum.projetoTJW.repository.ITurmaRepository;
import com.lum.projetoTJW.response.AlunoResponse;
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
@RequestMapping(value= "api/v1/turmas")
public class TurmaController {
    @Autowired
    private ITurmaRepository repositoryTurma;
    @Autowired
    private IProfessorRepository repositoryProfessor;

    @PostMapping(value = "/create")
    public ResponseEntity<Object> create(@RequestBody TurmaDto turma){
        Optional findProfessor = repositoryProfessor.findById(turma.getProfessorId());
        if(!(findProfessor.isPresent())){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Professor não encontrado");
        }
        Professor professorGet = (Professor) findProfessor.get();
        Turma saveTurma = new Turma();
        saveTurma.setDescription(turma.getDescription());
        saveTurma.setProfessor(professorGet);
        saveTurma.setName(turma.getName());
        Turma savedTurma = repositoryTurma.save(saveTurma);
        return ResponseEntity.status(HttpStatus.CREATED).body("Turma Criada: "+savedTurma.getName());
    }
    @GetMapping
    public  List<TurmaResponse> findAllTurmas(){
        List<Turma> turmas = (List<Turma>) repositoryTurma.findAll();
        List<TurmaResponse> turmasResponse =  new ArrayList<TurmaResponse>();
        turmas.forEach(turma -> {
            List<Aluno> alunos = turma.getAlunos();
            List<AlunoResponse> alunosResponse = new ArrayList<AlunoResponse>();
            alunos.forEach(aluno1 -> {
                AlunoResponse alunoResponse = new AlunoResponse(aluno1.getName(),aluno1.getEmail(),null);
                alunosResponse.add(alunoResponse);
            });
            Professor professor = turma.getProfessor();
            ProfessorResponse professorResponse = new ProfessorResponse(professor.getName(),professor.getEmail(),null);
            TurmaResponse turmaResponse = new TurmaResponse(turma.getName(),turma.getDescription(),professorResponse,alunosResponse);
            turmasResponse.add(turmaResponse);
        });
        return turmasResponse;
    }
    @GetMapping(value = "/{id}")
    public ResponseEntity<Object> findProfessorById(@PathVariable Long id){
        Optional turma =  repositoryTurma.findById(id);
        if(!(turma.isPresent())){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Turma não encontrada");
        }
        Turma turmaGet = (Turma) turma.get();
        List<Aluno> alunos = turmaGet.getAlunos();
        List<AlunoResponse> alunosResponse = new ArrayList<AlunoResponse>();
        alunos.forEach(aluno1 -> {
            AlunoResponse alunoResponse = new AlunoResponse(aluno1.getName(),aluno1.getEmail(),null);
            alunosResponse.add(alunoResponse);
        });
        Professor professor = turmaGet.getProfessor();
        ProfessorResponse professorResponse = new ProfessorResponse(professor.getName(),professor.getEmail(),null);
        TurmaResponse turmaResponse = new TurmaResponse(turmaGet.getName(),turmaGet.getDescription(),professorResponse,alunosResponse);
        return ResponseEntity.status(HttpStatus.FOUND).body(turmaResponse);
    }
}
