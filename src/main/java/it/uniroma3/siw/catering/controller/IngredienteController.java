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
import it.uniroma3.siw.catering.model.Buffet;
import it.uniroma3.siw.catering.model.Chef;
import it.uniroma3.siw.catering.model.Ingrediente;
import it.uniroma3.siw.catering.model.Piatto;
import it.uniroma3.siw.catering.service.BuffetService;
import it.uniroma3.siw.catering.service.ChefService;
import it.uniroma3.siw.catering.service.IngredienteService;
import it.uniroma3.siw.catering.service.PiattoService;

@Controller
public class IngredienteController {

	@Autowired
	private IngredienteService ingredienteService;
	
	@Autowired
	private IngredienteValidator ingredienteValidator;

	@Autowired
	private ChefService chefService;

	@Autowired
	private BuffetService buffetService;
	
	@Autowired
	private PiattoService piattoService;
	
	@GetMapping("/admin/ingrediente_management")
	public String getAllIngredienti(Model model) {
		List<Ingrediente> ingredienti = this.ingredienteService.findAll();
		model.addAttribute("ingredienti", ingredienti);
		return "admin/ingrediente/ingrediente_management.html";
	}
	
	@GetMapping("/admin/ingrediente_management/create_ingrediente")
	public String getAddIngredienteForm(Model model) {
		model.addAttribute("ingrediente", new Ingrediente());
		return "admin/ingrediente/create_ingrediente.html";
	}
	
	@PostMapping("/admin/ingrediente_management/add_ingrediente") 
	public String addIngrediente(@Valid @ModelAttribute("ingrediente") Ingrediente ingrediente, BindingResult bindingResult, Model model) {		
		this.ingredienteValidator.validate(ingrediente, bindingResult);
		if(!bindingResult.hasErrors()) {     
			this.ingredienteService.save(ingrediente);
			model.addAttribute("ingrediente", ingrediente);
			return "redirect:/admin/ingrediente_management";   
		}
		else {
			return "admin/ingrediente/create_ingrediente.html";
		}
	}
	
	@GetMapping("/admin/ingrediente_management/edit_ingrediente/{id}")
	public String  getEditIngredienteForm(@PathVariable Long id, Model model) {
		model.addAttribute("ingrediente", ingredienteService.findById(id));
		return "admin/ingrediente/edit_ingrediente.html";
	}
	
	@PostMapping("/admin/ingrediente_management/{id}")
	public String editIngrediente(@PathVariable Long id, @Valid @ModelAttribute("ingrediente") Ingrediente ingrediente, BindingResult bindingResult, Model model) {
		this.ingredienteValidator.validate(ingrediente, bindingResult);
		if (!bindingResult.hasErrors()){ // se i dati sono corretti
			this.ingredienteService.save(ingrediente);
			return "redirect:/admin/ingrediente_management";
		} 
		else {
			return "admin/ingrediente/edit_ingrediente.html";
		}
	}
	
	@GetMapping("/admin/ingrediente_management/delete_ingrediente/{id}")
	public String deleteIngrediente(@PathVariable Long id, Model model) {
		boolean presenteInPiatto = false;
		Ingrediente ingrediente = this.ingredienteService.findById(id);
		for(Piatto p : this.piattoService.findAll()) {
			if(p.getIngredienti().contains(ingrediente)) {
				presenteInPiatto = true;
				break;
			}
		}
		model.addAttribute("ingr", ingrediente);
		if(!presenteInPiatto) {
			this.ingredienteService.deleteById(id);
			return "redirect:/admin/ingrediente_management";
		}
		else {
			List<Ingrediente> ingredienti = this.ingredienteService.findAll();
			model.addAttribute("ingredienti", ingredienti);
			return "admin/ingrediente/ingrediente_management";
		}
	}
	
	@GetMapping("/ingrediente/{id}")
	public String getIngrediente(@PathVariable("id") Long id, Model model) {
		Ingrediente ingrediente = this.ingredienteService.findById(id);
		model.addAttribute("ingrediente", ingrediente);
		List<Chef> chefs = this.chefService.findAll();
		model.addAttribute("chefs", chefs);
		List<Buffet> buffets = this.buffetService.findAll();
		model.addAttribute("buffets", buffets);
		return "ingrediente.html";
	}
	
	@GetMapping("/ingrediente")
	public String getIngredienti(Model model) {
		List<Ingrediente> ingredienti = this.ingredienteService.findAll();
		model.addAttribute("ingredienti", ingredienti);
		return "ingredienti.html";
	}
}
