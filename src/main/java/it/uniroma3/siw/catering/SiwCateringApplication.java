package it.uniroma3.siw.catering;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import it.uniroma3.siw.catering.model.Admin;
import it.uniroma3.siw.catering.model.Credenziali;
import it.uniroma3.siw.catering.repository.CredenzialiRepository;

@SpringBootApplication
public class SiwCateringApplication implements CommandLineRunner {
	
	@Autowired private CredenzialiRepository credR;
	@Autowired protected PasswordEncoder passwordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(SiwCateringApplication.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception {
		
		Admin u = new Admin();
		u.setNome("Elisa");
		u.setCognome("Catena");
		
		Credenziali c = new Credenziali();
		c.setAdmin(u);
		c.setPassword(this.passwordEncoder.encode("admin2000"));
		c.setUsername("adin");
		c.setRole("ADMIN");
		
		credR.save(c);	
		
		System.out.println("Popolazione DB");
		


		System.out.println("OKOKOK");
	}
}
