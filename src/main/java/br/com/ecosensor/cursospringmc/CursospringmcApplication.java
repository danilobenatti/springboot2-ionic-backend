package br.com.ecosensor.cursospringmc;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.ecosensor.cursospringmc.domain.Categoria;
import br.com.ecosensor.cursospringmc.repositories.CategoriaRepository;

@SpringBootApplication
public class CursospringmcApplication implements CommandLineRunner {
	
	@Autowired
	CategoriaRepository categoriaRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(CursospringmcApplication.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception {
		
		Categoria cat1 = Categoria.builder().name("Copa e Cozinha").build();
		Categoria cat2 = Categoria.builder().name("Material Escolar").build();
		
		List<Categoria> asList = Arrays.asList(cat1, cat2);
		categoriaRepository.saveAll(asList);
		
	}
	
}
