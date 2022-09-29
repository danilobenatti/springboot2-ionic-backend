package br.com.ecosensor.cursospringmc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import br.com.ecosensor.cursospringmc.domain.Categoria;
import br.com.ecosensor.cursospringmc.repositories.CategoriaRepository;
import br.com.ecosensor.cursospringmc.services.exceptions.DataIntegrityException;
import br.com.ecosensor.cursospringmc.services.exceptions.ObjectNotFoundException;

@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository repository;
	
	public Categoria findCategoryById(Integer id) {
		Optional<Categoria> category = repository.findById(id);
		return category.orElseThrow(
				() -> new ObjectNotFoundException("Object not found! Id: " + id
						+ ", Type: " + Categoria.class.getSimpleName()));
	}
	
	public Iterable<Categoria> findAllCategory() {
		return repository.findAll();
	}
	
	public Categoria insertCategory(Categoria obj) {
		obj.setId(null);
		return repository.save(obj);
	}
	
	public Categoria updateCategory(Categoria obj) {
		findCategoryById(obj.getId());
		return repository.save(obj);
	}
	
	public void deleteCategory(Integer id) {
		try {
			repository.delete(findCategoryById(id));
		} catch (DataIntegrityViolationException ex) {
			throw new DataIntegrityException(
					"Category has associated products!");
		}
	}
	
}
