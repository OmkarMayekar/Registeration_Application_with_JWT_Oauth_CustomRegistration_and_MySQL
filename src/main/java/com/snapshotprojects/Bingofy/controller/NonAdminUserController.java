package com.snapshotprojects.Bingofy.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.snapshotprojects.Bingofy.entities.NonAdminUser;

@RestController
@RequestMapping("api/v1/nonadminusers")
@ResponseBody
public class NonAdminUserController {
	private final static List<NonAdminUser> STUDENTS = Arrays.asList(
			new NonAdminUser((long) 1, "James Bond", "passowrd"), new NonAdminUser((long) 2, "Maria Jones", "passowrd"),
			new NonAdminUser((long) 3, "Anna smith", "passowrd"));

	@GetMapping
	public List<NonAdminUser> getAllStudents() {
		return STUDENTS;
	}

	@SuppressWarnings("unlikely-arg-type")
	@GetMapping(path = "{non_admin_user_id}")
	public NonAdminUser getStudent(@PathVariable("non_admin_user_id") Integer non_admin_user_id) {
		return STUDENTS.stream().filter(non_admin_user -> non_admin_user_id.equals(non_admin_user.getId())).findFirst()
				.orElseThrow(() -> new IllegalStateException("Student" + non_admin_user_id + "does not exists"));
	}
}