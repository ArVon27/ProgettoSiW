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
import it.uniroma3.CO.spring.model.Piatto;
import it.uniroma3.CO.spring.repository.PiattoRepository;

@Service
public class PiattoService {
	

	@Autowired
	protected PiattoRepository piattoRepository;
	
	@Transactional
	public Piatto getPiatto(Long id) {
		Optional<Piatto> result = this.piattoRepository.findById(id);
		return result.orElse(null);
	}

	@Transactional
	public Piatto getPiatto(String nome) {
		Optional<Piatto> result = this.piattoRepository.findByNome(nome);
		return result.orElse(null);
	}
		
    @Transactional
    public Piatto savePiatto(Piatto piatto) {
        return this.piattoRepository.save(piatto);
    }
    
	@Transactional
	public List<Piatto> getAllPiatto() {
		List<Piatto> result = (List<Piatto>) this.piattoRepository.findAll();
		return result;
	}
    
	@Transactional
	public void deletePiatto (Long id) {
		piattoRepository.deleteById(id);		
	}
}