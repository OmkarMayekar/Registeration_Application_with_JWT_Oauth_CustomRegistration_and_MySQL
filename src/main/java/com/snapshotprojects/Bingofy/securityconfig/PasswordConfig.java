package com.snapshotprojects.Bingofy.securityconfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class PasswordConfig {
	@Bean
	public PasswordEncoder passwordEncoder() {
		System.out.println("passwordEncoder() from PasswordConfig called");
		return new BCryptPasswordEncoder(10);
	}
}
