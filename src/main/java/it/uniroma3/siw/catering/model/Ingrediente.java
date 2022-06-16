package it.uniroma3.siw.catering.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
public class Ingrediente {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@NotBlank
	@NotNull
	private String nome;
	
	@NotBlank
	@NotNull
	private String origine;
	
	@NotBlank
	@NotNull
	private String descrizione;
	
	public Ingrediente() {

	}

	public Ingrediente(String nome, String origine, String descrizione) {
		this.nome = nome;
		this.origine = origine;
		this.descrizione = descrizione;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getOrigine() {
		return this.origine;
	}

	public void setOrigine(String origine) {
		this.origine = origine;
	}

	public String getDescrizione() {
		return this.descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	
	@Override
	public boolean equals(Object obj) {
		Ingrediente ingrediente = (Ingrediente)obj;
		return this.getId().equals(ingrediente.getId());
	}

	@Override
	public int hashCode() {
		return this.id.hashCode();
	}
	
}
