package it.uniroma3.siw.catering.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
public class Buffet {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@NotBlank
	@NotNull
	private String nome;
	
	@NotBlank
	@NotNull
	private String descrizione;
	
	@ManyToOne
	private Chef chef; 
	
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
	private Set<Piatto> piatti;

	public Buffet() {
		this.piatti = new HashSet<Piatto>();
	}
	
	public Buffet(String nome, String descrizione, Chef chef, Set<Piatto> piatti) {
		this.nome = nome;
		this.descrizione = descrizione;
		this.chef = chef;
		this.piatti = piatti;
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

	public String getDescrizione() {
		return this.descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public Chef getChef() {
		return this.chef;
	}

	public void setChef(Chef chef) {
		this.chef = chef;
	}

	public Set<Piatto> getPiatti() {
		return this.piatti;
	}

	public void setPiatti(Set<Piatto> piatti) {
		this.piatti = piatti;
	}
	
	public boolean equals(Object obj) {
		Buffet buffet = (Buffet)obj;
		return this.getId().equals(buffet.getId());
	}

	public int hashCode() {
		return this.id.hashCode();
	}

}
