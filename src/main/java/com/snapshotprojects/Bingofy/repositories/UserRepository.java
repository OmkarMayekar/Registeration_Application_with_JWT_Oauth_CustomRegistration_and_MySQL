package com.snapshotprojects.Bingofy.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.snapshotprojects.Bingofy.entities.ApplicationUser;

@Repository
public interface UserRepository extends JpaRepository<ApplicationUser, Long> {
	/* ApplicationUser findByUsername(String username); */
	@Query("SELECT u FROM application_user u WHERE u.email = ?1")
	ApplicationUser findByEmail(String email);
	@Query("SELECT u FROM application_user u WHERE u.uuid = ?1")
	ApplicationUser findUserByUUID(String uuid);
	@Query("SELECT u FROM application_user u WHERE u.username = ?1")
	ApplicationUser findByUsername(String username);
}
