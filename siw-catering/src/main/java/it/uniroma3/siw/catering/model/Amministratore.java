package it.uniroma3.siw.catering.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Amministratore {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String nome;
	private String cognome;
	
	public Amministratore() {
		
	}
	
	public Amministratore(String nome, String cognome) {
		this.nome = nome;
		this.cognome = cognome;
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

	public String getCognome() {
		return this.cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	
	public boolean equals(Object obj) {
		Amministratore amministratore = (Amministratore)obj;
		return this.getId().equals(amministratore.getId());
	}

	public int hashCode() {
		return this.id.hashCode();
	}

}