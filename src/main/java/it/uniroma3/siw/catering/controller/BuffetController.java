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
		model.addAttribute("piatti", this.piattoService.findAll());
		model.addAttribute("chefs", this.chefService.findAll()); 
		return "admin/buffet/create_buffet.html";
	}

	@PostMapping("/admin/buffet_management/add_buffet")
	public String addBuffet(@Valid @ModelAttribute("buffet") Buffet buffet, BindingResult bindingResult, Model model) {
		this.buffetValidator.validate(buffet, bindingResult);
		if(!bindingResult.hasErrors()) {    
			this.buffetService.save(buffet);
			return "redirect:/admin/buffet_management";   
		}
		else {
			List<Piatto> piatti = this.piattoService.findAll();
			model.addAttribute("piatti", piatti);
			List<Chef> chefs = this.chefService.findAll();
			model.addAttribute("chefs", chefs); 
			return "admin/buffet/create_buffet.html";
		}
	}

	@GetMapping("/admin/buffet_management/edit_buffet/{id}")
	public String  showEditBuffetForm(@PathVariable Long id, Model model) {
		model.addAttribute("buffet", buffetService.findById(id));
		model.addAttribute("piatti", this.piattoService.findAll());
		model.addAttribute("chefs", this.chefService.findAll()); 
		return "admin/buffet/edit_buffet.html";
	}

	@PostMapping("/admin/buffet_management/{id}")
	public String editBuffet(@PathVariable Long id, @Valid @ModelAttribute("buffet") Buffet buffet, BindingResult bindingResult, Model model) {
		this.buffetValidator.validate(buffet, bindingResult);
		Buffet buffetVecchio = this.buffetService.findById(id);
		boolean verificato = false;
		if(buffet.getPiatti().size() == 0) {
			verificato = buffetVecchio.getPiatti().size() > 0;
		}
		else if(buffet.getPiatti().size() == buffetVecchio.getPiatti().size()) {
			for(Piatto piatto : buffet.getPiatti()) {
				System.out.println(buffet.getNome());
				if(!buffetVecchio.getPiatti().contains(piatto)) {
					verificato = true;
					break; 
				}
			}
		}
		else 
			verificato = true;

		if(verificato || ( buffetVecchio.getChef() != buffet.getChef() ) || ( !bindingResult.hasErrors() )) {
			this.buffetService.save(buffet);
			return "redirect:/admin/buffet_management";
		}
		
		model.addAttribute("piatti", this.piattoService.findAll());
		model.addAttribute("chefs", this.chefService.findAll());
		return "admin/buffet/edit_buffet.html";
	}

	@GetMapping("/admin/buffet_management/delete_buffet/{id}")
	public String deleteBuffet(@PathVariable Long id) {
		this.buffetService.deleteById(id);
		return "redirect:/admin/buffet_management";
	}

	@GetMapping("/admin/buffet_management/buffet_details/{id}")
	public String showBuffetDetails(@PathVariable Long id, Model model) {
		model.addAttribute("buffet", this.buffetService.findById(id));
		return "admin/buffet/buffet_details.html";
	}

	@GetMapping("/buffet/{id}")   
	public String getBuffet(@PathVariable("id") Long id, Model model) {
		model.addAttribute("buffet", this.buffetService.findById(id));
		model.addAttribute("chefs", this.chefService.findAll());
		model.addAttribute("buffets", this.buffetService.findAll());
		return "buffet.html";
	}


}
