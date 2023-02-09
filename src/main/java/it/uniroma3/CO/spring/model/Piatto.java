package it.uniroma3.CO.spring.model;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import lombok.Data;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;


@Entity
public @Data class Piatto {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(nullable = false)
	private String nome;
	
	@Column(nullable = false)
	private String descrizione;
	
	@ManyToMany
	private List<Ingrediente> ingredienti = new ArrayList<>();
	
	@ManyToMany(mappedBy="piatti",cascade= {CascadeType.MERGE})
	private List<Buffet> buffet = new ArrayList<>();
	
	
}
