package com.springbootbase.webapi;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.StringUtils;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Value("${api.security.token.name}")
	private String tokenName;
	@Value("${api.security.token.key}")
	private String tokenKey;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		if (!StringUtils.hasLength(tokenKey)) {
			tokenKey = UUID.randomUUID().toString();
		}

		APIKey509Filter filter = new APIKey509Filter(tokenName);
		filter.setAuthenticationManager(authentication -> {
			// Principal validates the APIKey
			if (authentication.getPrincipal() == null) {
				throw new BadCredentialsException("Access Denied");
			}
			String apikey = (String)authentication.getPrincipal();
			if (authentication.getPrincipal() != null && this.tokenKey.equals(apikey)) {
				authentication.setAuthenticated(true);
				return authentication;
			} else {
				throw new BadCredentialsException("Access Denied");
			}
		});

		http.authorizeRequests()
			.antMatchers("/health")
			.permitAll()
		.and()
		.antMatcher("/v?/**")
		.csrf().disable()
		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		.and()
		.addFilter(filter)
		.authorizeRequests()
		.anyRequest()
		.authenticated();
	}

	@Bean
	public PasswordEncoder passwordEncoder() 
	{
		return new BCryptPasswordEncoder();
	}
}
