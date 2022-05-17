package it.uniroma3.siw.catering.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import it.uniroma3.siw.catering.controller.validator.ChefValidator;
import it.uniroma3.siw.catering.service.ChefService;

@Controller
public class ChefController {

	@Autowired
	private ChefService chefService;
	
	@Autowired
	private ChefValidator chefValidator;
}
