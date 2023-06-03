package com.lum.projetoTJW.controller;

import com.lum.projetoTJW.entity.Professor;
import com.lum.projetoTJW.entity.Turma;
import com.lum.projetoTJW.repository.IProfessorRepository;
import com.lum.projetoTJW.response.ProfessorResponse;
import com.lum.projetoTJW.response.TurmaResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value= "api/v1/professores")
public class ProfessorController {
    @Autowired
    private IProfessorRepository repository;

    @PostMapping (value = "/create")
    public Professor create(@RequestBody Professor professor){
       return repository.save(professor);
    }
    @GetMapping
    public List<ProfessorResponse>  findAllProfessores(){
        List<Professor> professores = (List<Professor>) repository.findAll();
        List<ProfessorResponse> professoresResponse = new ArrayList<ProfessorResponse>();
        professores.forEach(professor -> {
            List<Turma> turmas = professor.getTurmas();
            List<TurmaResponse> turmasResponse = new ArrayList<TurmaResponse>();
            turmas.forEach(turma -> {
                TurmaResponse turmaResponse = new TurmaResponse(turma.getName(),turma.getDescription(),null);
                turmasResponse.add(turmaResponse);
            });

            ProfessorResponse professorResponse = new ProfessorResponse(professor.getName(),professor.getEmail(),turmasResponse);
            professoresResponse.add(professorResponse);
        });
        return professoresResponse;
    }
    @GetMapping(value = "/{id}")
    public ProfessorResponse findProfessorById(@PathVariable Long id){
        Professor professor =  repository.findById(id).get();
        List<Turma> turmas = professor.getTurmas();
        List<TurmaResponse> turmasResponse = new ArrayList<TurmaResponse>();
        turmas.forEach(turma -> {
            TurmaResponse turmaResponse = new TurmaResponse(turma.getName(),turma.getDescription(),null);
            turmasResponse.add(turmaResponse);
        });

        ProfessorResponse professorResponse = new ProfessorResponse(professor.getName(),professor.getEmail(),turmasResponse);
        return professorResponse;
    }
}
