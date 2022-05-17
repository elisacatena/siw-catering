package it.uniroma3.siw.catering.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import it.uniroma3.siw.catering.controller.validator.IngredienteValidator;
import it.uniroma3.siw.catering.service.IngredienteService;

@Controller
public class IngredienteController {

	@Autowired
	private IngredienteService ingredienteService;
	
	@Autowired
	private IngredienteValidator ingredienteValidator;
}
