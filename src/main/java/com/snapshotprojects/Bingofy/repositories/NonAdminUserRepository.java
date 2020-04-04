package com.snapshotprojects.Bingofy.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import com.snapshotprojects.Bingofy.entities.ApplicationUser;
import com.snapshotprojects.Bingofy.entities.NonAdminUser;

@Repository
public interface NonAdminUserRepository extends JpaRepository<NonAdminUser, Long> {
	NonAdminUser findByName(String name);
}
