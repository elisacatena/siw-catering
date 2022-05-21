package it.uniroma3.siw.catering;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import it.uniroma3.siw.catering.model.Buffet;
import it.uniroma3.siw.catering.model.Chef;
import it.uniroma3.siw.catering.model.Ingrediente;
import it.uniroma3.siw.catering.model.Piatto;
import it.uniroma3.siw.catering.repository.ChefRepository;
import it.uniroma3.siw.catering.repository.PiattoRepository;

@Component
public class DBPopulation implements ApplicationRunner {

	@Autowired
	private ChefRepository chefRepository;
	
	@Autowired
	private PiattoRepository piattoRepository;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		this.addAll();
	}
	
	private void addAll() {
		
		System.out.println("Popolazione DB");
		
		Chef barbieri = new Chef();
		barbieri.setNome("Bruno");
		barbieri.setCognome("Barbieri");
		barbieri.setNazionalita("Italiana");
		barbieri.setImgUrl("https://upload.wikimedia.org/wikipedia/commons/7/71/Bruno_Barbieri_firma.svg");
		
		Chef cannavacciuolo = new Chef();
		cannavacciuolo.setNome("Antonino");
		cannavacciuolo.setCognome("Cannavacciuolo");
		cannavacciuolo.setNazionalita("Italiana");
		cannavacciuolo.setImgUrl("https://it.wikipedia.org/wiki/Antonino_Cannavacciuolo#/media/File:Antonino_Cannavacciuolo-signature.svg");
		
		Chef cracco = new Chef();
		cracco.setNome("Carlo");
		cracco.setCognome("Cracco");
		cracco.setNazionalita("italiana");
		cracco.setImgUrl("https://upload.wikimedia.org/wikipedia/commons/6/60/Carlo_Cracco_firma.svg");
		
		Buffet b1 = new Buffet();
		b1.setNome("b1");
		b1.setChef(cracco);
		b1.setDescrizione("b1 desc");
		
		Buffet b2 = new Buffet();
		b2.setNome("b2");
		b2.setChef(cannavacciuolo);
		b2.setDescrizione("b2 desc");
		
		Buffet b3 = new Buffet();
		b3.setNome("b3");
		b3.setChef(barbieri);
		b3.setDescrizione("b3 desc");
		
		Ingrediente sale = new Ingrediente();
		sale.setNome("sale");
		sale.setDescrizione("sale desc");
		sale.setOrigine("italia");
		
		Ingrediente pepe = new Ingrediente();
		pepe.setNome("pepe");
		pepe.setDescrizione("pepe desc");
		pepe.setOrigine("america");
		
		Piatto p1 = new Piatto();
		p1.setNome("p1");
		p1.setDescrizione("p1 desc");
		p1.getBuffet().add(b3);
		p1.getBuffet().add(b2);
		p1.getIngredienti().add(sale);
		
		Piatto p2 = new Piatto();
		p2.setNome("p2");
		p2.setDescrizione("p2 desc");
		p2.getBuffet().add(b1);
		p2.getBuffet().add(b2);
		p2.getIngredienti().add(pepe);
		
		barbieri.getBuffet().add(b3);
		cannavacciuolo.getBuffet().add(b2);
		cracco.getBuffet().add(b1);
		
		this.chefRepository.save(barbieri);
		this.chefRepository.save(cannavacciuolo);
		this.chefRepository.save(cracco);

		this.piattoRepository.save(p1);
		this.piattoRepository.save(p2);

		System.out.println("OKOKOK");
	}
}
