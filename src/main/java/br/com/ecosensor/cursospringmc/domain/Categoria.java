package br.com.ecosensor.cursospringmc.domain;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = {"id"})
@Builder
public class Categoria implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private int id;
	
	private String nome;
	
}
