package com.alura.challengeBook.challengeBook;

import com.alura.challengeBook.challengeBook.principal.Principal;
import com.alura.challengeBook.challengeBook.repository.LibroRepository;
import com.alura.challengeBook.challengeBook.service.ConsumoAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ChallengeBookApplication implements CommandLineRunner {
	@Autowired
	private LibroRepository repository;

	public static void main(String[] args) {
		SpringApplication.run(ChallengeBookApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("Hola Desde Buscador de Libros");
		Principal principal =new Principal(repository);
		principal.muestraElMenu();
	}
}
