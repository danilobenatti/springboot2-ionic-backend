package br.com.ecosensor.cursospringmc.resources.exceptions;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StandardError implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer status;
	private String message;
	private Long timeStamp;
	
	public StandardError(Integer status, String message, Long timeStamp) {
		super();
		this.status = status;
		this.message = message;
		this.timeStamp = timeStamp;
	}
	
}
