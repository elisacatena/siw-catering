package it.uniroma3.siw.catering.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import it.uniroma3.siw.catering.model.Piatto;

@Repository
public interface PiattoRepository extends CrudRepository<Piatto, Long> {

	public boolean existsByNomeAndDescrizione(String nome, String descrizione);

}
