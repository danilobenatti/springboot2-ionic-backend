package br.com.ecosensor.cursospringmc.resources;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ecosensor.cursospringmc.domain.Categoria;

@RestController
@RequestMapping(value = "/categorias")
public class CategoriaResource {
	
	@GetMapping(value = "/listar")
	public List<Categoria> listar() {
		
		Categoria cat1 = new Categoria(1, "Informática");
		Categoria cat2 = new Categoria(2, "Escritório");
		
		List<Categoria> listaCategoria = new ArrayList<>();
		listaCategoria.add(cat1);
		listaCategoria.add(cat2);
		listaCategoria.add(Categoria.builder().id(3).nome("Lazer").build());
		
		return listaCategoria;
	}
	
}
