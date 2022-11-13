package br.com.ecosensor.cursospringmc.services;

import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.SimpleMailMessage;

public class MockMailService extends AbstractEmailService {
	
	private static final Logger LOG = LoggerFactory
			.getLogger(MockMailService.class);
	
	@Override
	public void sendEmail(SimpleMailMessage message) {
		LOG.info("Simulating email sending...");
		LOG.info(message.toString());
		LOG.info("Email sent!");
	}
	
	@Override
	public void sendEmailHtml(MimeMessage message) {
		LOG.info("Simulating HTML email sending...");
		LOG.info(message.toString());
		LOG.info("Email sent!");
	}
	
}
