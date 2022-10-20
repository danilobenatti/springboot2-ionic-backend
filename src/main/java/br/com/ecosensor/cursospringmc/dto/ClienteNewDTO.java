package br.com.ecosensor.cursospringmc.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import br.com.ecosensor.cursospringmc.services.validation.ClientInsert;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@ClientInsert
public class ClienteNewDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@NotEmpty(message = "Atributo 'nome': é obrigatório.")
	@Size(min = 3, max = 120,
		message = "Atributo 'nome': mínimo de 3 e máximo de 120 caracteres.")
	private String name;
	
	@NotEmpty(message = "Atributo 'e-mail': é obrigatório.")
	@Email(message = "Atributo 'e-mail': está inválido.")
	private String email;
	
	@NotEmpty(message = "Atributo 'CPF ou CNPJ': é obrigatório.")
	private String cpfCnpj;
	
	private Integer type;
	
	@NotEmpty(message = "Atributo 'logradouro': é obrigatório.")
	private String street;
	
	@NotEmpty(message = "Atributo 'número': é obrigatório.")
	private String number;
	
	private String complement;
	
	private String district;
	
	@NotEmpty(message = "Atributo 'Código Postal - CEP': é obrigatório.")
	private String zipCode;
	
	@NotEmpty(message = "Atributo 'Telefone 1': é obrigatório.")
	private String phone1;
	
	private String phone2;
	
	private String phone3;
	
	private Integer cityid;
	
}
