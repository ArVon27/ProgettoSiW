package it.uniroma3.CO.spring.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.CO.spring.model.Buffet;

public interface BuffetRepository extends CrudRepository<Buffet, Long> {
	
	public Optional<Buffet> findByNome(String nome);

}
