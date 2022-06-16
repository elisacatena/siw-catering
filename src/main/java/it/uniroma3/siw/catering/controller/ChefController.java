package it.uniroma3.siw.catering.controller;

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
import it.uniroma3.siw.catering.service.BuffetService;
import it.uniroma3.siw.catering.service.ChefService;

@Controller
public class ChefController {

	@Autowired
	private ChefService chefService;
	
	@Autowired
	private ChefValidator chefValidator;

	@Autowired
	private BuffetService buffetService;
	
	@GetMapping("/admin/chef_management")
	public String getAllChefs(Model model) {
		model.addAttribute("chefs", this.chefService.findAll());
		return "admin/chef/chef_management.html";
	}
	
	@GetMapping("/admin/chef_management/create_chef")
	public String showAddChefForm(Model model) {
		model.addAttribute("chef", new Chef());
		return "admin/chef/create_chef.html";
	}
	
	@PostMapping("/admin/chef_management/add_chef") 
	public String addChef(@Valid @ModelAttribute("chef") Chef chef, BindingResult bindingResult) {		
		this.chefValidator.validate(chef, bindingResult);
		if(!bindingResult.hasErrors()) {     
			this.chefService.save(chef);
			return "redirect:/admin/chef_management";   
		}
		else {
			return "admin/chef/create_chef.html";
		}
	}
	
	@GetMapping("/admin/chef_management/edit_chef/{id}")
	public String  showEditChefForm(@PathVariable Long id, Model model) {
		model.addAttribute("chef", chefService.findById(id));
		return "admin/chef/edit_chef.html";
	}
	
	@PostMapping("/admin/chef_management/{id}")
	public String editChef(@PathVariable Long id, @Valid @ModelAttribute("chef") Chef chef, BindingResult bindingResult) {		
		this.chefValidator.validate(chef, bindingResult);
		if (!bindingResult.hasErrors()){ 
			this.chefService.save(chef);
			return "redirect:/admin/chef_management";
		} 
		else {
			return "admin/chef/edit_chef.html";
		}
	}
	
	@GetMapping("/admin/chef_management/delete_chef/{id}")
	public String deleteChef(@PathVariable Long id) {
		this.chefService.deleteById(id);
		return "redirect:/admin/chef_management";
	}
	
	@GetMapping("/admin/chef_management/chef_details/{id}")
	public String showChefDetails(@PathVariable Long id, Model model) {
		model.addAttribute("chef", this.chefService.findById(id));
		return "admin/chef/chef_details.html";
	}
		
	@GetMapping("/chef/{id}")  
	public String getChef(@PathVariable("id") Long id, Model model) {
		model.addAttribute("chef", this.chefService.findById(id));
		model.addAttribute("chefs", this.chefService.findAll());
		model.addAttribute("buffets", this.buffetService.findAll());
		return "chef.html";
	}
	
	
}
