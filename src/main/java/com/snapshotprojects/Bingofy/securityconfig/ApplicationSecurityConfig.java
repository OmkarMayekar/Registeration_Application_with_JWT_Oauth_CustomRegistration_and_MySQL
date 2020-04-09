package com.snapshotprojects.Bingofy.securityconfig;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.snapshotprojects.Bingofy.enums.ApplicationUserPermission;
import com.snapshotprojects.Bingofy.enums.ApplicationUserRole;
import com.snapshotprojects.Bingofy.jwt.JwtConfig;
import com.snapshotprojects.Bingofy.jwt.JwtTokenVerifer;
import com.snapshotprojects.Bingofy.jwt.JwtUserNamePasswordAuthenticationFilter;
import com.snapshotprojects.Bingofy.services.UserService;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {

	private PasswordEncoder passwordEncoder;
	private UserService applicationUserService;
	private SecretKey secretKey;
	private JwtConfig jwtConfig;

	@Autowired
	public ApplicationSecurityConfig(PasswordEncoder passwordEncoder, UserService applicationUserService,
			SecretKey secretKey, JwtConfig jwtConfig) {
		System.out.println("ApplicationSecurityConfig constructor initialized");
		this.passwordEncoder = passwordEncoder;
		this.applicationUserService = applicationUserService;
		this.secretKey = secretKey;
		this.jwtConfig = jwtConfig;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		System.out.println("configure(http) called");
		http.csrf().disable().authorizeRequests().and().sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
				.addFilter(new JwtUserNamePasswordAuthenticationFilter(authenticationManager(), jwtConfig, secretKey))
				.addFilterAfter(new JwtTokenVerifer(secretKey, jwtConfig),
						JwtUserNamePasswordAuthenticationFilter.class)
				.authorizeRequests().antMatchers("/*", "index", "/css/*", "/js/*").permitAll().anyRequest()
				.authenticated().and().oauth2Login().loginPage("/login.html");
	}

	@Bean
	public DaoAuthenticationProvider daoAuthenticationProvider() {
		System.out.println("daoAuthenticationProvider called");
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setPasswordEncoder(passwordEncoder);
		provider.setUserDetailsService(applicationUserService);
		return provider;
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		System.out.println("configure(auth) called");
		auth.authenticationProvider(daoAuthenticationProvider());
	}
}
