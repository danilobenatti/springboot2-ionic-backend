package br.com.ecosensor.cursospringmc.services;

import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.ecosensor.cursospringmc.domain.Cliente;
import br.com.ecosensor.cursospringmc.repositories.ClienteRepository;
import br.com.ecosensor.cursospringmc.services.exceptions.ObjectNotFoundException;

@Service
public class AuthService {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	@Autowired
	private EmailService emailService;
	
	private Random random = new Random();
	
	public void sendNewPassword(String email) {
		
		Optional<Cliente> client = clienteRepository.findByEmail(email);
		if (!client.isPresent()) {
			throw new ObjectNotFoundException("Object (Cliente) not found!");
		}
		String newPassword = newPassword();
		client.ifPresent(cli -> cli.setPassword(encoder.encode(newPassword)));
		clienteRepository.save(client.get());
		emailService.sendNewPasswordEmail(client, newPassword);
	}
	
	private String newPassword() {
		char[] vet = new char[10];
		for (int i = 0; i < vet.length; i++) {
			vet[i] = randomChar();
		}
		return new String(vet);
	}
	
	private char randomChar() {
		int opt = random.nextInt(3);
		switch (opt) {
			case 0: // generate a numerical digit.
				return (char) (random.nextInt(10) + 48);
			case 1: // generate an upper case letter.
				return (char) (random.nextInt(26) + 65);
			case 2: // generate an lower case letter.
				return (char) (random.nextInt(26) + 97);
			default:
				break;
		}
		return 0;
	}
	
}
