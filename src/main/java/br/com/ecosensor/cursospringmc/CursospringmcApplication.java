package br.com.ecosensor.cursospringmc;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.ecosensor.cursospringmc.domain.Categoria;
import br.com.ecosensor.cursospringmc.domain.Produto;
import br.com.ecosensor.cursospringmc.repositories.CategoriaRepository;
import br.com.ecosensor.cursospringmc.repositories.ProdutoRepository;

@SpringBootApplication
public class CursospringmcApplication implements CommandLineRunner {
	
	@Autowired
	CategoriaRepository categoriaRepository;
	
	@Autowired
	ProdutoRepository produtoRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(CursospringmcApplication.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception {
		
		Categoria cat1 = Categoria.builder().name("Copa e Cozinha").build();
		Categoria cat2 = Categoria.builder().name("Material Escolar").build();
		
		List<Categoria> asList = Arrays.asList(cat1, cat2);
		categoriaRepository.saveAll(asList);
		
		Produto p1 = Produto.builder().name("Copo Descartável 100und").price(20.00).build();
		Produto p2 = Produto.builder().name("Guardanapo Papel 50und").price(15.00).build();
		Produto p3 = Produto.builder().name("Régua 30cm").price(10.00).build();
		
		cat1.getProducts().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProducts().add(p3);
		
		p1.getCategories().addAll(Arrays.asList(cat1));
		p2.getCategories().addAll(Arrays.asList(cat1));
		p3.getCategories().addAll(Arrays.asList(cat1, cat2));
		
		produtoRepository.saveAll(Arrays.asList(p1,p2,p3));
		
	}
	
}
