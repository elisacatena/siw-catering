package it.uniroma3.siw.catering.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import it.uniroma3.siw.catering.model.Admin;

@Repository
public interface AdminRepository extends CrudRepository<Admin, Long> {
	

}
