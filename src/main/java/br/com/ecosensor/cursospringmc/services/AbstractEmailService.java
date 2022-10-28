package br.com.ecosensor.cursospringmc.services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;

import br.com.ecosensor.cursospringmc.domain.Pedido;

public abstract class AbstractEmailService implements EmailService {
	
	@Value("${default.sender}")
	private String sender;
	
	@Override
	public void sendOrderConfirmationEmail(Pedido obj) {
		SimpleMailMessage mailMessage = prepareSimpleMailMessageFromPedido(obj);
		sendEmail(mailMessage);
	}
	
	protected SimpleMailMessage prepareSimpleMailMessageFromPedido(Pedido obj) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(obj.getClient().getEmail());
		message.setFrom(sender);
		message.setSubject("Confirm order: " + obj.getId());
		message.setSentDate(new Date(System.currentTimeMillis()));
		message.setText(obj.toString());
		return message;
	}
	
}
