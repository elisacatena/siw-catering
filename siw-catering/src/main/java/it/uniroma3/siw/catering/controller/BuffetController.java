package it.uniroma3.siw.catering.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import it.uniroma3.siw.catering.controller.validator.BuffetValidator;
import it.uniroma3.siw.catering.service.BuffetService;

@Controller
public class BuffetController {

	@Autowired
	private BuffetService buffetService;
	
	@Autowired
	private BuffetValidator buffetValidator;
}
