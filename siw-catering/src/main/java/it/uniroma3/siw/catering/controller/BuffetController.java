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
import it.uniroma3.siw.catering.model.Piatto;
import it.uniroma3.siw.catering.service.BuffetService;
import it.uniroma3.siw.catering.service.ChefService;
import it.uniroma3.siw.catering.service.PiattoService;

@Controller
public class BuffetController {

	@Autowired
	private BuffetService buffetService;
	
	@Autowired
	private BuffetValidator buffetValidator;
	
	@Autowired 
	private PiattoService piattoService;
	
	@Autowired
	private ChefService chefService;
	
	@GetMapping("/admin/buffet_management")
	public String getAllBuffets(Model model) {
		List<Buffet> buffets = this.buffetService.findAll();
		model.addAttribute("buffets", buffets);
		return "admin/buffet/buffet_management.html";
	}
	
	@GetMapping("/admin/buffet_management/create_buffet")
	public String showAddBuffetForm(Model model) {
		model.addAttribute("buffet", new Buffet());
		List<Piatto> piatti = this.piattoService.findAll();
		model.addAttribute("piatti", piatti);
		List<Chef> chefs = this.chefService.findAll();
		model.addAttribute("chefs", chefs); 
		return "admin/buffet/create_buffet.html";
	}
	
	@Transactional
	@PostMapping("/admin/buffet_management/add_buffet")
	public String addBuffet(@Valid @ModelAttribute("buffet") Buffet buffet, BindingResult bindingResult, Model model) {
		this.buffetValidator.validate(buffet, bindingResult);
		if(!bindingResult.hasErrors()) {    
			this.buffetService.save(buffet);
			model.addAttribute("buffet", buffet);
			return "redirect:/admin/buffet_management";   
		}
		else {
			return "admin/buffet/create_buffet.html";
		}
	}
	
	@GetMapping("/admin/buffet_management/edit_buffet/{id}")
	public String  showEditBuffetForm(@PathVariable Long id, Model model) {
		model.addAttribute("buffet", buffetService.findById(id));
		List<Piatto> piatti = this.piattoService.findAll();
		model.addAttribute("piatti", piatti);
		List<Chef> chefs = this.chefService.findAll();
		model.addAttribute("chefs", chefs);
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
			buffetToUpdate.setPiatti(buffet.getPiatti());
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
	
	@GetMapping("/admin/buffet_management/buffet_details/{id}")
	public String showBuffetDetails(@PathVariable Long id, Model model) {
		model.addAttribute("buffet", this.buffetService.findById(id));
		return "admin/buffet/buffet_details.html";
	}
	
	@GetMapping("/buffet/{id}")    //id è un parametro, non è la stringa id
	/* l'annotazione @PathVariable indica che Long id viene dal path */
	public String getBuffet(@PathVariable("id") Long id, Model model) {
		Buffet buffet = this.buffetService.findById(id);
		model.addAttribute("buffet", buffet);
		return "buffet.html";
	}


}
