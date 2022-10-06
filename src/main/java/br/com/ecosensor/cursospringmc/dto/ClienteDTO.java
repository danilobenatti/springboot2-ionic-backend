package br.com.ecosensor.cursospringmc.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import br.com.ecosensor.cursospringmc.domain.Cliente;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ClienteDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	@NotEmpty(message = "Atributo 'nome': é obrigatório.")
	@Size(min = 3, max = 120,
			message = "Atributo 'nome': mínimo de 3 e máximo de 120 caracteres.")
	private String name;
	
	@NotEmpty(message = "Atributo 'e-mail': é obrigatório.")
	@Email(message = "Atributo 'e-mail': está inválido.")
	private String email;
	
	public ClienteDTO(Cliente obj) {
		this.id = obj.getId();
		this.name = obj.getName();
		this.email = obj.getEmail();
	}
	
}
