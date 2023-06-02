package com.lum.projetoTJW.controllers;

import com.lum.projetoTJW.dto.CreateTurmaDto;
import com.lum.projetoTJW.models.Professor;
import com.lum.projetoTJW.models.Turma;
import com.lum.projetoTJW.repository.ProfessorRepository;
import com.lum.projetoTJW.repository.TurmaRepository;
import com.lum.projetoTJW.responses.TurmaResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
@RestController
@RequestMapping(value= "api/v1/turmas")
public class TurmaController {
    @Autowired
    private TurmaRepository repository;
    @Autowired
    private ProfessorRepository pRepository;
    @GetMapping(value = "/{id}")
    public TurmaResponse findTurmaId(@PathVariable Long id){
        Turma turma =  repository.findById(id).get();
        TurmaResponse response = new TurmaResponse(turma.getName(),turma.getProfessor().getName(),turma.getDetails());
        return response;
    }
    @GetMapping
    public List<TurmaResponse> findAllTurma(){
       List<Turma> turmas = repository.findAll();
        List<TurmaResponse> turmasResponse = new ArrayList<TurmaResponse>();
        turmas.forEach(turma -> {
            TurmaResponse response = new TurmaResponse(turma.getName(),turma.getProfessor().getName(),turma.getDetails());
            turmasResponse.add(response);
        });
        return turmasResponse;
    }
    @PostMapping(value = "/create")
    public TurmaResponse createTurma(@RequestBody CreateTurmaDto createTurmaDto){
        Turma turma = new Turma();
        turma.setName(createTurmaDto.name);
        turma.setDetails(createTurmaDto.details);
        Professor professor = pRepository.findById(createTurmaDto.professorId).get();
        turma.setProfessor(professor);
        Turma result = repository.save(turma);
        TurmaResponse response = new TurmaResponse(result.getName(),result.getProfessor().getName(),turma.getDetails());
        return response;
    }
}
