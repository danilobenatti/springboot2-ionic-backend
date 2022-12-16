package br.com.ecosensor.cursospringmc.repositories;

import org.springframework.data.repository.CrudRepository;

import br.com.ecosensor.cursospringmc.domain.Estado;

public interface EstadoRepository extends CrudRepository<Estado, Integer> {
	
}
