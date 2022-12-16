package br.com.ecosensor.cursospringmc.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import br.com.ecosensor.cursospringmc.security.JwtAuthenticationFilter;
import br.com.ecosensor.cursospringmc.security.JwtAutorizationFilter;
import br.com.ecosensor.cursospringmc.security.JwtUtil;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private Environment environment;
	
	@Autowired
	private JwtUtil jwtUtil;
	
	private static final String[] PUBLIC_MATCHERS = { "/h2-console/**" };
	
	private static final String[] PUBLIC_MATCHERS_GET = { "/produtos/**",
			"/categorias/**" };
	
	private static final String[] PUBLIC_MATCHERS_POST = { "/clientes/**",
			"/auth/forgot_password/**" };
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		if (Arrays.asList(environment.getActiveProfiles()).contains("test")) {
			http.headers().frameOptions().disable();
		}
		
		http.cors().and().csrf().disable();
		http.authorizeRequests()
				.antMatchers(HttpMethod.GET, PUBLIC_MATCHERS_GET).permitAll()
				.antMatchers(HttpMethod.POST, PUBLIC_MATCHERS_POST).permitAll()
				.antMatchers(PUBLIC_MATCHERS).permitAll().anyRequest()
				.authenticated();
		http.addFilter(
				new JwtAuthenticationFilter(authenticationManager(), jwtUtil));
		http.addFilter(new JwtAutorizationFilter(authenticationManager(),
				jwtUtil, userDetailsService));
		http.sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	}
	
	/*
	 * ~~(Migrate manually based on
	 * https://spring.io/blog/2022/02/21/spring-security-without-the-
	 * websecurityconfigureradapter)~~>
	 */@Override
	protected void configure(AuthenticationManagerBuilder auth)
			throws Exception {
		auth.userDetailsService(userDetailsService)
				.passwordEncoder(bCryptPasswordEncoder());
	}
	
	@Bean
	CorsConfigurationSource corsConfigurationSource() {
		final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**",
				new CorsConfiguration().applyPermitDefaultValues());
		return source;
	}
	
	@Bean
	BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
}
