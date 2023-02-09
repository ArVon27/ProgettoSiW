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
import it.uniroma3.CO.spring.controller.validator.BuffetValidator;
import it.uniroma3.CO.spring.controller.validator.ChefValidator;
import it.uniroma3.CO.spring.controller.validator.IngredienteValidator;
import it.uniroma3.CO.spring.controller.validator.PiattoValidator;
import it.uniroma3.CO.spring.model.Buffet;
import it.uniroma3.CO.spring.model.Chef;
import it.uniroma3.CO.spring.model.Credentials;
import it.uniroma3.CO.spring.model.Ingrediente;
import it.uniroma3.CO.spring.model.Piatto;




@Controller
public class CreatorController {
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
	@Autowired
	IngredienteValidator ingredientevalidator;
	@Autowired
	PiattoValidator piattovalidator;
	@Autowired
	BuffetValidator buffetvalidator;
	@Autowired
	ChefValidator chefvalidator;
	
	
	@GetMapping("/ingredienteCreator")
	public String ingredienteCreator(Model model) {
		Credentials utente = credentialservice.getCurrentCredentials();	
		model.addAttribute("utente", utente);
		Ingrediente ingrediente = new Ingrediente();
		
		model.addAttribute("ingrediente", ingrediente);
		return "ingredienteCreator.html";
	}
	
	
	
	
	
	
	@RequestMapping(value = { "/ingredienteFatto" }, method = RequestMethod.POST)
    public String ingredienteFatto (@ModelAttribute("ingrediente") Ingrediente ingrediente, BindingResult credentialsBindingResult, Model model) {
        
        this.ingredientevalidator.validate(ingrediente, credentialsBindingResult);

        if(!credentialsBindingResult.hasErrors()) {
        	
        	ingredienteservice.saveIngrediente(ingrediente);
        	}
        
        return "redirect:/home";
		}
	
	
	//------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
	
	Piatto piattoTemp;
	@GetMapping("/piattoCreator")
	public String piattoCreator (Model model) {
		Credentials utente = credentialservice.getCurrentCredentials();	
		model.addAttribute("utente", utente);
		piattoTemp = new Piatto();
		model.addAttribute("piatto", piattoTemp);
		return "piattoCreator.html";
	}
	
	
	@RequestMapping(value = { "/piattoFatto1" }, method = RequestMethod.POST)
    public String piattoFatto1 (@ModelAttribute("piatto") Piatto piatto, BindingResult credentialsBindingResult, Model model) {
        
        this.piattovalidator.validate(piatto, credentialsBindingResult);

        if(!credentialsBindingResult.hasErrors()) {
        	
        	piattoTemp=piatto;
        	
        	return "redirect:/piattoFatto2";
        	}
        
        return "redirect:/home";
		}
	
	
	@GetMapping("/piattoFatto2")
	public String piattoFatto2 (Model model) {
		Credentials utente = credentialservice.getCurrentCredentials();	
		model.addAttribute("utente", utente);

		model.addAttribute("piatto", piattoTemp);
		List<Ingrediente> ingredienti = ingredienteservice.getAllIngrediente();
		model.addAttribute("ingredienti", ingredienti);
		
		List<Ingrediente> ingredienti2 = piattoTemp.getIngredienti();
		model.addAttribute("ingredienti2", ingredienti2);
		return "piattoCreator2.html";
	}
	
	
	@GetMapping("/piattoFatto3/{id}")
    public String piattoFatto3 (@PathVariable("id") Long id, Model model) {
        	
			Ingrediente ingrediente = ingredienteservice.getIngrediente(id);
			List<Ingrediente> ingredienti = piattoTemp.getIngredienti();
			
			int y = 0;
			for(Ingrediente caso: ingredienti) {
				if (caso.getNome().equals(ingrediente.getNome())) {
					y=1;
				}
			}
			if(y==0) {
				piattoTemp.getIngredienti().add(ingrediente);
				}
        	return "redirect:/piattoFatto2";
        	}

	
	@GetMapping("/piattoFatto4")
	public String piattoFatto4 (Model model) {
		piattoservice.savePiatto(piattoTemp);
		return "redirect:/home";
	}
	//--------------------------------------------------------------------------------------------------------------------------------------------------
	
