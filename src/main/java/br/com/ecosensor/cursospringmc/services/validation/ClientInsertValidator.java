package br.com.ecosensor.cursospringmc.services.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import br.com.ecosensor.cursospringmc.domain.enums.TipoCliente;
import br.com.ecosensor.cursospringmc.dto.ClienteNewDTO;
import br.com.ecosensor.cursospringmc.resources.exceptions.FieldMessage;
import br.com.ecosensor.cursospringmc.services.validation.utils.BR;

public class ClientInsertValidator
		implements ConstraintValidator<ClientInsert, ClienteNewDTO> {
	
	@Override
	public void initialize(ClientInsert constraintAnnotation) {
		// document why this method is empty
	}
	
	@Override
	public boolean isValid(ClienteNewDTO client,
			ConstraintValidatorContext context) {
		
		List<FieldMessage> list = new ArrayList<>();
		
		if (client.getType() == null) {
			list.add(new FieldMessage("type", "Client type cannot be null."));
		}
		
		if (client.getType().equals(TipoCliente.PESSOAFISICA.getCode())
				&& !BR.isValidCpf(client.getCpfOuCnpj())) {
			list.add(new FieldMessage("cpfOuCnpj", "Invalid CPF."));
		}
		
		if (client.getType().equals(TipoCliente.PESSOAJURIDICA.getCode())
				&& !BR.isValidCnpj(client.getCpfOuCnpj())) {
			list.add(new FieldMessage("cpfOuCnpj", "Invalid CNPJ."));
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
