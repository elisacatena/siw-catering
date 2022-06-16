package it.uniroma3.siw.catering.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import it.uniroma3.siw.catering.model.Buffet;

@Repository
public interface BuffetRepository extends CrudRepository<Buffet, Long> {

	public boolean existsByNomeAndDescrizione(String nome, String descrizione);

}
