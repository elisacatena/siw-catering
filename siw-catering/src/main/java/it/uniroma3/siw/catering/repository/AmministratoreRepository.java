package it.uniroma3.siw.catering.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import it.uniroma3.siw.catering.model.Amministratore;

@Repository
public interface AmministratoreRepository extends CrudRepository<Amministratore, Long> {
	
	public List<Amministratore> findByNome(String nome);
	public List<Amministratore> findByNomeOrCognome(String nome, String cognome);

}