	Buffet buffetTemp;
	@GetMapping("/buffetCreator")
	public String buffetCreator (Model model) {
		Credentials utente = credentialservice.getCurrentCredentials();	
		model.addAttribute("utente", utente);
		buffetTemp = new Buffet();
		model.addAttribute("buffet", buffetTemp);
		return "buffetCreator.html";
	}
	
	@RequestMapping(value = { "/buffetFatto1" }, method = RequestMethod.POST)
    public String buffetFatto1 (@ModelAttribute("buffet") Buffet buffet, BindingResult credentialsBindingResult, Model model) {
        this.buffetvalidator.validate(buffet, credentialsBindingResult);
        if(!credentialsBindingResult.hasErrors()) {
        	buffetTemp=buffet;
        	return "redirect:/buffetFatto2";
        	}
        return "redirect:/home";
		}
	
	@GetMapping("/buffetFatto2")
	public String buffetFatto2 (Model model) {
		Credentials utente = credentialservice.getCurrentCredentials();	
		model.addAttribute("utente", utente);
		List<Chef> chefs = chefservice.getAllChef();

		model.addAttribute("chefs", chefs);
		
		return "buffetCreator2.html";
	}
	
	@GetMapping("/buffetSalvaChef/{id}")
	public String buffetSalvaChef(@PathVariable("id") Long id, Model model) {
		//System.out.println("provaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
		//System.out.println("provaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
		//System.out.println("provaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
		//System.out.println(id);
		Chef chef = chefservice.getChef(id);
		//System.out.println(chef.getNome());
		buffetTemp.setChef(chef);
		//System.out.println(buffetTemp.getChef().getNome());
		return "redirect:/buffetFatto3";
	}
	
	
	
