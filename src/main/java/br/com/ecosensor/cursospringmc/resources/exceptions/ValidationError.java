package br.com.ecosensor.cursospringmc.resources.exceptions;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ValidationError extends StandardError {
	private static final long serialVersionUID = 1L;
	
	private List<FieldMessage> errors = new ArrayList<>();
	
	public ValidationError(Integer status, String message, Long timeStamp) {
		super(status, message, timeStamp);
	}
	
	public void addError(String fieldname, String message) {
		errors.add(new FieldMessage(fieldname, message));
	}
	
}
