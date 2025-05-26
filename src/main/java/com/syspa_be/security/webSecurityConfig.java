package com.syspa_be.security;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
@Order(SecurityProperties.BASIC_AUTH_ORDER)
@EnableSpringDataWebSupport

public class webSecurityConfig {
	@Bean 
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception  {
	 http.cors(cors->cors.disable()).csrf(csrf->csrf.disable())
	 .authorizeHttpRequests(request -> request
	 .requestMatchers(("/api/**")).permitAll()
	 .anyRequest()
	.authenticated());
	 return http.build(); 
	}

	
	@Bean
	 public PasswordEncoder passwordEncoder() {
	 return new BCryptPasswordEncoder();
	 } 

	@Bean
	 public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
	 return config.getAuthenticationManager();
	    }

}
