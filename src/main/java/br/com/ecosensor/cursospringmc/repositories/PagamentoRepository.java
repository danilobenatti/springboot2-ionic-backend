package br.com.ecosensor.cursospringmc.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.ecosensor.cursospringmc.domain.Pagamento;

@Repository
public interface PagamentoRepository extends CrudRepository<Pagamento, Integer> {
	
}
