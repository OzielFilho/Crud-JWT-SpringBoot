package com.lum.projetoTJW.controller;

import com.lum.projetoTJW.dto.ProfessorDto;
import com.lum.projetoTJW.entity.Professor;
import com.lum.projetoTJW.repository.IProfessorRepository;
import com.lum.projetoTJW.response.AlunoResponse;
import com.lum.projetoTJW.response.ProfessorResponse;
import com.lum.projetoTJW.response.TurmaResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class HomeController {
	@Autowired
	private ProfessorController professorController;
	@Autowired
	private TurmaController turmaController;

	@Autowired
	private AlunoController alunoController;
	@GetMapping("/home")
	public ModelAndView index() {
		List<ProfessorResponse> professores = professorController.findAllProfessores();
		List<TurmaResponse> turmas = turmaController.findAllTurmas();
		List<AlunoResponse> alunos = alunoController.findAllAlunos();
		ModelAndView mv = new ModelAndView("home/index");
		mv.addObject("professores",professores);
		mv.addObject("turmas",turmas);
		mv.addObject("alunos",alunos);
		return mv;

	}
	@GetMapping("/createProfessor")
	public String createProfessor() {
		return "home/createProfessor";
	}

	@GetMapping("/addNewTurmaAluno")
	public String addNewTurmaAluno() {
		return "home/addNewTurmaAluno";
	}
	@GetMapping("/addNewTurmaProfessor")
	public String addNewTurmaProfessor() {
		return "home/addNewTurmaProfessor";
	}

	@GetMapping("/createAluno")
	public String createAluno() {return  "home/createAluno";}
	@GetMapping("/createTurma")
	public String createTurma() {return  "home/createTurma";}
}
