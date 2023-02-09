package it.uniroma3.CO.spring.controller;
import java.util.ArrayList;
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

import it.uniroma3.CO.spring.service.BuffetService;
import it.uniroma3.CO.spring.service.CredentialsService;
import it.uniroma3.CO.spring.service.IngredienteService;
import it.uniroma3.CO.spring.service.PiattoService;
import it.uniroma3.CO.spring.model.Buffet;
import it.uniroma3.CO.spring.model.Credentials;
import it.uniroma3.CO.spring.model.Ingrediente;
import it.uniroma3.CO.spring.model.Piatto;




@Controller
public class ProfiloController {
	@Autowired
	CredentialsService credentialservice;
	@Autowired
	BuffetService buffetservice;
	@Autowired
	PiattoService piattoservice;
	
	
	@GetMapping("/like/{id}")
	public String like(@PathVariable("id") Long id,Model model) {
		Credentials utente = credentialservice.getCurrentCredentials();	
		Buffet buffet2 = buffetservice.getBuffet(id);
		if(!utente.getBuffet().contains(buffet2)) {
			utente.getBuffet().add(buffet2);
			buffet2.getUtente().add(utente);
		
			buffetservice.saveBuffet(buffet2);
		}
		if(buffet2.getUtente().contains(utente)) {
			return "redirect:/home";
		}
	
		return "redirect:/profilo";
		
	}
	
	@GetMapping("/profilo")
	public String profilo(Model model) {
		Credentials utente = credentialservice.getCurrentCredentials();	
		model.addAttribute("utente", utente);
		String paginaVuota = "si";
		
		//long id2 = 1;
		//Buffet buffet2 = buffetservice.getBuffet(id2);
		//utente.getBuffet().add(buffet2);
		//buffet2.getUtente().add(utente);
		
		
		List<Buffet> buffet = utente.getBuffet();
		
		//buffet.add(buffet2);

		if(!buffet.isEmpty()) {
			paginaVuota="no";
		}
		
		model.addAttribute("paginaVuota", paginaVuota);
		model.addAttribute("buffet", buffet);
		return "profilo.html";
	}

	
	
	
	
	@GetMapping("/Cancella/{id}")
	public String registraAnnuncio (@PathVariable("id") Long id, Model model) {
	    
		Credentials utente = credentialservice.getCurrentCredentials();	
		Buffet buffet = buffetservice.getBuffet(id);
		
		utente.getBuffet().remove(buffet);
		buffet.getUtente().remove(utente);
		
		credentialservice.saveCredentials2(utente);
		buffetservice.saveBuffet(buffet);
		
		
	    return "redirect:/profilo";
	}

	
	
	
}












