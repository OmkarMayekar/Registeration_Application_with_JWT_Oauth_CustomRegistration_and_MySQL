package com.snapshotprojects.Bingofy.services;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.snapshotprojects.Bingofy.entities.ApplicationUser;
import com.snapshotprojects.Bingofy.repositories.NonAdminUserRepository;
import com.snapshotprojects.Bingofy.repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	NonAdminUserRepository nonAdminUserRepository;

	@Autowired
	public UserServiceImpl(PasswordEncoder passwordEncoder) {
		System.out.println("UserServiceImpl constructor initialized");
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	public ApplicationUser findByUsername(String username) {
		return userRepository.findByUsername(username);
	}

	@Override
	public ApplicationUser save(ApplicationUser applicationUser) {
		return userRepository.save(applicationUser);
		/*
		 * NonAdminUser nonAdminUser = new NonAdminUser();
		 * nonAdminUser.setName(applicationUser.getUsername());
		 * nonAdminUser.setPassword(applicationUser.getPassword());
		 * nonAdminUserRepository.save(nonAdminUser);
		 */
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		System.out.println("loadUserByName method called in UserServiceImpl and username is : " + username);
		ApplicationUser user = null;
		Set<GrantedAuthority> grantedAuthorities = null;
		try {
			user = userRepository.findByUsername(username);
			System.out.println("User found in database : " + user);
			if (user == null) {
				throw new UsernameNotFoundException("Invalid username or password");
			}

			grantedAuthorities = new HashSet<>();
			for (GrantedAuthority role : user.getAuthorities()) {
				grantedAuthorities.add(new SimpleGrantedAuthority(role.getAuthority()));
				// System.out.println("granted AUTHORITIES : " + grantedAuthorities);
			}
		} catch (Exception e) {
			System.out.println("Exception occurred in retrieving user : " + e);
		}
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
				grantedAuthorities);
	}
}
