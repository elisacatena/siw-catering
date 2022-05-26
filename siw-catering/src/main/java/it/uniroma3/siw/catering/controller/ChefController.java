package it.uniroma3.siw.catering.controller;

import java.util.ArrayList;
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

import it.uniroma3.siw.catering.controller.validator.ChefValidator;
import it.uniroma3.siw.catering.model.Chef;
import it.uniroma3.siw.catering.service.ChefService;

@Controller
public class ChefController {

	@Autowired
	private ChefService chefService;
	
	@Autowired
	private ChefValidator chefValidator;
	
	@GetMapping("/admin/chef_management")
	public String getAllChefs(Model model) {
		List<Chef> chefs = new ArrayList<>();
		chefs = this.chefService.findAll();
		model.addAttribute("chefs", chefs);
		return "admin/chef/chef_management.html";
	}
	
	@GetMapping("/admin/chef_management/create_chef")
	public String chefFormCreazione(Model model) {
		model.addAttribute("chef", new Chef());
		return "admin/chef/create_chef.html";
	}
	
	@Transactional
	@PostMapping("/admin/chef_management/add_chef") 
	public String addChef(@Valid @ModelAttribute("chef") Chef chef, BindingResult bindingResult, Model model) {		
		this.chefValidator.validate(chef, bindingResult);
		if(!bindingResult.hasErrors()) {     
			this.chefService.save(chef);
			model.addAttribute("chef", chef);
			System.out.println("CREATOOOOOOOOOOOOOOOOOO");
			return "redirect:/admin/chef_management";   
		}
		else {
			return "admin/chef/create_chef.html";
		}
	}
	
	@GetMapping("/admin/chef_management/edit_chef/{id}")
	public String  chefFormModifica(@PathVariable Long id, Model model) {
		model.addAttribute("chef", chefService.findById(id));
		return "admin/chef/edit_chef.html";
	}
	
	@Transactional
	@PostMapping("/admin/chef_management/{id}")
	public String updateChef(@PathVariable Long id, @Valid @ModelAttribute("chef") Chef chef, BindingResult bindingResults, Model model) {
		if(!bindingResults.hasErrors()) {
			Chef chefToUpdate = chefService.findById(id);
			chefToUpdate.setId(chef.getId());
			chefToUpdate.setNome(chef.getNome());
			chefToUpdate.setCognome(chef.getCognome());
			chefToUpdate.setNazionalita(chef.getNazionalita());
			this.chefService.updateChef(chefToUpdate);
			model.addAttribute("chef", chef);
			return "redirect:/admin/chef_management";
		}
		else
			return "admin/chef/edit_chef.html";
	}
	
	@Transactional
	@GetMapping("/admin/chef_management/delete_chef/{id}")
	public String deleteChef(@PathVariable Long id) {
		this.chefService.deleteById(id);
		return "redirect:/admin/chef_management";
	}
		
	@GetMapping("/chef/{id}")  
	public String getChef(@PathVariable("id") Long id, Model model) {
		Chef chef = this.chefService.findById(id);
		model.addAttribute("chef", chef);
		List<Chef> chefs = this.chefService.findAll();
		model.addAttribute("chefs", chefs);
		return "chef.html";
	}
	
	
//	/* richiede tutti gli chef */
//	@GetMapping//("/public/chef")
//	public String getChefs(Model model) {
//		List<Chef> chefs = this.chefService.findAll();
//		model.addAttribute("chefs", chefs);
//		return "chefs.html";
//	}
	
	/* richiede tutti gli chef */
	@GetMapping("/")
	public String getAllChef(Model model) {
		List<Chef> chefs = this.chefService.findAll();
		model.addAttribute("chefs", chefs);
		return "index.html";
	}
	
	

}
