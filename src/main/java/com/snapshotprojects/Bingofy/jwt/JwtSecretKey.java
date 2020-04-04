package com.snapshotprojects.Bingofy.jwt;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.jsonwebtoken.security.Keys;

@Configuration
public class JwtSecretKey {

	private final JwtConfig jwtConfig;

	@Autowired
	public JwtSecretKey(JwtConfig jwtConfig) {
		System.out.println("JwtSecretKey constructor initialized");
		this.jwtConfig = jwtConfig;
	}

	@Bean
	public SecretKey secretKey() {
		System.out.println("secretKey() called from JwtSecretKey");
		return Keys.hmacShaKeyFor(jwtConfig.getSecretKey().getBytes());
	}
}