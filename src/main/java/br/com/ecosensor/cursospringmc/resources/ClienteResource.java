package br.com.ecosensor.cursospringmc.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ecosensor.cursospringmc.domain.Cliente;
import br.com.ecosensor.cursospringmc.services.ClienteService;

@RestController
@RequestMapping(path = "/clientes")
public class ClienteResource {
	
	@Autowired
	private ClienteService service;
	
	@GetMapping(value = "/buscar/{id}")
	public ResponseEntity<Cliente> buscar(@PathVariable Integer id) {
		Cliente cliente = service.findClient(id);
		return ResponseEntity.ok().body(cliente);
	}
}
