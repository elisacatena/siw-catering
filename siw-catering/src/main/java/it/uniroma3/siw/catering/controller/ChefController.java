package it.uniroma3.siw.catering.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import it.uniroma3.siw.catering.controller.validator.ChefValidator;
import it.uniroma3.siw.catering.model.Chef;
import it.uniroma3.siw.catering.service.ChefService;

@Controller
public class ChefController {

	@Autowired
	private ChefService chefService;
	
	@Autowired
	private ChefValidator chefValidator;
	
	@PostMapping("/chef")
	public String addChef(@Valid @ModelAttribute("chef") Chef chef, BindingResult bindingResult, Model model) {
		//vogliamo che l'ogetto chef sia validato (@Valid) e che sia legato al modello (@ModelAttribute("chef"))
		
		this.chefValidator.validate(chef, bindingResult);
		
		if(!bindingResult.hasErrors()) {     //verifichiamo che non ci siano errori di validazione
			this.chefService.save(chef);
			model.addAttribute("chef", chef);
			return "chef.html";   //prossima vista
		}
		else {
			return "index.html";
		}
	}
		
	@GetMapping("/chef/{id}")    //id è un parametro, non è la stringa id
	/* l'annotazione @PathVariable indica che Long id viene dal path */
	public String getChef(@PathVariable("id") Long id, Model model) {
		Chef chef = this.chefService.findById(id);
		model.addAttribute("chef", chef);
		return "chef.html";
	}
	
	
	/* richiede tutti gli chef */
	@GetMapping("/chef")
	public String getChefs(Model model) {
		List<Chef> chefs = new ArrayList<Chef>();
		chefs = this.chefService.findAll();
		model.addAttribute("chefs", chefs);
		return "chefs.html";
	}

}
