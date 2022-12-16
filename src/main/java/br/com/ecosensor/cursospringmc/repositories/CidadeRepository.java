package br.com.ecosensor.cursospringmc.repositories;

import org.springframework.data.repository.CrudRepository;

import br.com.ecosensor.cursospringmc.domain.Cidade;

public interface CidadeRepository extends CrudRepository<Cidade, Integer> {
	
}
