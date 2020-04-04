package com.snapshotprojects.Bingofy.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.snapshotprojects.Bingofy.entities.NonAdminUser;

@RestController
@RequestMapping("/management/api/v1/students")
public class AdminUserController {
	private final static List<NonAdminUser> STUDENTS = Arrays.asList(
			new NonAdminUser((long) 1, "James Bond", "password"), new NonAdminUser((long) 2, "Maria Jones", "password"),
			new NonAdminUser((long) 3, "Anna smith", "password"));

	@GetMapping
	@PreAuthorize("hasAuthority('nonadmin:read')")
	public List<NonAdminUser> getAllStudents(NonAdminUser non_admin_user) {
		System.out.println("getAllStudents");
		return STUDENTS;
	}

	@PostMapping
	@PreAuthorize("hasAnyRole('Role_ADMIN','Role_ADMINTRAINEE')")
	public void registerStudent(@RequestBody NonAdminUser non_admin_user) {
		System.out.println("registerStudent");
		System.out.println(non_admin_user);
	}

	@DeleteMapping(path = "{non_admin_user}")
	@PreAuthorize("hasAuthority('nonadmin:write')")
	public void deleteStudent(@PathVariable("non_admin_user") Integer non_admin_user) {
		System.out.println("deleteStudent");
		System.out.println(non_admin_user);
	}

	@PutMapping(path = "{non_admin_user}")
	@PreAuthorize("hasAuthority('nonadmin:write')")
	public void updateStudent(@PathVariable("non_admin_user") Integer studentId,
			@RequestBody NonAdminUser non_admin_user) {
		System.out.println("updateStudent");
		System.out.println(String.format("%s %s", studentId, non_admin_user));
	}
}
