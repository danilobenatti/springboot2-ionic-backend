package br.com.ecosensor.cursospringmc.dto;

import java.io.Serializable;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ClienteNewDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String name;
	
	private String email;
	
	private String cpfOuCnpj;
	
	private Integer clientType;
	
	private String street;
	
	private String number;
	
	private String complement;
	
	private String district;
	
	private String zipCode;
	
	private String phone1;
	
	private String phone2;
	
	private String phone3;
	
	private Integer cityid;
	
}
