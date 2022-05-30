package it.uniroma3.siw.catering.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import it.uniroma3.siw.catering.model.Buffet;
import it.uniroma3.siw.catering.model.Chef;
import it.uniroma3.siw.catering.service.BuffetService;
import it.uniroma3.siw.catering.service.ChefService;

public class IndexController {

	@Autowired
	private BuffetService buffetService;
	
	@Autowired 
	private ChefService chefService;
	
	/* richiede tutti gli chef e i buffet */
	@GetMapping("/")
	public String getAllChef(Model model) {
		List<Chef> chefs = this.chefService.findAll();
		model.addAttribute("chefs", chefs);
		List<Buffet> buffets = this.buffetService.findAll();
		model.addAttribute("buffets", buffets);
		return "/index";
	}
}
