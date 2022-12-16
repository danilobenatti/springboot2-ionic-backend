package br.com.ecosensor.cursospringmc.services;

import java.util.Optional;

import javax.mail.internet.MimeMessage;

import org.springframework.mail.SimpleMailMessage;

import br.com.ecosensor.cursospringmc.domain.Cliente;
import br.com.ecosensor.cursospringmc.domain.Pedido;

public interface EmailService {
	
	void sendOrderConfirmationEmail(Pedido obj);
	
	void sendEmail(SimpleMailMessage message);
	
	void sendOrderConfirmationEmailHtml(Pedido obj);
	
	void sendEmailHtml(MimeMessage message);
	
	void sendNewPasswordEmail(Optional<Cliente> client, String newPassword);
	
}
