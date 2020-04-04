package com.snapshotprojects.Bingofy.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.snapshotprojects.Bingofy.exceptions.ValidationException;
import com.snapshotprojects.Bingofy.jwt.UserNameAndPasswordAuthenticationRequest;
import com.snapshotprojects.Bingofy.responses.APIResponse;
import com.snapshotprojects.Bingofy.responses.ErrorResponse;
import com.snapshotprojects.Bingofy.responses.ServiceResponse;
import com.snapshotprojects.Bingofy.responses.UserDTO;
import com.snapshotprojects.Bingofy.services.OnBoardingService;

@RestController
@RequestMapping("/registration")
public class RegistrationController {

	@Autowired
	OnBoardingService onBoardingSevice;

	@PostMapping
	@ResponseBody
	public APIResponse<?> registerUser(@RequestBody UserDTO userDto, HttpServletResponse httpResponse) {
		System.out.println("registerUser method called!!" + userDto);
		try {
			ServiceResponse serviceReponseForRegisterUser = onBoardingSevice.registerUser(userDto.getEmail(),
					userDto.getUsername(), userDto.getPassword(), userDto.isAdmin());
			System.out.println("controller is Admin user value : " + userDto.isAdmin());
			return APIResponse.response(serviceReponseForRegisterUser.getStatusCode(), serviceReponseForRegisterUser);
		} catch (ValidationException e) {
			httpResponse.setStatus(e.getHttpCode());
			return APIResponse.response(e.getHttpCode(), new ErrorResponse(e.getMessage()));
		}
	}
}