package br.com.ecosensor.cursospringmc.services;

import org.springframework.mail.SimpleMailMessage;

import br.com.ecosensor.cursospringmc.domain.Pedido;

public interface EmailService {
	
	void sendOrderConfirmationEmail(Pedido obj);
	
	void sendEmail(SimpleMailMessage message);
	
}
