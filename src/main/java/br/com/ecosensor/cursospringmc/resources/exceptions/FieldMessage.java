package br.com.ecosensor.cursospringmc.resources.exceptions;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FieldMessage implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String fieldname;
	
	private String message;
	
}
