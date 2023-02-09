package it.uniroma3.CO.spring.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.CO.spring.model.Chef;

public interface ChefRepository extends CrudRepository<Chef, Long> {
	
	public Optional<Chef> findByNome(String nome);

}