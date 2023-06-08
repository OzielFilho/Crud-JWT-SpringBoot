package com.lum.projetoTJW.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

	@GetMapping("/")
	public ModelAndView index() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("home/index");
		return mv;
	}
	@GetMapping("/createProfessor")
	public ModelAndView createProfessor() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("home/createProfessor");
		return mv;
	}
	@GetMapping("/createAluno")
	public ModelAndView createAluno() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("home/createAluno");
		return mv;
	}
	@GetMapping("/createTurma")
	public ModelAndView createTurma() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("home/createTurma");
		return mv;
	}
}
