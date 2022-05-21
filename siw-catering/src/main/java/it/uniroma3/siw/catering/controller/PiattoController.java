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

import it.uniroma3.siw.catering.controller.validator.PiattoValidator;
import it.uniroma3.siw.catering.model.Piatto;
import it.uniroma3.siw.catering.service.PiattoService;

@Controller
public class PiattoController {

	@Autowired
	private PiattoService piattoService;
	
	@Autowired
	private PiattoValidator piattoValidator;
	
	@PostMapping("/piatto")
	public String addPiatto(@Valid @ModelAttribute("piatto") Piatto piatto, BindingResult bindingResult, Model model) {		
		this.piattoValidator.validate(piatto, bindingResult);
		
		if(!bindingResult.hasErrors()) {     //verifichiamo che non ci siano errori di validazione
			this.piattoService.save(piatto);
			model.addAttribute("piatto", piatto);
			return "piatto.html";   //prossima vista
		}
		else {
			return "index.html";
		}
	}
		
	@GetMapping("/piatto/{id}")
	public String getPiatto(@PathVariable("id") Long id, Model model) {
		Piatto piatto= this.piattoService.findById(id);
		model.addAttribute("piatto", piatto);
		return "piatto.html";
	}
	
	@GetMapping("/piatti")
	public String getPiatti(Model model) {
		List<Piatto> piatti = new ArrayList<>();
		piatti = this.piattoService.findAll();
		model.addAttribute("piatti", piatti);
		return "piatti.html";
	}
}
