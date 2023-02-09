package it.uniroma3.CO.spring.controller.validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import it.uniroma3.CO.spring.model.Chef;
import it.uniroma3.CO.spring.model.Credentials;
import it.uniroma3.CO.spring.service.CredentialsService;

@Component
public class ChefValidator implements Validator {

    @Autowired
    private CredentialsService credentialsService;



	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return false;
	}
	
	
	

	@Override
	public void validate(Object o, Errors errors) {
		
		 	final Integer MAX_NAME_LENGTH = 20;
		    final Integer MIN_NAME_LENGTH = 0;	
		 	final Integer MAX_SURNAME_LENGTH = 20;
		    final Integer MIN_SURNAME_LENGTH = 0;	
		 	final Integer MAX_NATIONALITY_LENGTH = 20;
		    final Integer MIN_NATIONALITY_LENGTH = 0;	
		    
		    
	        Chef chef = (Chef) o;
	        String nome = chef.getNome().trim();
	        String cognome = chef.getCognome().trim();
	        String nazionalita= chef.getNazionalita().trim();
	        
	        if (nome.isEmpty())
	            errors.rejectValue("nome", "required");
	        else if (nome.length() < MIN_NAME_LENGTH || nome.length() > MAX_NAME_LENGTH)
	            errors.rejectValue("nome", "size");

		    
	        if (cognome.isEmpty())
	            errors.rejectValue("cognome", "required");
	        else if (cognome.length() < MIN_SURNAME_LENGTH || cognome.length() > MAX_SURNAME_LENGTH)
	            errors.rejectValue("cognome", "size");

	        
	        if (nazionalita.isEmpty())
	            errors.rejectValue("nazionalita", "required");
	        else if (nazionalita.length() < MIN_NATIONALITY_LENGTH || nazionalita.length() > MAX_NATIONALITY_LENGTH)
	            errors.rejectValue("nazionalita", "size");

	}

}
