package br.com.ecosensor.cursospringmc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ecosensor.cursospringmc.domain.Pedido;
import br.com.ecosensor.cursospringmc.repositories.PedidoRepository;
import br.com.ecosensor.cursospringmc.services.exceptions.ObjectNotFoundException;

@Service
public class PedidoService {
	
	@Autowired
	PedidoRepository repository;
	
	public Pedido findOrderById(Integer id) {
		Optional<Pedido> order = repository.findById(id);
		return order.orElseThrow(
				() -> new ObjectNotFoundException("Object not found! Id: "
						+ id + ", Type: " + Pedido.class.getSimpleName()));
	}
}
