package it.uniroma3.siw.catering.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.uniroma3.siw.catering.model.Chef;
import it.uniroma3.siw.catering.repository.ChefRepository;

@Service
public class ChefService {

	@Autowired
	private ChefRepository chefRepository;
	
	@Transactional
	public void save(Chef chef) {
		this.chefRepository.save(chef);
	}
	
	public Chef findById(Long id) {
		return this.chefRepository.findById(id).get();
	}
	
	public List<Chef> findAll() {
		List<Chef> chef = new ArrayList<Chef>();
		for(Chef c : this.chefRepository.findAll()) {
			chef.add(c);
		}
		return chef;
	}
	
	public boolean alreadyExists(Chef chef) {
		return this.chefRepository.existsByNomeAndCognomeAndNazionalita(chef.getNome(), 
				chef.getCognome(), chef.getNazionalita());
	}
	
	@Transactional
	public void deleteById(Long id) {
		this.chefRepository.deleteById(id);
	}
	
}
