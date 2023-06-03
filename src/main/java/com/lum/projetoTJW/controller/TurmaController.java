package com.lum.projetoTJW.controller;

import com.lum.projetoTJW.dto.TurmaDto;
import com.lum.projetoTJW.entity.Professor;
import com.lum.projetoTJW.entity.Turma;
import com.lum.projetoTJW.repository.IProfessorRepository;
import com.lum.projetoTJW.repository.ITurmaRepository;
import com.lum.projetoTJW.response.ProfessorResponse;
import com.lum.projetoTJW.response.TurmaResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
@RestController
@RequestMapping(value= "api/v1/turmas")
public class TurmaController {
    @Autowired
    private ITurmaRepository repositoryTurma;
    @Autowired
    private IProfessorRepository repositoryProfessor;

    @PostMapping(value = "/create")
    public Long create(@RequestBody TurmaDto turma){
        Professor findProfessor = repositoryProfessor.findById(turma.getProfessorId()).get();
        Turma saveTurma = new Turma();
        saveTurma.setDescription(turma.getDescription());
        saveTurma.setProfessor(findProfessor);
        saveTurma.setName(turma.getName());
        Turma savedTurma = repositoryTurma.save(saveTurma);
        return savedTurma.getId();
    }
    @GetMapping
    public  List<TurmaResponse> findAllTurmas(){
        List<Turma> turmas = (List<Turma>) repositoryTurma.findAll();
        List<TurmaResponse> turmasResponse =  new ArrayList<TurmaResponse>();
        turmas.forEach(turma -> {
            Professor professor = turma.getProfessor();
            ProfessorResponse professorResponse = new ProfessorResponse(professor.getName(),professor.getEmail(),null);
            TurmaResponse turmaResponse = new TurmaResponse(turma.getName(),turma.getDescription(),professorResponse);
            turmasResponse.add(turmaResponse);
        });
        return turmasResponse;
    }
    @GetMapping(value = "/{id}")
    public TurmaResponse findProfessorById(@PathVariable Long id){
        Turma turma =  repositoryTurma.findById(id).get();
        Professor professor = turma.getProfessor();
        ProfessorResponse professorResponse = new ProfessorResponse(professor.getName(),professor.getEmail(),null);
        TurmaResponse turmaResponse = new TurmaResponse(turma.getName(),turma.getDescription(),professorResponse);
        return turmaResponse;
    }
}