	@GetMapping("/buffetFatto3")
    public String buffetFatto3 (Model model) {
		
		Credentials utente = credentialservice.getCurrentCredentials();	
		model.addAttribute("utente", utente);

		List<Piatto> piatti = piattoservice.getAllPiatto();
		model.addAttribute("piatti", piatti);
		
		List<Piatto> piatti2 = buffetTemp.getPiatti();
		model.addAttribute("piatti2", piatti2);
		return "buffetCreator3.html";
	}
	
	
	@GetMapping("/buffetFatto4/{id}")
    public String buffetFatto4 (@PathVariable("id") Long id, Model model) {
        	
			Piatto piatto = piattoservice.getPiatto(id);
			
			List<Piatto> piatti = buffetTemp.getPiatti();
			
			int y = 0;
			for(Piatto caso: piatti) {
				if (caso.getNome().equals(piatto.getNome())) {
					y=1;
				}
			}
			if(y==0) {
				buffetTemp.getPiatti().add(piatto);
				//piatto.getBuffet().add(buffetTemp);
				//piattoservice.savePiatto(piatto);
				}
        	return "redirect:/buffetFatto3";
        	}
	
	
	
	
	@GetMapping("/buffetFatto5")
	public String buffetFatto5 (Model model) {
		
		//Chef chef = buffetTemp.getChef();
		//chef.getBuffet().add(buffetTemp);
		//chefservice.saveChef(chef);
		
		System.out.println("provaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
		System.out.println("provaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
		System.out.println("provaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
		//System.out.println(buffetTemp.getChef().getNome());
		
		//for (Piatto piatto : buffetTemp.getPiatti()) {
		//	System.out.println(piatto.getNome());
		//}
		
		
		//Buffet buffet = new Buffet();
		//buffet.setNome(buffetTemp.getNome());
		//buffet.setDescrizione(buffetTemp.getDescrizione());
		
		//System.out.println(buffetTemp.getChef().getNome());
		
		//buffet.setChef(buffetTemp.getChef());
		//buffet.getChef().getBuffet().add(buffet);
		
		
		buffetservice.saveBuffet(buffetTemp);
		return "redirect:/home";
	}
	
//------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------	
	
	
	
	@GetMapping("/chefCreator")
	public String chefCreator (Model model) {
		Credentials utente = credentialservice.getCurrentCredentials();	
		model.addAttribute("utente", utente);
		Chef chef = new Chef();
		model.addAttribute("chef", chef);
		return "chefCreator.html";
	}
	@RequestMapping(value = { "/chefFatto1" }, method = RequestMethod.POST)
    public String chefFatto1 (@ModelAttribute("chef") Chef chef, BindingResult credentialsBindingResult, Model model) {
        this.chefvalidator.validate(chef, credentialsBindingResult);
        if(!credentialsBindingResult.hasErrors()) {
        	chefservice.saveChef(chef);
        	}
        return "redirect:/home";
		}

	@GetMapping("/chefModifica1")
	public String chefModifica1(Model model) {
		Credentials utente = credentialservice.getCurrentCredentials();	
		model.addAttribute("utente", utente);
		
		List<Chef> chefs = chefservice.getAllChef();
		model.addAttribute("chefs", chefs);
		return "chefModifica1.html";
	}
	Long ids;
	@GetMapping("/chefModifica2/{id}")
	public String chefModifica2(@PathVariable("id") Long id, Model model) {
		Credentials utente = credentialservice.getCurrentCredentials();	
		model.addAttribute("utente", utente);
		ids=id;
		Chef chef = chefservice.getChef(id);
		model.addAttribute("chef", chef);
		return "chefModifica2.html";
	}
	@RequestMapping(value = { "/chefModifica3" }, method = RequestMethod.POST)
    public String chefModifica3 (@ModelAttribute("chef") Chef chef, BindingResult credentialsBindingResult, Model model) {
        this.chefvalidator.validate(chef, credentialsBindingResult);
        if(!credentialsBindingResult.hasErrors()) {
        	
        	chefservice.getChef(ids).setNome(chef.getNome());
        	chefservice.getChef(ids).setCognome(chef.getCognome());
        	chefservice.saveChef(chefservice.getChef(ids));
        	}
        return "redirect:/home";
		}
	
	
	
	
	
	@GetMapping("/piattoModifica1")
	public String piattoModifica1(Model model) {
		Credentials utente = credentialservice.getCurrentCredentials();	
		model.addAttribute("utente", utente);
		
		List<Piatto> piatti = piattoservice.getAllPiatto();
		model.addAttribute("piatti", piatti);
		return "piattoModifica1.html";
	}
	@GetMapping("/piattoModifica2/{id}")
	public String piattoModifica2(@PathVariable("id") Long id, Model model) {
		Credentials utente = credentialservice.getCurrentCredentials();	
		model.addAttribute("utente", utente);
		Piatto piatto = piattoservice.getPiatto(id);
		model.addAttribute("piatto", piatto);
		return "piattoModifica2.html";
	}
	@RequestMapping(value = { "/piattoModifica3/{id}" }, method = RequestMethod.POST)
    public String piattoModifica3 (@ModelAttribute("piatto") Piatto piatto, @PathVariable("id") Long id, BindingResult credentialsBindingResult, Model model) {
        this.piattovalidator.validate(piatto, credentialsBindingResult);
        if(!credentialsBindingResult.hasErrors()) {
        	
        	piattoservice.getPiatto(id).setNome(piatto.getNome());
        	piattoservice.getPiatto(id).setDescrizione(piatto.getDescrizione());
        	piattoservice.savePiatto(piattoservice.getPiatto(id));

        	}
        return "redirect:/home";
		}
	
	
	
	@GetMapping("/buffetModifica1")
	public String buffetModifica1(Model model) {
		Credentials utente = credentialservice.getCurrentCredentials();	
		model.addAttribute("utente", utente);
		
		List<Buffet> buffet = buffetservice.getAllBuffet();
		model.addAttribute("buffet", buffet);
		return "buffetModifica1.html";
	}
	@GetMapping("/buffetModifica2/{id}")
	public String buffetModifica2(@PathVariable("id") Long id, Model model) {
		Credentials utente = credentialservice.getCurrentCredentials();	
		model.addAttribute("utente", utente);
		Buffet buffet = buffetservice.getBuffet(id);
		model.addAttribute("buffet", buffet);
		return "buffetModifica2.html";
	}
	@RequestMapping(value = { "/buffetModifica3/{id}" }, method = RequestMethod.POST)
    public String buffetModifica3 (@ModelAttribute("buffet") Buffet buffet, @PathVariable("id") Long id, BindingResult credentialsBindingResult, Model model) {
        this.buffetvalidator.validate(buffet, credentialsBindingResult);
        if(!credentialsBindingResult.hasErrors()) {
        	
        	buffetservice.getBuffet(id).setNome(buffet.getNome());
        	buffetservice.getBuffet(id).setDescrizione(buffet.getDescrizione());
        	buffetservice.saveBuffet(buffetservice.getBuffet(id));

        	}
        return "redirect:/home";
		}
	
	
	@GetMapping("/ingredienteModifica1")
	public String ingredienteModifica1(Model model) {
		Credentials utente = credentialservice.getCurrentCredentials();	
		model.addAttribute("utente", utente);
		
		List<Ingrediente> ingredienti= ingredienteservice.getAllIngrediente();
		model.addAttribute("ingredienti", ingredienti);
		return "ingredienteModifica1.html";
	}
	@GetMapping("/ingredienteModifica2/{id}")
	public String ingredienteModifica2(@PathVariable("id") Long id, Model model) {
		Credentials utente = credentialservice.getCurrentCredentials();	
		model.addAttribute("utente", utente);
		Ingrediente ingrediente= ingredienteservice.getIngrediente(id);
		model.addAttribute("ingrediente", ingrediente);
		return "ingredienteModifica2.html";
	}
	@RequestMapping(value = { "/ingredienteModifica3/{id}" }, method = RequestMethod.POST)
    public String ingredienteModifica3 (@ModelAttribute("ingrediente") Ingrediente ingrediente, @PathVariable("id") Long id, BindingResult credentialsBindingResult, Model model) {
        this.ingredientevalidator.validate(ingrediente, credentialsBindingResult);
        if(!credentialsBindingResult.hasErrors()) {
        	
        	ingredienteservice.getIngrediente(id).setNome(ingrediente.getNome());
        	ingredienteservice.getIngrediente(id).setOrigine(ingrediente.getOrigine());
        	ingredienteservice.getIngrediente(id).setDescrizione(ingrediente.getDescrizione());
        	ingredienteservice.saveIngrediente(ingredienteservice.getIngrediente(id));

        	}
        return "redirect:/home";
		}
	

	
	
	
	
	
	
	
	
	@GetMapping("/chefCancella/{id}")
	public String chefCancella(@PathVariable("id") Long id, Model model) {
		Credentials utente = credentialservice.getCurrentCredentials();
		Chef chef = chefservice.getChef(id);
		
		for(Buffet buffet: chef.getBuffet()) {
			utente.getBuffet().remove(buffet);
			buffet.getUtente().clear();
			buffetservice.deleteBuffet(buffet);
		}
		
		chefservice.deleteChef(id);

		return "redirect:/chefModifica1";
	}
	
	
	
	
	@GetMapping("/buffetCancella/{id}")
	public String buffetCancella(@PathVariable("id") Long id, Model model) {
		Credentials utente = credentialservice.getCurrentCredentials();
		Buffet buffet = buffetservice.getBuffet(id);
		
		utente.getBuffet().remove(buffet);
		Chef chef = buffet.getChef();
		chef.getBuffet().remove(buffet);
		
		buffetservice.deleteBuffet(buffet);

		return "redirect:/buffetModifica1";
	}
	
	
	@GetMapping("/piattoCancella/{id}")
	public String piattoCancella(@PathVariable("id") Long id, Model model) {
		Credentials utente = credentialservice.getCurrentCredentials();
		
		Piatto piatto = piattoservice.getPiatto(id);
		
		for (Buffet buffet : piatto.getBuffet()) {
			buffet.getPiatti().remove(piatto);
		}
	
		for(Ingrediente ingrediente: piatto.getIngredienti()) {
			ingrediente.getPiatto().remove(piatto);
		}
		
		piattoservice.deletePiatto(id);
		
		return "redirect:/piattoModifica1";
	}
	
	
	
	
	
	
	
	
	
}

