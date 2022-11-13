package br.com.ecosensor.cursospringmc.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.ecosensor.cursospringmc.domain.Categoria;
import br.com.ecosensor.cursospringmc.domain.Produto;

@Repository
public interface ProdutoRepository extends CrudRepository<Produto, Integer> {
	
	@Transactional(readOnly = true)
	@Query(value = "SELECT DISTINCT prod FROM product prod "
			+ "INNER JOIN prod.categories cat "
			+ "WHERE LOWER(prod.name) LIKE LOWER(CONCAT('%',:name,'%')) "
			+ "AND cat IN :categories ")
	Page<Produto> search(@Param(value = "name") String name,
			@Param(value = "categories") Iterable<Categoria> categories,
			Pageable pageRequest);
	
	@Transactional(readOnly = true)
	Page<Produto> findDistinctByNameContainingIgnoreCaseAndCategoriesIn(
			String name, Iterable<Categoria> categories, Pageable pageRequest);
	
}
