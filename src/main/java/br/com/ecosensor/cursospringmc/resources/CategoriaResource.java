package br.com.ecosensor.cursospringmc.resources;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.ecosensor.cursospringmc.domain.Categoria;
import br.com.ecosensor.cursospringmc.services.CategoriaService;

@RestController
@RequestMapping(value = "/categorias")
public class CategoriaResource {
	
	@Autowired
	private CategoriaService service;
	
	@GetMapping
	public ResponseEntity<Iterable<Categoria>> findAll() {
		Iterable<Categoria> categories = service.findAllCategory();
		return ResponseEntity.ok().body(categories);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Categoria> findById(@PathVariable Integer id) {
		Categoria category = service.findCategoryById(id);
		return ResponseEntity.ok().body(category);
	}
	
	@PostMapping
	public ResponseEntity<Void> insert(@RequestBody Categoria obj) {
		obj = service.insertCategory(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<Void> update(@PathVariable Integer id,
			@RequestBody Categoria obj) {
		obj.setId(id);
		obj = service.updateCategory(obj);
		return ResponseEntity.noContent().build();
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		service.deleteCategory(id);
		return ResponseEntity.noContent().build();
	}
	
}
