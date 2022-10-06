package br.com.ecosensor.cursospringmc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import br.com.ecosensor.cursospringmc.domain.Cliente;
import br.com.ecosensor.cursospringmc.dto.ClienteDTO;
import br.com.ecosensor.cursospringmc.repositories.ClienteRepository;
import br.com.ecosensor.cursospringmc.services.exceptions.DataIntegrityException;
import br.com.ecosensor.cursospringmc.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository repository;
	
	public Iterable<Cliente> findAllClient() {
		return repository.findAll();
	}
	
	public Cliente findClientById(Integer id) {
		Optional<Cliente> client = repository.findById(id);
		return client.orElseThrow(
				() -> new ObjectNotFoundException("Object not found! Id: " + id
						+ ", Type: " + Cliente.class.getSimpleName()));
	}
	
	public Page<Cliente> findPage(Integer page, Integer size, String orderBy,
			String direction) {
		PageRequest pageRequest = PageRequest.of(page, size,
				Sort.by(Direction.valueOf(direction), orderBy));
		return repository.findAll(pageRequest);
	}
	
	public Cliente insertClient(Cliente obj) {
		
		return null;
	}
	
	public Cliente updateClient(Cliente obj) {
		Cliente newObj = findClientById(obj.getId());
		updateData(newObj, obj);
		return repository.save(newObj);
	}
	
	public void deleteClient(Integer id) {
		try {
			repository.delete(findClientById(id));
		} catch (DataIntegrityViolationException ex) {
			throw new DataIntegrityException("Client has associated orders!");
		}
		
	}
	
	public Cliente fromDto(ClienteDTO objDto) {
		return new Cliente(objDto.getId(), objDto.getName(), objDto.getEmail(),
				null, null);
	}
	
	private void updateData(Cliente newObj, Cliente obj) {
		newObj.setName(obj.getName());
		newObj.setEmail(obj.getEmail());
	}
	
}
