package com.snapshotprojects.Bingofy.controller;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.snapshotprojects.Bingofy.enums.HttpStatusCode;
import com.snapshotprojects.Bingofy.exceptions.ValidationException;
import com.snapshotprojects.Bingofy.request.UserDTO;
import com.snapshotprojects.Bingofy.responses.APIResponse;
import com.snapshotprojects.Bingofy.responses.ErrorResponse;
import com.snapshotprojects.Bingofy.responses.ServiceResponse;
import com.snapshotprojects.Bingofy.services.OnBoardingService;

@RestController
@RequestMapping("/registration")
public class RegistrationController {

	@Autowired
	OnBoardingService onBoardingService;

	@ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<HttpStatusCode, String> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
		System.out.println("handleMethodArgumentNotValid called");
		Map<HttpStatusCode, String> errors = new HashMap<>();
		ex.getBindingResult().getFieldErrors()
				.forEach(error -> errors.put(HttpStatusCode.VALIDATION_FAILED, error.getDefaultMessage()));
		return errors;
	}

	@PostMapping
	@ResponseBody
	public APIResponse<?> registerUser(@Valid @RequestBody UserDTO userDto, HttpServletResponse httpResponse,
			HttpServletRequest request) {
		System.out.println("registerUser method called!!" + userDto);
		try {
			onBoardingService.validateUserRegisterationRequest(userDto);
			
			ServiceResponse serviceReponseForRegisterUser = onBoardingService.registerUser(userDto);

			return APIResponse.response(serviceReponseForRegisterUser.getStatusCode(), serviceReponseForRegisterUser);
		} catch (ValidationException e) {
			httpResponse.setStatus(e.getHttpCode());
			return APIResponse.response(e.getHttpCode(), new ErrorResponse(e.getMessage()));
		}
	}

	@GetMapping("/oauth")
	public Map<String, Object> user(@AuthenticationPrincipal OAuth2User principal) {
		System.out.println("Principal is : " + principal);
		return Collections.singletonMap("name", principal.getAttribute("name"));
	}
}