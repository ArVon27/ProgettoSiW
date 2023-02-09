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
import it.uniroma3.CO.spring.model.Ingrediente;
import it.uniroma3.CO.spring.repository.IngredienteRepository;

@Service
public class IngredienteService {
	

	@Autowired
	protected IngredienteRepository ingredienteRepository;
	
	@Transactional
	public Ingrediente getIngrediente(Long id) {
		Optional<Ingrediente> result = this.ingredienteRepository.findById(id);
		return result.orElse(null);
	}

	@Transactional
	public Ingrediente getIngrediente(String nome) {
		Optional<Ingrediente> result = this.ingredienteRepository.findByNome(nome);
		return result.orElse(null);
	}
		
    @Transactional
    public Ingrediente saveIngrediente(Ingrediente ingrediente) {
        return this.ingredienteRepository.save(ingrediente);
    }
    
    
    
	@Transactional
	public List<Ingrediente> getAllIngrediente() {
		List<Ingrediente> result = (List<Ingrediente>) this.ingredienteRepository.findAll();
		return result;
	}
    
}