package br.com.ecosensor.cursospringmc.dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

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
	
	@NotEmpty(message = "Atributo 'nome': é obrigatório.")
	@Size(min = 5, max = 80,
		message = "Atributo 'nome': mínimo de 5 e máximo de 80 caracteres.")
	private String name;
	
	public CategoriaDTO(Categoria obj) {
		this.id = obj.getId();
		this.name = obj.getName();
	}
	
}
