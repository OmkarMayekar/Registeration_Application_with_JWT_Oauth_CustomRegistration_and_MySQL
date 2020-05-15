package com.snapshotprojects.Bingofy.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.snapshotprojects.Bingofy.enums.HttpStatusCode;
import com.snapshotprojects.Bingofy.exceptions.ValidationException;
import com.snapshotprojects.Bingofy.responses.APIResponse;
import com.snapshotprojects.Bingofy.services.OperationsService;

@RestController
@RequestMapping("/adminUser")
public class AdminUserController {

	@Autowired
	OperationsService operationsService;

	@ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public APIResponse<?> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
		Map<HttpStatusCode, Object> errors = new HashMap<>();
		ex.getBindingResult().getFieldErrors()
				.forEach(error -> errors.put(HttpStatusCode.VALIDATION_FAILED, error.getDefaultMessage()));
		return APIResponse.response(HttpStatusCode.VALIDATION_FAILED.getOrdinal(), errors);
	}

	@GetMapping("/getAllNonAdminUsersInfo")
	@ResponseBody
	@PreAuthorize("hasAuthority('nonadmin:write')")
	public APIResponse<?> getInventoryAttributes(HttpServletResponse httpResponse, HttpServletRequest request)
			throws ValidationException {
		List<String> serviceReponseForRegisterUser = operationsService.retrieveAllNonAdminUsersInfo();
		return APIResponse.response(HttpStatusCode.SUCCESS.getOrdinal(), serviceReponseForRegisterUser);
	}

	private final static List<String> STUDENTS = Arrays.asList("first element", "second element", "third element",
			"fourth element");

	@GetMapping
	@PreAuthorize("hasAuthority('nonadmin:read')")
	public List<String> getAllStudents() {
		return STUDENTS;
	}

	@DeleteMapping(path = "{non_admin_user}")
	@PreAuthorize("hasAuthority('nonadmin:write')")
	public void deleteStudent(@PathVariable("non_admin_user") Integer non_admin_user) {
		System.out.println(non_admin_user);
	}
}
