package br.com.ecosensor.cursospringmc.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import br.com.ecosensor.cursospringmc.domain.Categoria;

public interface CategoriaRepository extends CrudRepository<Categoria, Integer>,
		PagingAndSortingRepository<Categoria, Integer> {
	
}
