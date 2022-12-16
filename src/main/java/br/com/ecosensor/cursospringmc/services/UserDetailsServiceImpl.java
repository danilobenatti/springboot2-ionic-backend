package br.com.ecosensor.cursospringmc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.ecosensor.cursospringmc.domain.Cliente;
import br.com.ecosensor.cursospringmc.repositories.ClienteRepository;
import br.com.ecosensor.cursospringmc.security.UserSpringSecurity;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	
	@Autowired
	private ClienteRepository repository;
	
	@Override
	public UserDetails loadUserByUsername(String email)
			throws UsernameNotFoundException {
		Optional<Cliente> client = repository.findByEmail(email);
		if (!client.isPresent()) {
			throw new UsernameNotFoundException(email);
		}
		return new UserSpringSecurity(client.get().getId(),
				client.get().getEmail(), client.get().getPassword(),
				client.get().getProfiles());
	}
	
}
