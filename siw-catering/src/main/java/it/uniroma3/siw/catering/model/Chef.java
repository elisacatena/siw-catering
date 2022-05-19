package it.uniroma3.siw.catering.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;

@Entity
public class Chef {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@NotBlank
	private String nome;
	
	@NotBlank
	private String cognome;
	
	@NotBlank
	private String nazionalita;
	
	/* cascade per remove perchè se elimino lo chef elimino anche tutti i buffet da lui proposti */
	@OneToMany(mappedBy = "chef", cascade = {CascadeType.REMOVE, CascadeType.PERSIST})
	private List<Buffet> buffet;
	
	public Chef() {
		
	}
	
	public Chef(String nome, String cognome, String nazionalita, List<Buffet> buffet) {
		this.nome = nome;
		this.cognome = cognome;
		this.nazionalita = nazionalita;
		this.buffet = buffet;
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

	public String getNazionalita() {
		return this.nazionalita;
	}

	public void setNazionalita(String nazionalita) {
		this.nazionalita = nazionalita;
	}

	public List<Buffet> getBuffet() {
		return this.buffet;
	}

	public void setBuffet(List<Buffet> buffet) {
		this.buffet = buffet;
	}
	
	public boolean equals(Object obj) {
		Chef chef = (Chef)obj;
		return this.getId().equals(chef.getId());
	}

	public int hashCode() {
		return this.id.hashCode();
	}
	
}