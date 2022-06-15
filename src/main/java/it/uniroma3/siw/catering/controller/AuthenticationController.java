package it.uniroma3.siw.catering.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import it.uniroma3.siw.catering.model.Credenziali;
import it.uniroma3.siw.catering.service.CredenzialiService;

@Controller
public class AuthenticationController {
	
	@Autowired
	private CredenzialiService credenzialiService;
	
	@GetMapping("/login") 
	public String showLoginForm (Model model) {
		return "loginForm";
	}
	
	@GetMapping("/logout") 
	public String logout(Model model) {
		return "redirect:/";
	}
	
    @GetMapping("/default")
    public String defaultAfterLogin(Model model) {
        
    	UserDetails adminDetails = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    	Credenziali credenziali = credenzialiService.getCredenziali(adminDetails.getUsername());
    	if (credenziali.getRole().equals(Credenziali.ADMIN_ROLE)) {
            return "administration";
        }
        return "loginForm";
    }
	
}
