package br.com.ecosensor.cursospringmc.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ecosensor.cursospringmc.domain.Categoria;
import br.com.ecosensor.cursospringmc.services.CategoriaService;

@RestController
@RequestMapping(value = "/categorias")
public class CategoriaResource {
	
	@Autowired
	private CategoriaService service;
	
	@GetMapping(value = "/todas")
	public ResponseEntity<Iterable<Categoria>> buscarTodas() {
		Iterable<Categoria> categories = service.findAllCategory();
		return ResponseEntity.ok().body(categories);
	}
	
	@GetMapping(value = "/buscar/{id}")
	public ResponseEntity<Categoria> buscarUma(@PathVariable Integer id) {
		Categoria category = service.findCategoryById(id);
		return ResponseEntity.ok().body(category);
	}
	
}
