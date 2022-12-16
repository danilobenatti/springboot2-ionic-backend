package br.com.ecosensor.cursospringmc.resources;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.ecosensor.cursospringmc.domain.Pedido;
import br.com.ecosensor.cursospringmc.services.PedidoService;

@RestController
@RequestMapping(path = "/pedidos")
public class PedidoResource {
	
	@Autowired
	private PedidoService service;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Pedido> findById(@PathVariable Integer id) {
		Pedido order = service.findOrderById(id);
		return ResponseEntity.ok().body(order);
	}
	
	@PostMapping
	public ResponseEntity<Void> insert(@Valid @RequestBody Pedido obj) {
		obj = service.insertOrder(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@GetMapping
	public ResponseEntity<Page<Pedido>> findAllPage(
			@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "size", defaultValue = "24") Integer size,
			@RequestParam(value = "orderBy",
				defaultValue = "instant") String orderBy,
			@RequestParam(value = "direction",
				defaultValue = "DESC") String direction) {
		Page<Pedido> list = service.findPage(page, size, orderBy, direction);
		return ResponseEntity.ok().body(list);
	}
	
}
