package br.com.ecosensor.cursospringmc.dto;

import java.io.Serializable;

import br.com.ecosensor.cursospringmc.domain.Categoria;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CategoriaDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	private String name;
	
	public CategoriaDTO (Categoria obj) {
		this.id = obj.getId();
		this.name = obj.getName();
	}
	
}
