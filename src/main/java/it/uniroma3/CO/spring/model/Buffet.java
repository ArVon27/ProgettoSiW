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
public @Data class Buffet {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(nullable = false)
	private String nome;
	
	@Column(nullable = false)
	private String descrizione;
	
	@ManyToOne(cascade= {CascadeType.MERGE})
	private Chef chef;

	@ManyToMany(mappedBy="buffet",cascade= {CascadeType.PERSIST})
	private List<Credentials> utente = new ArrayList<>();
	
	@ManyToMany
	private List<Piatto> piatti = new ArrayList<>();
	
}
