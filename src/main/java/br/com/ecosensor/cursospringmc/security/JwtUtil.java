package br.com.ecosensor.cursospringmc.security;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtUtil {
	
	@Value(value = "${jwt.secretkey}")
	private String jwtSecretKey;
	
	@Value(value = "${jwt.expirationtime}")
	private Long jwtExpirationMs;
	
	public String tokenGenerator(String username) {
		return Jwts.builder().setSubject(username)
				.setExpiration(
						new Date(System.currentTimeMillis() + jwtExpirationMs))
				.signWith(SignatureAlgorithm.HS512, jwtSecretKey.getBytes())
				.compact();
	}
	
	public boolean tokenValid(String token) {
		Claims claims = getClaims(token);
		if (claims != null) {
			String username = claims.getSubject();
			Date expiration = claims.getExpiration();
			Date now = new Date(System.currentTimeMillis());
			if (username != null && expiration != null
					&& now.before(expiration)) {
				return true;
			}
		}
		return false;
	}
	
	private Claims getClaims(String token) {
		try {
			return Jwts.parser().setSigningKey(jwtSecretKey.getBytes())
					.parseClaimsJws(token).getBody();
		} catch (Exception e) {
			return null;
		}
	}
	
	public String getUsername(String token) {
		Claims claims = getClaims(token);
		if (claims != null) {
			return claims.getSubject();
		}
		return null;
	}
	
}
