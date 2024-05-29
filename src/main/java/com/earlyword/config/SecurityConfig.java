package com.earlyword.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.earlyword.service.CustomOAuth2UserService;

@Configuration
@EnableWebSecurity
public class SecurityConfig { //WebSecurityConfigurerAdapter was deprecated

	private final CustomOAuth2UserService customOAuth2UserService;

	public SecurityConfig(CustomOAuth2UserService customOAuth2UserService) {
		this.customOAuth2UserService = customOAuth2UserService;
	}

	@Bean
	public BCryptPasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.csrf()
			.disable()
			.sessionManagement()
			.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
			.and()
			.formLogin()
			.disable()
			.httpBasic()
			.disable()
			.authorizeHttpRequests(
				authorize -> authorize.requestMatchers("/api/user").permitAll().anyRequest().authenticated())
			.oauth2Login(oauth2 -> oauth2.userInfoEndpoint().userService(customOAuth2UserService));
		return http.build();
	}
}