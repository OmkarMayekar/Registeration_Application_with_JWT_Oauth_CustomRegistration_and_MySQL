package com.snapshotprojects.Bingofy.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/management/api/v1/students")
public class AdminUserController {
	private final static List<String> STUDENTS = Arrays.asList(
			"first element","second element", "third element", "fourth element");

	@GetMapping
	@PreAuthorize("hasAuthority('nonadmin:read')")
	public List<String> getAllStudents() {
		System.out.println("getAllElements");
		return STUDENTS;
	}

	@DeleteMapping(path = "{non_admin_user}")
	@PreAuthorize("hasAuthority('nonadmin:write')")
	public void deleteStudent(@PathVariable("non_admin_user") Integer non_admin_user) {
		System.out.println("deleteStudent");
		System.out.println(non_admin_user);
	}
}
