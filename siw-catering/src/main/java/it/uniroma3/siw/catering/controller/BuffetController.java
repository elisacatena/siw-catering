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

import it.uniroma3.siw.catering.controller.validator.BuffetValidator;
import it.uniroma3.siw.catering.model.Buffet;
import it.uniroma3.siw.catering.service.BuffetService;

@Controller
public class BuffetController {

	@Autowired
	private BuffetService buffetService;
	
	@Autowired
	private BuffetValidator buffetValidator;
	
	@PostMapping("/buffet")
	public String addBuffet(@Valid @ModelAttribute("buffet") Buffet buffet, BindingResult bindingResult, Model model) {
		
		this.buffetValidator.validate(buffet, bindingResult);
		
		if(!bindingResult.hasErrors()) {     //verifichiamo che non ci siano errori di validazione
			this.buffetService.save(buffet);
			model.addAttribute("buffet", buffet);
			return "buffet.html";   //prossima vista
		}
		else {
			return "index.html";
		}
	}
		
	@GetMapping("/buffet/{id}")    //id è un parametro, non è la stringa id
	/* l'annotazione @PathVariable indica che Long id viene dal path */
	public String getBuffet(@PathVariable("id") Long id, Model model) {
		Buffet buffet = this.buffetService.findById(id);
		model.addAttribute("buffet", buffet);
		return "buffet.html";
	}
	
	
	/* richiede tutti gli chef */
	@GetMapping("/buffet")
	public String getBuffets(Model model) {
		List<Buffet> buffets = new ArrayList<>();
		buffets = this.buffetService.findAll();
		model.addAttribute("buffets", buffets);
		return "buffets.html";
	}

}
