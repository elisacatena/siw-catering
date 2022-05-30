package it.uniroma3.siw.catering.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import it.uniroma3.siw.catering.model.Chef;

@Repository
public interface ChefRepository extends CrudRepository<Chef, Long> {

	public boolean existsByNome(String nome);
	
}
