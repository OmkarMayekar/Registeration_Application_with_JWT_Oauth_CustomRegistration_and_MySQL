package com.snapshotprojects.Bingofy.services;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.snapshotprojects.Bingofy.entities.ApplicationUser;

@Service
public interface UserService extends UserDetailsService {

	ApplicationUser findByUsername(String username);

	ApplicationUser save(ApplicationUser applicationUser);
}