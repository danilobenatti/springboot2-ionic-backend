package br.com.ecosensor.cursospringmc.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.ecosensor.cursospringmc.domain.ItemPedido;


@Repository
public interface ItemPedidoRepository extends CrudRepository<ItemPedido, Integer> {
	
}
