package br.com.ecosensor.cursospringmc.resources.exceptions;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.ecosensor.cursospringmc.services.exceptions.AuthorizationException;
import br.com.ecosensor.cursospringmc.services.exceptions.DataIntegrityException;
import br.com.ecosensor.cursospringmc.services.exceptions.ObjectNotFoundException;

@ControllerAdvice
public class ResourceExceptionHandler {
	
	@ExceptionHandler(value = ObjectNotFoundException.class)
	public ResponseEntity<StandardError> objectNotFound(
			ObjectNotFoundException ex, HttpServletRequest request) {
		StandardError error = new StandardError(HttpStatus.NOT_FOUND.value(),
				ex.getMessage(), System.currentTimeMillis());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	}
	
	@ExceptionHandler(value = DataIntegrityException.class)
	public ResponseEntity<StandardError> dataIntegrity(
			DataIntegrityException ex, HttpServletRequest request) {
		StandardError error = new StandardError(HttpStatus.BAD_REQUEST.value(),
				ex.getMessage(), System.currentTimeMillis());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
	}
	
	@ExceptionHandler(value = MethodArgumentNotValidException.class)
	public ResponseEntity<StandardError> argumentNotValid(
			MethodArgumentNotValidException ex, HttpServletRequest request) {
		ValidationError error = new ValidationError(
				HttpStatus.BAD_REQUEST.value(), "Validation error!",
				System.currentTimeMillis());
		for (FieldError e : ex.getBindingResult().getFieldErrors()) {
			error.addError(e.getField(), e.getDefaultMessage());
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
	}
	
	@ExceptionHandler(value = AuthorizationException.class)
	public ResponseEntity<StandardError> authorization(
			AuthorizationException ex, HttpServletRequest request) {
		StandardError error = new StandardError(HttpStatus.FORBIDDEN.value(),
				ex.getMessage(), System.currentTimeMillis());
		return ResponseEntity.status(HttpStatus.FORBIDDEN).body(error);
	}
	
}
