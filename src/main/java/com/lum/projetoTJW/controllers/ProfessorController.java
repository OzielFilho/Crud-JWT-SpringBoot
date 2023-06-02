package com.lum.projetoTJW.controllers;

import com.lum.projetoTJW.models.Professor;
import com.lum.projetoTJW.models.Turma;
import com.lum.projetoTJW.repository.ProfessorRepository;
import com.lum.projetoTJW.responses.ProfessorResponse;
import com.lum.projetoTJW.responses.TurmaResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value= "api/v1/professores")
public class ProfessorController {
    @Autowired
    private ProfessorRepository repository;
    @GetMapping(value = "/{id}")
    public Professor findProfessorId(@PathVariable Long id){
      return repository.findById(id).get();
    }
    @GetMapping
    public List<ProfessorResponse> findAllProfessor(){
        List<Professor> professores = repository.findAll();
        List<ProfessorResponse> result =  new ArrayList<ProfessorResponse>();
        professores.forEach(professor -> {
            List<TurmaResponse> turmasResponse =  new ArrayList<TurmaResponse>();
            List<Turma> turmas = professor.getTurmas();
            turmas.forEach(turma -> {
                TurmaResponse response = new TurmaResponse(turma.getName(),turma.getProfessor().getName(),turma.getDetails());
                turmasResponse.add(response);
            });
            ProfessorResponse professorResponse = new ProfessorResponse(professor.getName(),professor.getEmail(),turmasResponse);
            result.add(professorResponse);
        });
        return result;
    }
    @PostMapping(value = "/create")
    public Long createProfessor(@RequestBody Professor professor){
       Professor professorSave =  repository.save(professor);
       return professorSave.getId();
    }
}
