package it.uniroma3.siw.catering.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.uniroma3.siw.catering.model.Buffet;
import it.uniroma3.siw.catering.model.Chef;
import it.uniroma3.siw.catering.repository.BuffetRepository;

@Service
public class BuffetService {
	
	@Autowired
	private BuffetRepository buffetRepository;
	
	@Transactional
	public void save(Buffet buffet) {
		this.buffetRepository.save(buffet);
	}
	
	public Buffet findById(Long id) {
		return this.buffetRepository.findById(id).get();
	}
	
	public List<Buffet> findAll() {
		List<Buffet> buffet = new ArrayList<Buffet>();
		for(Buffet b : this.buffetRepository.findAll()) {
			buffet.add(b);
		}
		return buffet;
	}
	
	public boolean alreadyExists(Buffet buffet) {
		return this.buffetRepository.existsByNome(buffet.getNome());
	}

	public List<Buffet> findAllByChef(Chef chef) {
		List<Buffet> buffet = new ArrayList<>();
		for(Buffet b : this.buffetRepository.findByChef(chef)) {
			buffet.add(b);
		}
		return buffet;
	}
	
	@Transactional
	public void deleteById(Long id) {
		this.buffetRepository.deleteById(id);
	}

 }

