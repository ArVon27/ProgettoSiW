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
import it.uniroma3.CO.spring.service.ChefService;
import it.uniroma3.CO.spring.service.CredentialsService;
import it.uniroma3.CO.spring.service.IngredienteService;
import it.uniroma3.CO.spring.service.PiattoService;
import it.uniroma3.CO.spring.model.Buffet;
import it.uniroma3.CO.spring.model.Chef;
import it.uniroma3.CO.spring.model.Credentials;
import it.uniroma3.CO.spring.model.Ingrediente;
import it.uniroma3.CO.spring.model.Piatto;




@Controller
public class HomeController {
	@Autowired
	CredentialsService credentialservice;
	@Autowired
	BuffetService buffetservice;
	@Autowired
	PiattoService piattoservice;
	@Autowired
	IngredienteService ingredienteservice;
	@Autowired
	ChefService chefservice;
	
	@GetMapping("/home")
	public String home(Model model) {

		
		Credentials utente = credentialservice.getCurrentCredentials();	
		model.addAttribute("utente", utente);
		return "home.html";
	}
	
	@GetMapping("/base")
	public String base(Model model) {
		Long id = (long) 1;
		Long id2 = (long) 2;
		Long id3 = (long) 3;
		Long id4 = (long) 4;
		Credentials utente = credentialservice.getCurrentCredentials();	
		//Buffet buffet2 = buffetservice.getBuffet(id2);
		//utente.getBuffet().add(buffet2);
		//buffet2.getUtente().add(utente);
		
		//Piatto piatto = new Piatto();
		//piatto.setNome("piattoprova");
		//piatto.setDescrizione("sono una descrizione di prova");
		//piattoservice.savePiatto(piatto);
		
		
		//Piatto piatto = piattoservice.getPiatto(id);
		//Ingrediente ingrediente1 = ingredienteservice.getIngrediente(id);
		//Ingrediente ingrediente2 = ingredienteservice.getIngrediente(id3);
		//piatto.getIngredienti().add(ingrediente1);
		//piatto.getIngredienti().add(ingrediente2);
		//ingrediente1.getPiatto().add(piatto);
		//ingrediente2.getPiatto().add(piatto);
		//piattoservice.savePiatto(piatto);
		//ingredienteservice.saveIngrediente(ingrediente1);
		//ingredienteservice.saveIngrediente(ingrediente2);
		
		
		//Buffet buffet = buffetservice.getBuffet(id3);
		//Buffet buffet2 = buffetservice.getBuffet(id4);
		//Chef chef = chefservice.getChef(id);
		//Chef chef2 = chefservice.getChef(id3);
		//chef.getBuffet().add(buffet);
		//chef2.getBuffet().add(buffet2);
		//buffet.setChef(chef);
		//buffet2.setChef(chef2);
		//chefservice.saveChef(chef);
		//chefservice.saveChef(chef2);
		//buffetservice.saveBuffet(buffet);
		//buffetservice.saveBuffet(buffet2);
		
		
		//Buffet buffet = buffetservice.getBuffet(id);
		//Piatto piatto3 = piattoservice.getPiatto(id);
		//Piatto piatto2 = piattoservice.getPiatto(id2);
		//buffet.getPiatti().add(piatto3);
		//buffet.getPiatti().add(piatto2);
		//piatto3.getBuffet().add(buffet);
		//piatto2.getBuffet().add(buffet);
		//buffetservice.saveBuffet(buffet);
		//piattoservice.savePiatto(piatto2);
		//piattoservice.savePiatto(piatto3);
		
		
		
		model.addAttribute("utente", utente);
		return "base.html";
	}
	

	
	@GetMapping("/buffetlist")
	public String buffet(Model model) {
		Credentials utente = credentialservice.getCurrentCredentials();	
		model.addAttribute("utente", utente);
		return "redirect:/buffetlist/1";
	}
	@GetMapping("/aboutUs")
	public String aboutUs(Model model) {
		Credentials utente = credentialservice.getCurrentCredentials();	
		model.addAttribute("utente", utente);
		return "aboutUs.html";
	}
}
