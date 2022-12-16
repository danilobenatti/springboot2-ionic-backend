package br.com.ecosensor.cursospringmc.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import br.com.ecosensor.cursospringmc.domain.Cliente;
import br.com.ecosensor.cursospringmc.domain.Pedido;

public interface PedidoRepository extends CrudRepository<Pedido, Integer> {
	
	@Transactional(readOnly = true)
	Page<Pedido> findByClient(Cliente client, Pageable pageRequest);
	
}
