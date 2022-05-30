package it.uniroma3.siw.catering.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
public class Piatto {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@NotBlank
	@NotNull
	private String nome;
	
	@NotBlank
	@NotNull
	private String descrizione;
	
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
	private List<Ingrediente> ingredienti;

	public Piatto() {
		this.ingredienti = new ArrayList<Ingrediente>();
	}
	
	public Piatto(String nome, String descrizione, List<Buffet> buffet, List<Ingrediente> ingredienti) {
		this.nome = nome;
		this.descrizione = descrizione;
		this.ingredienti = ingredienti;
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

	public List<Ingrediente> getIngredienti() {
		return this.ingredienti;
	}

	public void setIngredienti(List<Ingrediente> ingredienti) {
		this.ingredienti = ingredienti;
	}
	
	public boolean equals(Object obj) {
		Piatto piatto = (Piatto)obj;
		return this.getId().equals(piatto.getId());
	}

	public int hashCode() {
		return this.id.hashCode();
	}
	
}
