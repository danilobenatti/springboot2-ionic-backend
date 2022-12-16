package br.com.ecosensor.cursospringmc.repositories;

import org.springframework.data.repository.CrudRepository;

import br.com.ecosensor.cursospringmc.domain.Endereco;

public interface EnderecoRepository extends CrudRepository<Endereco, Integer> {
	
}
