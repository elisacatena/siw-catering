package it.uniroma3.siw.catering.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import it.uniroma3.siw.catering.controller.validator.PiattoValidator;
import it.uniroma3.siw.catering.service.PiattoService;

@Controller
public class PiattoController {

	@Autowired
	private PiattoService piattoService;
	
	@Autowired
	private PiattoValidator piattoValidator;
}
