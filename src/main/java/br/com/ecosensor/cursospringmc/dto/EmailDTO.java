package br.com.ecosensor.cursospringmc.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class EmailDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@NotEmpty(message = "Atributo 'e-mail': é obrigatório.")
	@Email(message = "Atributo 'e-mail': está inválido.")
	private String email;
	
}
