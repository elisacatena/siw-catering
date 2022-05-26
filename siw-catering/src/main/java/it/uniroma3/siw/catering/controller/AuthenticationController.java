package it.uniroma3.siw.catering.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import it.uniroma3.siw.catering.controller.validator.CredenzialiValidator;
import it.uniroma3.siw.catering.controller.validator.AdminValidator;
import it.uniroma3.siw.catering.model.Credenziali;
import it.uniroma3.siw.catering.model.Admin;
import it.uniroma3.siw.catering.service.CredenzialiService;

@Controller
public class AuthenticationController {
	
	@Autowired
	private CredenzialiService credenzialiService;
	
	@Autowired
	private AdminValidator adminValidator;
	
	@Autowired
	private CredenzialiValidator credenzialiValidator;
	
	@RequestMapping(value = "/register", method = RequestMethod.GET) 
	public String showRegisterForm (Model model) {
		model.addAttribute("admin", new Admin());
		model.addAttribute("credenziali", new Credenziali());
		return "registerAdmin";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.GET) 
	public String showLoginForm (Model model) {
		return "loginForm";
	}
	
	@RequestMapping(value = "/logout", method = RequestMethod.GET) 
	public String logout(Model model) {
		return "index";
	}
	
    @RequestMapping(value = "/default", method = RequestMethod.GET)
    public String defaultAfterLogin(Model model) {
        
    	UserDetails adminDetails = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    	Credenziali credenziali = credenzialiService.getCredenziali(adminDetails.getUsername());
    	if (credenziali.getRole().equals(Credenziali.ADMIN_ROLE)) {
            return "/administration";
        }
        return "index";
    }
	
    @RequestMapping(value = { "/register" }, method = RequestMethod.POST)
    public String registerAdmin(@ModelAttribute("admin") Admin admin,
                 BindingResult adminBindingResult,
                 @ModelAttribute("credenziali") Credenziali credenziali,
                 BindingResult credenzialiBindingResult,
                 Model model) {

        // validate user and credentials fields
        this.adminValidator.validate(admin, adminBindingResult);
        this.credenzialiValidator.validate(credenziali, credenzialiBindingResult);

        // if neither of them had invalid contents, store the User and the Credentials into the DB
        if(!adminBindingResult.hasErrors() && ! credenzialiBindingResult.hasErrors()) {
            // set the user and store the credentials;
            // this also stores the User, thanks to Cascade.ALL policy
        	credenziali.setAdmin(admin);
        	credenzialiService.saveCredenziali(credenziali);
            return "registrationSuccessful";
        }
        return "registerAdmin";
    }
}
