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
import it.uniroma3.CO.spring.repository.BuffetRepository;

@Service
public class BuffetService {
	

	@Autowired
	protected BuffetRepository buffetRepository;
	
	@Transactional
	public Buffet getBuffet(Long id) {
		Optional<Buffet> result = this.buffetRepository.findById(id);
		return result.orElse(null);
	}

	@Transactional
	public Buffet getBuffet(String nome) {
		Optional<Buffet> result = this.buffetRepository.findByNome(nome);
		return result.orElse(null);
	}
		
	@Transactional
	public List<Buffet> getAllBuffet() {
		List<Buffet> result = (List<Buffet>) this.buffetRepository.findAll();
		return result;
	}
		
	
	
    @Transactional
    public Buffet saveBuffet (Buffet buffet) {
        return this.buffetRepository.save(buffet);
    }
    

	public void deleteBuffet(Buffet buffet) {
		this.buffetRepository.delete(buffet);
	}
    
    
}