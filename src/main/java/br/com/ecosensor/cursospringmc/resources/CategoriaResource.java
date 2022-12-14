package br.com.ecosensor.cursospringmc.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.ecosensor.cursospringmc.domain.Categoria;
import br.com.ecosensor.cursospringmc.dto.CategoriaDTO;
import br.com.ecosensor.cursospringmc.services.CategoriaService;

@RestController
@RequestMapping(value = "/categorias")
public class CategoriaResource {
	
	@Autowired
	private CategoriaService service;
	
	@GetMapping
	public ResponseEntity<Iterable<CategoriaDTO>> findAll() {
		List<CategoriaDTO> collect = StreamSupport
				.stream(service.findAllCategory().spliterator(), false)
				.map(CategoriaDTO::new).collect(Collectors.toList());
		return ResponseEntity.ok().body(collect);
	}
	
	@GetMapping(path = "/page")
	public ResponseEntity<Page<CategoriaDTO>> findAllPage(
			@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "size", defaultValue = "24") Integer size,
			@RequestParam(value = "orderBy",
					defaultValue = "name") String orderBy,
			@RequestParam(value = "direction",
					defaultValue = "ASC") String direction) {
		Page<Categoria> list = service.findPage(page, size, orderBy, direction);
		Page<CategoriaDTO> listDto = list.map(CategoriaDTO::new);
		return ResponseEntity.ok().body(listDto);
	}
	
	@GetMapping(path = "/{id}")
	public ResponseEntity<Categoria> findById(@PathVariable Integer id) {
		Categoria category = service.findCategoryById(id);
		return ResponseEntity.ok().body(category);
	}
	
	@PreAuthorize(value = "hasAnyRole('ADMIN')")
	@PostMapping
	public ResponseEntity<Void> insert(
			@Valid @RequestBody CategoriaDTO objDto) {
		Categoria obj = service.fromDto(objDto);
		obj = service.insertCategory(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@PreAuthorize(value = "hasAnyRole('ADMIN')")
	@PutMapping(path = "/{id}")
	public ResponseEntity<Void> update(@PathVariable Integer id,
			@Valid @RequestBody CategoriaDTO objDto) {
		Categoria obj = service.fromDto(objDto);
		obj.setId(id);
		obj = service.updateCategory(obj);
		return ResponseEntity.noContent().build();
	}
	
	@PreAuthorize(value = "hasAnyRole('ADMIN')")
	@DeleteMapping(path = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		service.deleteCategory(id);
		return ResponseEntity.noContent().build();
	}
	
}
