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
import it.uniroma3.CO.spring.model.Credentials;
import it.uniroma3.CO.spring.model.Piatto;
import it.uniroma3.CO.spring.controller.validator.CredentialsValidator;
import it.uniroma3.CO.spring.service.BuffetService;
import it.uniroma3.CO.spring.service.CredentialsService;
import it.uniroma3.CO.spring.service.PiattoService;


@Controller
public class BuffetController {
	@Autowired
	CredentialsService credentialservice;
	@Autowired
	BuffetService buffetservice;
	@Autowired
	PiattoService piattoservice;
	
	
	
	@GetMapping("/buffetlist/{page}")
	public String buffetList(@PathVariable("page") int page , Model model) {
		Credentials utente = credentialservice.getCurrentCredentials();	
		
		model.addAttribute("pagina", page);
		model.addAttribute("utente", utente);
		String ultimapagina = "no";
		String primapagina = "no";
		String paginavuota = "no";
		String separatore = "no";
		//----------------------------------------------------------------------------------------------------
		List<Buffet> buffet = new ArrayList<>();
		
		
			buffet = buffetservice.getAllBuffet();
		
			
			
		//	long num= 1;
		//	Buffet prova = buffet.get(1);
		//	prova.getPiatti().add(piattoservice.getPiatto(num));
			
			
			
			
			
			Collections.reverse(buffet);
			int numeroAnnunci = buffet.size();
			
			if (numeroAnnunci==0) {
				paginavuota="si";
			}
			
			List<Buffet> annunci1 = new ArrayList<Buffet>();
			int pagineMax = numeroAnnunci/6;
			if(!buffet.isEmpty()) {
			if (numeroAnnunci%6>0) {
				pagineMax++;
			}
			if(page<pagineMax) {
				for(int i=0; i<6; i++) {
					annunci1.add(buffet.get(i+((page-1)*6)));
				}
			}
			else {
					if (page==pagineMax){
						int numeroAttuale = numeroAnnunci-((pagineMax-1)*6);
							for(int i=0; i<numeroAttuale; i++) {
								annunci1.add(buffet.get((i+(page-1)*6)));
								}
							}
					}
			}
			
				model.addAttribute("buffet", annunci1);
				
				if(page==pagineMax){
					ultimapagina="si";
				}
				model.addAttribute("paginaFinale", ultimapagina);
				
				if(page==1){
					primapagina="si";
				}
				model.addAttribute("paginaPrima", primapagina);
				model.addAttribute("paginaVuota", paginavuota);
				if(page>pagineMax) {
				return "redirect:/buffetlist/" + pagineMax;
			}
		
		//----------------------------------------------------------------------------------------------------
		
		return "buffetlist.html";
	}

	@GetMapping("/buffet/{id}")
	public String buffet(@PathVariable("id") long id, Model model) {
		Credentials utente = credentialservice.getCurrentCredentials();	
				
		Buffet buffet = buffetservice.getBuffet(id);
		model.addAttribute("buffet", buffet);
		model.addAttribute("utente", utente);
		
		List<Piatto> piatti = buffet.getPiatti();
		
		model.addAttribute("piatti", piatti);
		return "buffet.html";
	}


}





















