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

import it.uniroma3.siw.catering.controller.validator.PiattoValidator;
import it.uniroma3.siw.catering.model.Buffet;
import it.uniroma3.siw.catering.model.Chef;
import it.uniroma3.siw.catering.model.Ingrediente;
import it.uniroma3.siw.catering.model.Piatto;
import it.uniroma3.siw.catering.service.BuffetService;
import it.uniroma3.siw.catering.service.ChefService;
import it.uniroma3.siw.catering.service.IngredienteService;
import it.uniroma3.siw.catering.service.PiattoService;

@Controller
public class PiattoController {

	@Autowired
	private PiattoService piattoService;
	
	@Autowired
	private PiattoValidator piattoValidator;
	
	@Autowired
	private IngredienteService ingredienteService;
	
	@Autowired
	private BuffetService buffetService;
	
	@Autowired 
	private ChefService chefService;
	
	@GetMapping("/admin/piatto_management")
	public String getAllPiatti(Model model) {
		List<Piatto> piatti = this.piattoService.findAll();
		model.addAttribute("piatti", piatti);
		return "admin/piatto/piatto_management.html";
	}
	
	@GetMapping("/admin/piatto_management/create_piatto")
	public String showAddPiattoForm(Model model) {
		model.addAttribute("piatto", new Piatto());
		List<Ingrediente> ingredienti = this.ingredienteService.findAll();
		model.addAttribute("ingredienti", ingredienti);
		return "admin/piatto/create_piatto.html";
	}
	
	@Transactional
	@PostMapping("/admin/piatto_management/add_piatto") 
	public String addPiatto(@Valid @ModelAttribute("piatto") Piatto piatto, BindingResult bindingResult, Model model) {		
		this.piattoValidator.validate(piatto, bindingResult);
		if(!bindingResult.hasErrors()) {     
			this.piattoService.save(piatto);
			model.addAttribute("piatto", piatto);
			return "redirect:/admin/piatto_management";   
		}
		else {
			return "admin/piatto/create_piatto.html";
		}
	}
	
	@GetMapping("/admin/piatto_management/edit_piatto/{id}")
	public String  showEditPiattoForm(@PathVariable Long id, Model model) {
		model.addAttribute("piatto", piattoService.findById(id));
		List<Ingrediente> ingredienti = this.ingredienteService.findAll();
		model.addAttribute("ingredienti", ingredienti);
		return "admin/piatto/edit_piatto.html";
	}
	
	@Transactional
	@PostMapping("/admin/piatto_management/{id}")
	public String editPiatto(@PathVariable Long id, @Valid @ModelAttribute("piatto") Piatto piatto, BindingResult bindingResults, Model model) {
		if(!bindingResults.hasErrors()) {
			Piatto piattoToUpdate = piattoService.findById(id);
			piattoToUpdate.setId(piatto.getId());
			piattoToUpdate.setNome(piatto.getNome());
			piattoToUpdate.setDescrizione(piatto.getDescrizione());
			piattoToUpdate.setIngredienti(piatto.getIngredienti());
			this.piattoService.updatePiatto(piattoToUpdate);
			model.addAttribute("piatto", piatto);
			return "redirect:/admin/piatto_management";
		}
		else
			return "admin/piatto/edit_piatto.html";
	}
	
	@Transactional
	@GetMapping("/admin/piatto_management/delete_piatto/{id}")
	public String deletePiatto(@PathVariable Long id) {
		this.piattoService.deleteById(id);
		return "redirect:/admin/piatto_management";
	}
	
	@GetMapping("/admin/piatto_management/piatto_details/{id}")
	public String showPiattoDetails(@PathVariable Long id, Model model) {
		model.addAttribute("piatto", this.piattoService.findById(id));
		return "admin/piatto/piatto_details.html";
	}
	
	@GetMapping("/piatto/{id}")
	public String getPiatto(@PathVariable("id") Long id, Model model) {
		Piatto piatto= this.piattoService.findById(id);
		model.addAttribute("piatto", piatto);
		List<Chef> chefs = this.chefService.findAll();
		model.addAttribute("chefs", chefs);
		List<Buffet> buffets = this.buffetService.findAll();
		model.addAttribute("buffets", buffets);
		return "piatto.html";
	}
	
	@GetMapping("/piatto")
	public String getPiatti(Model model) {
		List<Piatto> piatti = this.piattoService.findAll();
		model.addAttribute("piatti", piatti);
		return "piatti.html";
	}

	
}
