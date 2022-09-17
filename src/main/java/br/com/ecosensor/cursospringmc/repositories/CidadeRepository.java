package br.com.ecosensor.cursospringmc.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.ecosensor.cursospringmc.domain.Cidade;

@Repository
public interface CidadeRepository extends CrudRepository<Cidade, Integer> {
	
}
