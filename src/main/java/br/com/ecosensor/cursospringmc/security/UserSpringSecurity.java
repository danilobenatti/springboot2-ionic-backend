package br.com.ecosensor.cursospringmc.security;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import br.com.ecosensor.cursospringmc.domain.enums.PerfilUsuario;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class UserSpringSecurity implements UserDetails {
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	private String email;
	
	private String password;
	
	private Collection<? extends GrantedAuthority> authorities;
	
	public Integer getId() {
		return id;
	}
	
	public UserSpringSecurity(Integer id, String email, String password,
			Set<PerfilUsuario> userProfiles) {
		super();
		this.id = id;
		this.email = email;
		this.password = password;
		this.authorities = userProfiles.stream()
				.map(i -> new SimpleGrantedAuthority(i.getDescription()))
				.collect(Collectors.toList());
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}
	
	@Override
	public String getPassword() {
		return password;
	}
	
	@Override
	public String getUsername() {
		return email;
	}
	
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}
	
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}
	
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}
	
	@Override
	public boolean isEnabled() {
		return true;
	}
	
	public boolean hasHole(PerfilUsuario profile) {
		return getAuthorities()
				.contains(new SimpleGrantedAuthority(profile.getDescription()));
	}
	
}
