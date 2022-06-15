package it.uniroma3.siw.catering.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import it.uniroma3.siw.catering.model.Admin;

@Repository
public interface AdminRepository extends CrudRepository<Admin, Long> {
	
	public List<Admin> findByNome(String nome);
	public List<Admin> findByNomeOrCognome(String nome, String cognome);

}
