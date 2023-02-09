package it.uniroma3.CO.spring.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.uniroma3.CO.spring.model.Buffet;
import it.uniroma3.CO.spring.model.Chef;
import it.uniroma3.CO.spring.repository.ChefRepository;

@Service
public class ChefService {
	

	@Autowired
	protected ChefRepository chefRepository;
	
	@Transactional
	public Chef getChef(Long id) {
		Optional<Chef> result = this.chefRepository.findById(id);
		return result.orElse(null);
	}

	@Transactional
	public Chef getChef(String nome) {
		Optional<Chef> result = this.chefRepository.findByNome(nome);
		return result.orElse(null);
	}
		
    @Transactional
    public Chef saveChef(Chef chef) {
        return this.chefRepository.save(chef);
    }
    
	@Transactional
	public List<Chef> getAllChef() {
		List<Chef> result = (List<Chef>) this.chefRepository.findAll();
		return result;
	}
	
	@Transactional
	public void deleteChef (Long id) {
		chefRepository.deleteById(id);		
	}
}