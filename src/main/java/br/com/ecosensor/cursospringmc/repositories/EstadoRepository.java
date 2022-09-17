package br.com.ecosensor.cursospringmc.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.ecosensor.cursospringmc.domain.Estado;

@Repository
public interface EstadoRepository extends CrudRepository<Estado, Integer> {
	
}
