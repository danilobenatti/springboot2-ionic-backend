package br.com.ecosensor.cursospringmc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ecosensor.cursospringmc.domain.Cliente;
import br.com.ecosensor.cursospringmc.repositories.ClienteRepository;
import br.com.ecosensor.cursospringmc.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {
	
	@Autowired
	ClienteRepository repository;
	
	public Cliente findClientById(Integer id) {
		Optional<Cliente> client = repository.findById(id);
		return client.orElseThrow(
				() -> new ObjectNotFoundException("Object not found! Id: "
						+ id + ", Type: " + Cliente.class.getSimpleName()));
	}
}
