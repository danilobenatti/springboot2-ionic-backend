package br.com.ecosensor.cursospringmc.services.validation;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.ecosensor.cursospringmc.domain.Cliente;
import br.com.ecosensor.cursospringmc.domain.enums.TipoCliente;
import br.com.ecosensor.cursospringmc.dto.ClienteNewDTO;
import br.com.ecosensor.cursospringmc.repositories.ClienteRepository;
import br.com.ecosensor.cursospringmc.resources.exceptions.FieldMessage;
import br.com.ecosensor.cursospringmc.services.validation.utils.BR;

public class ClientInsertValidator
		implements ConstraintValidator<ClientInsert, ClienteNewDTO> {
	
	@Autowired
	private ClienteRepository repository;
	
	@Override
	public void initialize(ClientInsert constraintAnnotation) {
		// document why this method is empty
	}
	
	@Override
	public boolean isValid(ClienteNewDTO client,
			ConstraintValidatorContext context) {
		
		List<FieldMessage> list = new ArrayList<>();
		
		String fieldType = "type";
		String fieldCpfCnpj = "cpfCnpj";
		String fieldEmail = "email";
		
		if (client.getType() == null) {
			list.add(new FieldMessage(fieldType, "Client type cannot be null."));
		}
		
		if (client.getType().equals(TipoCliente.PESSOAFISICA.getCode())
				&& !BR.isValidCpf(client.getCpfCnpj())) {
			list.add(new FieldMessage(fieldCpfCnpj, "Invalid CPF."));
		}
		
		if (client.getType().equals(TipoCliente.PESSOAJURIDICA.getCode())
				&& !BR.isValidCnpj(client.getCpfCnpj())) {
			list.add(new FieldMessage(fieldCpfCnpj, "Invalid CNPJ."));
		}
		
		Optional<Cliente> opt1 = repository.findByEmail(client.getEmail());
		if (opt1.isPresent()) {
			list.add(new FieldMessage(fieldEmail, "Already existing e-mail."));
		}
		
		Optional<Cliente> opt2 = repository.findByCpfCnpj(client.getCpfCnpj());
		if (opt2.isPresent()) {
			list.add(new FieldMessage(fieldCpfCnpj, "Already existing CPF/CNPJ."));
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
