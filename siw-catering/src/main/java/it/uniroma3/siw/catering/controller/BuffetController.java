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

import it.uniroma3.siw.catering.controller.validator.BuffetValidator;
import it.uniroma3.siw.catering.model.Buffet;
import it.uniroma3.siw.catering.model.Chef;
import it.uniroma3.siw.catering.service.BuffetService;
import it.uniroma3.siw.catering.service.ChefService;

@Controller
public class BuffetController {

	@Autowired
	private BuffetService buffetService;
	
	@Autowired
	private BuffetValidator buffetValidator;
	
	@Autowired 
	private ChefService chefService;
	
	@GetMapping("/admin/buffet_management")
	public String getAllBuffets(Model model) {
		List<Buffet> buffets = this.buffetService.findAll();
		model.addAttribute("buffets", buffets);
		return "admin/buffet/buffet_management.html";
	}
	
	// ritorna tutti i buffet di uno chef per poterli modificare
	@GetMapping("/admin/buffet_management/chef/{chef_id}")
	public String getAllBuffetsPerChef(@PathVariable Long chef_id, Model model) {
		Chef chef = chefService.findById(chef_id);
		model.addAttribute("buffets", buffetService.findAllByChef(chef));
		model.addAttribute("chef", chef);
		
		return "admin/buffet/buffetPerChef_management.html";
	}
	
	@GetMapping("/admin/buffet_management/create_buffet")
	public String getAddBuffetForm(Model model) {
		model.addAttribute("buffet", new Buffet());
		return "admin/buffet/create_buffet.html";
	}
	
	@Transactional
	@PostMapping("/admin/buffet_management/add_buffet/{chefId}")
	public String addBuffet(@PathVariable Long chefId, @Valid @ModelAttribute("buffet") Buffet buffet, BindingResult bindingResult, Model model) {
		
		this.buffetValidator.validate(buffet, bindingResult);
		
		if(!bindingResult.hasErrors()) {     //verifichiamo che non ci siano errori di validazione
			Chef chef = chefService.findById(chefId);
			chef.getBuffet().add(buffet);
			buffet.setChef(chef);
			this.buffetService.save(buffet);
			model.addAttribute("buffet", buffet);
			return "redirect:/admin/chef_management";   
		}
		else {
			return "admin/buffet/create_buffet.html";
		}
	}
	
	@GetMapping("/admin/buffet_management/edit_buffet/{id}")
	public String  getEditBuffetForm(@PathVariable Long id, Model model) {
		model.addAttribute("buffet", buffetService.findById(id));
		return "admin/buffet/edit_buffet.html";
	}
	
	@Transactional
	@PostMapping("/admin/buffet_management/{id}")
	public String editBuffet(@PathVariable Long id, @Valid @ModelAttribute("buffet") Buffet buffet, BindingResult bindingResults, Model model) {
		if(!bindingResults.hasErrors()) {
			Buffet buffetToUpdate = this.buffetService.findById(id);
			buffetToUpdate.setId(buffet.getId());
			buffetToUpdate.setNome(buffet.getNome());
			buffetToUpdate.setDescrizione(buffet.getDescrizione());
			this.buffetService.updateBuffet(buffetToUpdate);
			model.addAttribute("buffet", buffet);
			return "redirect:/admin/buffet_management";
		}
		else
			return "admin/buffet/edit_buffet.html";
	}
	
	@GetMapping("/admin/buffet_management/delete_buffet/{id}")
	public String deleteBuffet(@PathVariable Long id) {
		buffetService.deleteById(id);
		return "redirect:/admin/buffet_management";
	}
	
	@GetMapping("/buffet/{id}")    //id è un parametro, non è la stringa id
	/* l'annotazione @PathVariable indica che Long id viene dal path */
	public String getBuffet(@PathVariable("id") Long id, Model model) {
		Buffet buffet = this.buffetService.findById(id);
		model.addAttribute("buffet", buffet);
		return "buffet.html";
	}
	
	
	/* richiede tutti i buffet */
	@GetMapping("/buffet")
	public String getBuffets(Model model) {
		List<Buffet> buffets = this.buffetService.findAll();
		model.addAttribute("buffets", buffets);
		return "buffets.html";
	}

}
