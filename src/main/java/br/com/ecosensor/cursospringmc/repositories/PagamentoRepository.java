package br.com.ecosensor.cursospringmc.repositories;

import org.springframework.data.repository.CrudRepository;

import br.com.ecosensor.cursospringmc.domain.Pagamento;

public interface PagamentoRepository extends CrudRepository<Pagamento, Integer> {
	
}
