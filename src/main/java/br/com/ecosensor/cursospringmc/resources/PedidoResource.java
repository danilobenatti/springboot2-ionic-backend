package br.com.ecosensor.cursospringmc.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ecosensor.cursospringmc.domain.Pedido;
import br.com.ecosensor.cursospringmc.services.PedidoService;

@RestController
@RequestMapping(path = "/pedidos")
public class PedidoResource {
	
	@Autowired
	private PedidoService service;
	
	@GetMapping(value = "/buscar/{id}")
	public ResponseEntity<Pedido> buscarUm(@PathVariable Integer id) {
		Pedido order = service.findOrderById(id);
		return ResponseEntity.ok().body(order);
	}
}
