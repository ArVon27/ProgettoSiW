package it.uniroma3.CO.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import it.uniroma3.CO.spring.model.Credentials;
import it.uniroma3.CO.spring.controller.validator.CredentialsValidator;
import it.uniroma3.CO.spring.service.CredentialsService;

import it.uniroma3.CO.spring.model.Ingrediente;
import it.uniroma3.CO.spring.service.IngredienteService;
@Controller
public class AuthenticationController {



	
	@Autowired
	CredentialsValidator credentialsValidator;
	@Autowired
	CredentialsService credentialsService;
	@Autowired
	IngredienteService ingredienteService;
	

	@RequestMapping(value = "/login", method = RequestMethod.GET) 
	public String showLoginForm (Model model) {
		return "login.html";
	}
	
	@GetMapping("/register")
	public String register(Model model) {
		Credentials utente = new Credentials();
		model.addAttribute("utente", utente);
		return "register.html";
	}
	
	
	//@PostMapping("/register")
	//public String register1(Model model) {}
	
	
	
    @RequestMapping(value = { "/register" }, method = RequestMethod.POST)
    public String registerUser(@ModelAttribute("utente") Credentials credentials,
                 BindingResult credentialsBindingResult,
                 Model model) {
        // valida lo user e le credenziali
        this.credentialsValidator.validate(credentials, credentialsBindingResult);

        // se non ci sono errori salva tutto e porta l'utente alla pagina di approvazione
        if(!credentialsBindingResult.hasErrors()) {
            
            // per controllare se e il primo utente mi evito una fetch per la size salvando le credenziali utente
            Credentials credenzialiUtente = credentialsService.saveCredentials(credentials);
            
            //se il primo utente e un admin, allora nessuna approvazione richiesta
           
            return "redirect:/login";
        }
        // altrimenti stampa gli errori in console...
        //if(userBindingResult.hasErrors()) {
        //	System.out.println("######## Errore ######## [ "+ userBindingResult.getAllErrors() + " ]");
        //}
        return "register";
    }
}
