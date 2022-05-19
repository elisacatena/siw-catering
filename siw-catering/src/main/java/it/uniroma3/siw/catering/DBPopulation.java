package it.uniroma3.siw.catering;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import it.uniroma3.siw.catering.model.Chef;
import it.uniroma3.siw.catering.repository.ChefRepository;

@Component
public class DBPopulation implements ApplicationRunner {

	@Autowired
	private ChefRepository chefRepository;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		this.addAll();
	}
	
	private void addAll() {
		
		System.out.println("Popolazione DB");
		
		Chef barbieri = new Chef();
		barbieri.setNome("Bruno");
		barbieri.setCognome("Barbieri");
		barbieri.setNazionalita("italiana");
		
	
		
//		Chef cannavacciuolo = new Chef();
//		barbieri.setNome("Antonino");
//		barbieri.setCognome("Cannavacciuolo");
//		barbieri.setNazionalita("italiaa");
		
		
		
//		Chef cracco = new Chef();
//		barbieri.setNome("Carlo");
//		barbieri.setCognome("Cracco");
//		barbieri.setNazionalita("italiana");
		
		this.chefRepository.save(barbieri);
//		this.chefRepository.save(cannavacciuolo);
//		this.chefRepository.save(cracco);
		
		System.out.println("OKOKOK");
	}
}
