package it.uniroma3.CO.spring.controller.validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import it.uniroma3.CO.spring.model.Buffet;
import it.uniroma3.CO.spring.model.Credentials;
import it.uniroma3.CO.spring.service.CredentialsService;

@Component
public class BuffetValidator implements Validator {

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
		    
	        Buffet buffet = (Buffet) o;
	        String nome = buffet.getNome().trim();
	        
	        if (nome.isEmpty())
	            errors.rejectValue("nome", "required");
	        else if (nome.length() < MIN_NAME_LENGTH || nome.length() > MAX_NAME_LENGTH)
	            errors.rejectValue("nome", "size");

		    
		    
	}

}
