package com.snapshotprojects.Bingofy.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.snapshotprojects.Bingofy.entities.ApplicationUser;

@Repository
public interface UserRepository extends JpaRepository<ApplicationUser, Long> {
	ApplicationUser findByUsername(String username);
	ApplicationUser findByEmail(String email);
}
