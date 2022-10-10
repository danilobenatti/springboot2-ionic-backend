package br.com.ecosensor.cursospringmc.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.ecosensor.cursospringmc.domain.Cliente;

@Repository
public interface ClienteRepository extends CrudRepository<Cliente, Integer>,
		PagingAndSortingRepository<Cliente, Integer> {
	
	@Transactional(readOnly = true)
	List<Cliente> findByEmail(String email);
	
	@Transactional(readOnly = true)
	List<Cliente> findByCpfOuCnpj(String cpfOuCnpj);
}
