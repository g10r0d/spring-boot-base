package com.springbootbase.webapi;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.access.ExceptionTranslationFilter;
import org.springframework.security.web.authentication.Http403ForbiddenEntryPoint;
import org.springframework.util.StringUtils;

@Configuration
@EnableWebSecurity
public class AuthTokenSecurityConfig extends WebSecurityConfigurerAdapter {

	@Value("${api.security.token.name}")
	private String tokenName;
	@Value("${api.security.token.key}")
	private String tokenKey;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		if (!StringUtils.hasLength(tokenKey)) {
			tokenKey = UUID.randomUUID().toString();
		}
		PreAuthTokenHeaderFilter filter = new PreAuthTokenHeaderFilter(tokenName);
		filter.setAuthenticationManager((Authentication authentiation) -> {
			String principal = (String) authentiation.getPrincipal();
			if (!tokenKey.equals(principal)) {
				throw new BadCredentialsException("Invalid Token");
			}
			authentiation.setAuthenticated(true);
			return authentiation;
		});

		http.antMatcher("/v?/**")
			.csrf().disable()
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().addFilter(filter)
			.addFilterBefore(new ExceptionTranslationFilter(new Http403ForbiddenEntryPoint()), filter.getClass())
			.authorizeRequests().anyRequest().authenticated();
	}
}
