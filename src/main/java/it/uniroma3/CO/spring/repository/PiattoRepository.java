package it.uniroma3.CO.spring.repository;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.CO.spring.model.Piatto;

public interface PiattoRepository extends CrudRepository<Piatto, Long> {
	
	public Optional<Piatto> findByNome(String nome);

}
