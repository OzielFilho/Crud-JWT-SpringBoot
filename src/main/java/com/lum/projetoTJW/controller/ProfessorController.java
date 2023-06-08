package com.lum.projetoTJW.controller;

import com.lum.projetoTJW.dto.NewTurmaProfessorDto;
import com.lum.projetoTJW.dto.ProfessorDto;
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
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "api/v1/professores")
public class ProfessorController {
    @Autowired
    private IProfessorRepository repository;
    @Autowired
    private ITurmaRepository repositoryTurma;

    @PostMapping(value = "/create")
    public ModelAndView  create(ProfessorDto professor) {
        Professor newProfessor = new Professor();
        newProfessor.setEmail(professor.getEmail());
        newProfessor.setName(professor.getName());
        repository.save(newProfessor);
        return new ModelAndView("redirect:/home");
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
    public ResponseEntity<Object> findProfessorById(@PathVariable Long id) {
        Optional professor = repository.findById(id);
        if(!(professor.isPresent())){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Professor não encontrado");
        }
        Professor professorGet = (Professor) professor.get();
        List<Turma> turmas = professorGet.getTurmas();
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

        ProfessorResponse professorResponse = new ProfessorResponse(professorGet.getName(), professorGet.getEmail(),
                turmasResponse);
        return ResponseEntity.status(HttpStatus.FOUND).body(professorResponse);
    }

    @PostMapping(value = "/addNewTurma")
    public Object addNewTurmaInProfessor(NewTurmaProfessorDto newTurmaAlunoDto) {
        Optional professor = repository.findById(newTurmaAlunoDto.getIdProfessor());
        if(!(professor.isPresent())){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Professor não encontrado");
        }
        Optional turma = repositoryTurma.findById(newTurmaAlunoDto.getIdTurma());
        if(!(turma.isPresent())){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Turma não encontrada");
        }
        Professor professorGet = (Professor) professor.get();
        Turma turmaGet = (Turma) turma.get();
        professorGet.getTurmas().add(turmaGet);
        Professor update = repository.save(professorGet);
        return new ModelAndView("redirect:/home");
    }
}
