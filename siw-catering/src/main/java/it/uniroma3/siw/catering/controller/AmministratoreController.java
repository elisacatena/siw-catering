package it.uniroma3.siw.catering.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AmministratoreController {

	@GetMapping("/amministrazione")
	public String getPaginaAmministrazione(Model model) {
		return "amministrazione.html";
	}
}
