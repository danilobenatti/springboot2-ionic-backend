package br.com.ecosensor.cursospringmc.services;

import org.springframework.security.core.context.SecurityContextHolder;

import br.com.ecosensor.cursospringmc.security.UserSpringSecurity;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserService {
	
	public static UserSpringSecurity authenticated() {
		try {
			return (UserSpringSecurity) SecurityContextHolder.getContext()
					.getAuthentication().getPrincipal();
		} catch (Exception ex) {
			return null;
		}
	}
	
}
