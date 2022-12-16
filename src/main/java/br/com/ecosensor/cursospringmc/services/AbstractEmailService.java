package br.com.ecosensor.cursospringmc.services;

import java.util.Date;
import java.util.Optional;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import br.com.ecosensor.cursospringmc.domain.Cliente;
import br.com.ecosensor.cursospringmc.domain.Pedido;

public abstract class AbstractEmailService implements EmailService {
	
	@Value("${default.sender}")
	private String sender;
	
	@Autowired
	private TemplateEngine templateEngine;
	
	@Autowired
	private JavaMailSender mailSender;
	
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
	
	protected String htmlFromTemplateOrder(Pedido obj) {
		Context context = new Context();
		context.setVariable("order", obj);
		return templateEngine.process("email/orderConfirmation.html", context);
	}
	
	@Override
	public void sendOrderConfirmationEmailHtml(Pedido obj) {
		MimeMessage mimeMessage = null;
		try {
			mimeMessage = prepareMimeMessageFromPedido(obj);
		} catch (MessagingException e) {
			sendOrderConfirmationEmail(obj);
		}
		sendEmailHtml(mimeMessage);
	}
	
	protected MimeMessage prepareMimeMessageFromPedido(Pedido obj)
			throws MessagingException {
		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper messageHelper;
		messageHelper = new MimeMessageHelper(message, true);
		messageHelper.setTo(obj.getClient().getEmail());
		messageHelper.setFrom(sender);
		messageHelper.setSubject("Confirmation Order! Code: " + obj.getId());
		messageHelper.setSentDate(new Date(System.currentTimeMillis()));
		messageHelper.setText(htmlFromTemplateOrder(obj), true);
		return message;
	}
	
	@Override
	public void sendNewPasswordEmail(Optional<Cliente> client,
			String newPassword) {
		SimpleMailMessage mailMessage = prepareNewPasswordEmail(client,
				newPassword);
		sendEmail(mailMessage);
		
	}
	
	protected SimpleMailMessage prepareNewPasswordEmail(
			Optional<Cliente> client, String newPassword) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(client.get().getEmail());
		message.setFrom(sender);
		message.setSubject("New password request: " + client.get().getEmail());
		message.setSentDate(new Date(System.currentTimeMillis()));
		message.setText("New Password: " + newPassword);
		return message;
	}
	
}
