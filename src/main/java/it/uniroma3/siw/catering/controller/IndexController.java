package it.uniroma3.siw.catering.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import it.uniroma3.siw.catering.service.BuffetService;
import it.uniroma3.siw.catering.service.ChefService;

@Controller
public class IndexController {
	
	@Autowired
	private ChefService chefService;

	@Autowired
	private BuffetService buffetService;

	/* richiede tutti gli chef */
	@GetMapping("/")
	public String getAllChef(Model model) {
		model.addAttribute("chefs", this.chefService.findAll());
		model.addAttribute("buffets", this.buffetService.findAll());
		return "index.html";
	}
	
	@GetMapping("/contatti")
	public String getContatti(Model model) {
		model.addAttribute("chefs", this.chefService.findAll());
		model.addAttribute("buffets", this.buffetService.findAll());
		return "contatti.html";
	}
	
	@GetMapping("/chi-siamo")
	public String getChiSiamo(Model model) {
		model.addAttribute("chefs", this.chefService.findAll());
		model.addAttribute("buffets", this.buffetService.findAll());
		return "chi_siamo.html";
	}

}
