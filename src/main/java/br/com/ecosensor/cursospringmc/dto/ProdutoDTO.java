package br.com.ecosensor.cursospringmc.dto;

import java.io.Serializable;

import br.com.ecosensor.cursospringmc.domain.Produto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ProdutoDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	private String name;
	
	private Double unitPrice;
	
	public ProdutoDTO(Produto obj) {
		this.id = obj.getId();
		this.name = obj.getName();
		this.unitPrice = obj.getUnitPrice();
	}
}
