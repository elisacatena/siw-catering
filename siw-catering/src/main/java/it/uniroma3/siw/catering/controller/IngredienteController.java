package it.uniroma3.siw.catering.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
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
	
	@Transactional
	@PostMapping("/admin/ingrediente_management/add_ingrediente") 
	public String addIngrediente(@Valid @ModelAttribute("ingrediente") Ingrediente ingrediente, BindingResult bindingResult, Model model) {		
		this.ingredienteValidator.validate(ingrediente, bindingResult);
		if(!bindingResult.hasErrors()) {     
			this.ingredienteService.save(ingrediente);
			model.addAttribute("ingrediente", ingrediente);
			System.out.println("CREATOOOOOOOOOOOOOOOOOO");
			//return "redirect:/admin/ingrediente_management";   
			return "admin/ingrediente/ingrediente_management.html";
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
	
	@Transactional
	@PostMapping("/admin/ingrediente_management/{id}")
	public String editIngrediente(@PathVariable Long id, @Valid @ModelAttribute("ingrediente") Ingrediente ingrediente, BindingResult bindingResults, Model model) {
		if(!bindingResults.hasErrors()) {
			Ingrediente ingredienteToUpdate = ingredienteService.findById(id);
			ingredienteToUpdate.setId(ingrediente.getId());
			ingredienteToUpdate.setNome(ingrediente.getNome());
			ingredienteToUpdate.setOrigine(ingrediente.getOrigine());
			ingredienteToUpdate.setDescrizione(ingrediente.getDescrizione());
			this.ingredienteService.updateIngrediente(ingredienteToUpdate);
			model.addAttribute("ingrediente", ingrediente);
			//return "redirect:/admin/ingrediente_management";
			return "admin/ingrediente/ingrediente_management.html";
		}
		else
			//return "admin/ingrediente/edit_ingrediente.html";
			return "admin/ingrediente/edit_management.html";
	}
	
	@Transactional
	@GetMapping("/admin/ingrediente_management/delete_ingrediente/{id}")
	public String deleteIngrediente(@PathVariable Long id) {
		this.ingredienteService.deleteById(id);
		return "admin/ingrediente/ingrediente_management";
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
