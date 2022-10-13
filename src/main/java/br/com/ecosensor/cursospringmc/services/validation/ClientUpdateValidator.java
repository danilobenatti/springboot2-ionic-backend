package br.com.ecosensor.cursospringmc.services.validation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import br.com.ecosensor.cursospringmc.domain.Cliente;
import br.com.ecosensor.cursospringmc.dto.ClienteDTO;
import br.com.ecosensor.cursospringmc.repositories.ClienteRepository;
import br.com.ecosensor.cursospringmc.resources.exceptions.FieldMessage;

public class ClientUpdateValidator
		implements ConstraintValidator<ClientUpdate, ClienteDTO> {
	
	@Autowired
	private HttpServletRequest request;
	
	@Autowired
	private ClienteRepository repository;
	
	@Override
	public void initialize(ClientUpdate constraintAnnotation) {
		// document why this method is empty
	}
	
	@Override
	public boolean isValid(ClienteDTO client,
			ConstraintValidatorContext context) {
		
		@SuppressWarnings("unchecked")
		Map<String, String> map = (Map<String, String>) request
				.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
		
		Integer uriId = Integer.parseInt(map.get("id"));
		
		List<FieldMessage> list = new ArrayList<>();
		
		Optional<Cliente> opt1 = repository.findByEmail(client.getEmail());
		if (opt1.isPresent() && !opt1.get().getId().equals(uriId)) {
			list.add(new FieldMessage("email", "Already existing e-mail."));
		}
		
		for (FieldMessage msg : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(msg.getMessage())
					.addPropertyNode(msg.getFieldname())
					.addConstraintViolation();
		}
		
		return list.isEmpty();
		
	}
	
}
