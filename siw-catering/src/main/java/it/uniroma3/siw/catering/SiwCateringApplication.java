package it.uniroma3.siw.catering;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import it.uniroma3.siw.catering.model.Buffet;
import it.uniroma3.siw.catering.model.Chef;
import it.uniroma3.siw.catering.model.Ingrediente;
import it.uniroma3.siw.catering.model.Piatto;
import it.uniroma3.siw.catering.repository.BuffetRepository;
import it.uniroma3.siw.catering.repository.ChefRepository;
import it.uniroma3.siw.catering.repository.IngredienteRepository;
import it.uniroma3.siw.catering.repository.PiattoRepository;

@SpringBootApplication
public class SiwCateringApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(SiwCateringApplication.class, args);
	}

	@Autowired
	private ChefRepository chefRepository;
	
	@Autowired 
	private PiattoRepository piattoRepository;
	
	@Autowired
	private BuffetRepository buffetRepository;
	
	@Autowired
	private IngredienteRepository ingredienteRepository;
	
	@Override
	public void run(String... args) throws Exception {
		
		System.out.println("Popolazione DB");
		
		Ingrediente sale = new Ingrediente();
		sale.setNome("sale");
		sale.setDescrizione("sale desc");
		sale.setOrigine("italia");
		
		Ingrediente pepe = new Ingrediente();
		pepe.setNome("pepe");
		pepe.setDescrizione("pepe desc");
		pepe.setOrigine("america");
		
		Ingrediente pomodoro = new Ingrediente();
		pomodoro.setNome("pomodoro");
		pomodoro.setDescrizione("pomodoro desc");
		pomodoro.setOrigine("italia");
		
		Ingrediente pesto = new Ingrediente();
		pesto.setNome("pesto");
		pesto.setDescrizione("pesto desc");
		pesto.setOrigine("italia");
		
		this.ingredienteRepository.save(sale);
		this.ingredienteRepository.save(pepe);
		this.ingredienteRepository.save(pomodoro);
		this.ingredienteRepository.save(pesto);
		
		Piatto p1 = new Piatto();
		p1.setNome("p1");
		p1.setDescrizione("p1 desc");
		p1.getIngredienti().add(sale);
		p1.getIngredienti().add(pepe);
		p1.getIngredienti().add(pomodoro);
		p1.getIngredienti().add(pesto);
		
		Piatto p2 = new Piatto();
		p2.setNome("p2");
		p2.setDescrizione("p2 desc");
		p2.getIngredienti().add(pepe);
		
		Piatto p3 = new Piatto();
		p3.setNome("p3");
		p3.setDescrizione("p3 desc");
		p3.getIngredienti().add(pepe);
		
		Piatto p4 = new Piatto();
		p4.setNome("p4");
		p4.setDescrizione("p4 desc");
		p4.getIngredienti().add(pepe);
		
		Piatto p5 = new Piatto();
		p5.setNome("p5");
		p5.setDescrizione("p5 desc");
		p5.getIngredienti().add(pepe);
		
		Piatto p6 = new Piatto();
		p6.setNome("p6");
		p6.setDescrizione("p6 desc");
		p6.getIngredienti().add(pepe);
		
		Piatto p7 = new Piatto();
		p7.setNome("p7");
		p7.setDescrizione("p7 desc");
		p7.getIngredienti().add(pepe);
		
		this.piattoRepository.save(p1);
		this.piattoRepository.save(p2);
		this.piattoRepository.save(p3);
		this.piattoRepository.save(p4);
		this.piattoRepository.save(p5);
		this.piattoRepository.save(p6);
		this.piattoRepository.save(p7);
		
		
		Chef barbieri = new Chef();
		barbieri.setNome("Bruno");
		barbieri.setCognome("Barbieri");
		barbieri.setNazionalita("Italiana");
		
		Chef cannavacciuolo = new Chef();
		cannavacciuolo.setNome("Antonino");
		cannavacciuolo.setCognome("Cannavacciuolo");
		cannavacciuolo.setNazionalita("Italiana");
		
		Chef cracco = new Chef();
		cracco.setNome("Carlo");
		cracco.setCognome("Cracco");
		cracco.setNazionalita("italiana");
		
		this.chefRepository.save(barbieri);
		this.chefRepository.save(cannavacciuolo);
		this.chefRepository.save(cracco);
		
		Buffet b1 = new Buffet();
		b1.setNome("b1");
		b1.setChef(barbieri);
		b1.setDescrizione("b1 desc");
		
		Buffet b2 = new Buffet();
		b2.setNome("b2");
		b2.setChef(cannavacciuolo);
		b2.setDescrizione("b2 desc");
		
		Buffet b3 = new Buffet();
		b3.setNome("b3");
		b3.setChef(barbieri);
		b3.setDescrizione("b3 desc");
		
		Buffet b4 = new Buffet();
		b4.setNome("b4");
		b4.setChef(barbieri);
		b4.setDescrizione("b4 desc");
		
		Buffet b5 = new Buffet();
		b5.setNome("b5");
		b5.setChef(barbieri);
		b5.setDescrizione("b5 desc");
		
		Buffet b6 = new Buffet();
		b6.setNome("b6");
		b6.setChef(cracco);
		b6.setDescrizione("b6 desc");
		
		Buffet b7 = new Buffet();
		b7.setNome("b7");
		b7.setChef(barbieri);
		b7.setDescrizione("b7 desc");
		
		b1.getPiatti().add(p1);
		b1.getPiatti().add(p2);
		b1.getPiatti().add(p2);
		b1.getPiatti().add(p3);
		b1.getPiatti().add(p4);
		b1.getPiatti().add(p5);
		b1.getPiatti().add(p6);
		b2.getPiatti().add(p7);
		b3.getPiatti().add(p3);
		b4.getPiatti().add(p4);
		b5.getPiatti().add(p5);
		b6.getPiatti().add(p6);
		b7.getPiatti().add(p7);

		this.buffetRepository.save(b1);
		this.buffetRepository.save(b2);
		this.buffetRepository.save(b3);
		this.buffetRepository.save(b4);
		this.buffetRepository.save(b5);
		this.buffetRepository.save(b6);
		this.buffetRepository.save(b7);
		
	
		barbieri.getBuffet().add(b3);
		cannavacciuolo.getBuffet().add(b2);
		barbieri.getBuffet().add(b1);
		barbieri.getBuffet().add(b4);
		barbieri.getBuffet().add(b5);
		cracco.getBuffet().add(b6);
		barbieri.getBuffet().add(b7);

		System.out.println("OKOKOK");
	}
}
