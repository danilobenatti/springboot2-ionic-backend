package br.com.ecosensor.cursospringmc.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.ecosensor.cursospringmc.domain.Produto;
import br.com.ecosensor.cursospringmc.dto.ProdutoDTO;
import br.com.ecosensor.cursospringmc.resources.utils.URL;
import br.com.ecosensor.cursospringmc.services.ProdutoService;

@RestController
@RequestMapping(value = "/produtos")
public class ProdutoResource {
	
	@Autowired
	private ProdutoService service;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Produto> findById(@PathVariable Integer id) {
		Produto order = service.findProductById(id);
		return ResponseEntity.ok().body(order);
	}
	
	@GetMapping(value = "/page")
	public ResponseEntity<Page<ProdutoDTO>> findAllPage(
			@RequestParam(value = "name", defaultValue = "") String name,
			@RequestParam(value = "categories",
				defaultValue = "") String categories,
			@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "size", defaultValue = "24") Integer size,
			@RequestParam(value = "orderBy",
				defaultValue = "name") String orderBy,
			@RequestParam(value = "direction",
				defaultValue = "ASC") String direction) {
		String nameDecoded = URL.decodeParam(name);
		List<Integer> ids = URL.decodeIntList(categories);
		Page<Produto> list = service.searchProduct(nameDecoded, ids, page, size,
				orderBy, direction);
		Page<ProdutoDTO> listDto = list.map(ProdutoDTO::new);
		return ResponseEntity.ok().body(listDto);
	}
	
}
