package com.lum.projetoTJW.controllers;
import com.lum.projetoTJW.dto.CreateAlunoDto;
import com.lum.projetoTJW.models.Aluno;
import com.lum.projetoTJW.models.Turma;
import com.lum.projetoTJW.repository.AlunoRepository;

import java.util.ArrayList;
import java.util.List;

import com.lum.projetoTJW.repository.TurmaRepository;
import com.lum.projetoTJW.responses.AlunoResponse;
import com.lum.projetoTJW.responses.TurmaResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value= "api/v1/alunos")
public class AlunoController {
    @Autowired
    private AlunoRepository repository;
    @Autowired
    private TurmaRepository tRepository;
    @GetMapping(value = "/{id}")
    public Aluno findAlunoId(@PathVariable Long id){
        return repository.findById(id).get();
    }
    @GetMapping
    public List<AlunoResponse> findAllAluno(){
        List<Aluno> alunos = repository.findAll();
        List<AlunoResponse> alunoResponseList =new ArrayList<AlunoResponse>();
        alunos.forEach(aluno -> {
            AlunoResponse alunoResponse = new AlunoResponse();
            List<TurmaResponse> turmaResponseList = new ArrayList<TurmaResponse>();
            alunoResponse.setEmail(aluno.getEmail());
            alunoResponse.setName(aluno.getName());
            aluno.getTurmas().forEach(turma -> {
                TurmaResponse turmaResponse = new TurmaResponse(turma.getName(),turma.getProfessor().getName(),turma.getDetails());
                turmaResponseList.add(turmaResponse);
            });
            alunoResponse.setTurmas(turmaResponseList);
            alunoResponseList.add(alunoResponse);
        });
        return alunoResponseList;
    }
    @PostMapping(value = "/create")
    public Integer createAluno(@RequestBody CreateAlunoDto aluno){
        Aluno alunocreate = new Aluno();
        alunocreate.setName(aluno.name);
        alunocreate.setEmail(aluno.email);
        Turma turma = tRepository.findById(aluno.turma_id).get();
        List<Turma> turmas = new ArrayList<Turma>();
        turmas.add(turma);
        alunocreate.setTurmas(turmas);
        Aluno alunoResponse = repository.save(alunocreate);
        return alunoResponse.getTurmas().size();
    }
}
