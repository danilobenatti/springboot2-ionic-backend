package br.com.ecosensor.cursospringmc.resources;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ecosensor.cursospringmc.dto.EmailDTO;
import br.com.ecosensor.cursospringmc.security.JwtUtil;
import br.com.ecosensor.cursospringmc.security.UserSpringSecurity;
import br.com.ecosensor.cursospringmc.services.AuthService;
import br.com.ecosensor.cursospringmc.services.UserService;

@RestController
@RequestMapping(value = "/auth")
public class AuthResource {
	
	@Autowired
	private JwtUtil jwtUtil;
	
	@Autowired
	private AuthService authService;
	
	@PostMapping(path = "/refresh_token")
	public ResponseEntity<Void> refreshToken(HttpServletResponse response) {
		UserSpringSecurity userSpringSecurity = UserService.authenticated();
		String token = jwtUtil.tokenGenerator(userSpringSecurity.getUsername());
		response.addHeader("Authorization", "Bearer " + token);
		return ResponseEntity.noContent().build();
	}
	
	@PostMapping(path = "/forgot_password")
	public ResponseEntity<Void> forgotPassword(
			@Valid @RequestBody EmailDTO objDTO) {
		authService.sendNewPassword(objDTO.getEmail());
		return ResponseEntity.noContent().build();
	}
	
}
