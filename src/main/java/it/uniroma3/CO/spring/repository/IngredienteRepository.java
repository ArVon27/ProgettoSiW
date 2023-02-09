package it.uniroma3.CO.spring.repository;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.CO.spring.model.Ingrediente;

public interface IngredienteRepository extends CrudRepository<Ingrediente, Long> {
	
	public Optional<Ingrediente> findByNome(String nome);

}
