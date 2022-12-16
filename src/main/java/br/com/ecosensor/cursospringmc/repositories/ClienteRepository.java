package br.com.ecosensor.cursospringmc.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;

import br.com.ecosensor.cursospringmc.domain.Cliente;

public interface ClienteRepository extends CrudRepository<Cliente, Integer>,
		PagingAndSortingRepository<Cliente, Integer> {
	
	@Transactional(readOnly = true)
	Optional<Cliente> findByEmail(String email);
	
	@Transactional(readOnly = true)
	Optional<Cliente> findByCpfCnpj(String cpfCnpj);
}
