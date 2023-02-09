package it.uniroma3.CO.spring.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import it.uniroma3.CO.spring.model.Buffet;
import it.uniroma3.CO.spring.model.Chef;
import it.uniroma3.CO.spring.model.Piatto;
import it.uniroma3.CO.spring.model.Credentials;
import it.uniroma3.CO.spring.model.Ingrediente;
import it.uniroma3.CO.spring.controller.validator.CredentialsValidator;
import it.uniroma3.CO.spring.service.BuffetService;
import it.uniroma3.CO.spring.service.ChefService;
import it.uniroma3.CO.spring.service.CredentialsService;
import it.uniroma3.CO.spring.service.PiattoService;


@Controller
public class PiattoController {
	@Autowired
	CredentialsService credentialservice;
	@Autowired
	PiattoService piattoservice;
	
	
	
	
	
	@GetMapping("/piattolist")
	public String buffet(Model model) {
		Credentials utente = credentialservice.getCurrentCredentials();	
		model.addAttribute("utente", utente);
		String paginaVuota = "si";
		List<Piatto> piatti = new ArrayList<>();
		piatti = piattoservice.getAllPiatto();
		if(!piatti.isEmpty()) {
			paginaVuota="no";
		}
		
		model.addAttribute("paginaVuota", paginaVuota);
		model.addAttribute("piatti", piatti);
		return "piattilist";
	}
	
	
	
	
	
	
	
	
	
	
	
	@GetMapping("/piatto/{id}")
	public String piatto(@PathVariable("id") long id, Model model) {
		Credentials utente = credentialservice.getCurrentCredentials();	
				
		Piatto piatto = piattoservice.getPiatto(id);
		model.addAttribute("piatto", piatto);
		
		
		model.addAttribute("utente", utente);
		
		List<Ingrediente> ingredienti = piatto.getIngredienti();
		String paginaVuota ="si";
		if(ingredienti!=null) {
			paginaVuota ="no";
		}
		model.addAttribute("paginaVuota", paginaVuota);
		model.addAttribute("ingredienti", ingredienti);
		return "piatto.html";
	}
}
