package it.uniroma3.siw.catering.controller;

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

import it.uniroma3.siw.catering.controller.validator.IngredienteValidator;
import it.uniroma3.siw.catering.model.Ingrediente;
import it.uniroma3.siw.catering.service.IngredienteService;

@Controller
public class IngredienteController {

	@Autowired
	private IngredienteService ingredienteService;
	
	@Autowired
	private IngredienteValidator ingredienteValidator;
	
	@PostMapping("/ingrediente")
	public String addIngrediente(@Valid @ModelAttribute("ingrediente") Ingrediente ingrediente, BindingResult bindingResult, Model model) {		
		this.ingredienteValidator.validate(ingrediente, bindingResult);
		
		if(!bindingResult.hasErrors()) {     //verifichiamo che non ci siano errori di validazione
			this.ingredienteService.save(ingrediente);
			model.addAttribute("ingrediente", ingrediente);
			return "ingrediente.html";   //prossima vista
		}
		else {
			return "index.html";
		}
	}
		
	@GetMapping("/ingrediente/{id}")
	public String getIngrediente(@PathVariable("id") Long id, Model model) {
		Ingrediente ingrediente = this.ingredienteService.findById(id);
		model.addAttribute("ingrediente", ingrediente);
		return "ingrediente.html";
	}
	
	@GetMapping("/ingrediente")
	public String getIngredienti(Model model) {
		List<Ingrediente> ingredienti = this.ingredienteService.findAll();
		model.addAttribute("ingredienti", ingredienti);
		return "ingredienti.html";
	}
}
