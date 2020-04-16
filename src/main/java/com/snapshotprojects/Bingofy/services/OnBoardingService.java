package com.snapshotprojects.Bingofy.services;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.snapshotprojects.Bingofy.entities.ApplicationUser;
import com.snapshotprojects.Bingofy.enums.ApplicationUserRole;
import com.snapshotprojects.Bingofy.enums.HttpStatusCode;
import com.snapshotprojects.Bingofy.enums.ResponseFlag;
import com.snapshotprojects.Bingofy.exceptions.ValidationException;
import com.snapshotprojects.Bingofy.repositories.UserRepository;
import com.snapshotprojects.Bingofy.request.UserDTO;
import com.snapshotprojects.Bingofy.responses.ServiceResponse;
import com.snapshotprojects.Bingofy.utilityclasses.CustomResponseUtilityClass;

@Service
public class OnBoardingService {
	@Autowired
	UserService userService;

	@Autowired
	UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	CustomResponseUtilityClass customResponseUtilityClass;
	
	@Autowired
	ServiceResponse response;

	public ServiceResponse registerUser(UserDTO userDto)
			throws ValidationException {
		try {
			String email = userDto.getEmail();
			String username = userDto.getUsername();
			String password = userDto.getPassword();
			ApplicationUserRole role = userDto.getRole();
			System.out.println("Paramters is in Service +" + email + " Username " + username + " password " + password
					+ " role " + role);
			ApplicationUser existingUserWithEmail = userRepository.findByEmail(email);
			ApplicationUser existingUserWithUsername = userRepository.findByUsername(username);
			if (existingUserWithEmail == null) {
				String encodedPassword = passwordEncoder.encode(password);
				ApplicationUser applicationUser = createApplicationUser(email, username, role, encodedPassword);
				System.out.println("application user Object being saved is :" + applicationUser.toString());
				userService.save(applicationUser);
				response = customResponseUtilityClass.buildSuccessResponseForOnBoardingService();
			}
			if (existingUserWithEmail != null) {
				System.out.println("existing User with E-mail Found====>" + existingUserWithEmail);
				response.setStatusCode(HttpStatusCode.NOT_ACCEPTABLE.getOrdinal());
				response.setserviceFlag(ResponseFlag.USER_WITH_SAME_EMAIL_ALREADY_EXISTS);
				response.setErrorMessage("Not Acceptable :: User with same e-mail already exists");
			} else if (existingUserWithUsername != null) {
				System.out.println("existing User with Username Found====>" + existingUserWithEmail);
				response.setStatusCode(HttpStatusCode.NOT_ACCEPTABLE.getOrdinal());
				response.setserviceFlag(ResponseFlag.USER_WITH_SAME_USERNAME_ALREADY_EXISTS);
				response.setErrorMessage("Not Acceptable :: User with same username already exists");
			}
			return response;
		} catch (Exception e) {
			System.out.println("Error in service :: registerUser method : " + e);
			response = customResponseUtilityClass.buildErrorResponseForOnBoardingService(e);
		}
		return response;
	}

	private ApplicationUser createApplicationUser(String email, String username, ApplicationUserRole role,
			String encodedPassword) {
		ApplicationUser applicationUser = new ApplicationUser();
		applicationUser.setUsername(username);
		applicationUser.setPassword(encodedPassword);
		applicationUser.setEmail(email);
		applicationUser.setUserList(new ArrayList<String>());
		applicationUser.setAccountNonExpired(true);
		applicationUser.setAccountNonLocked(true);
		applicationUser.setCredentialsNonExpired(true);
		applicationUser.setEnabled(true);
		applicationUser.setRole(role);
		return applicationUser;
	}

	public void validateUserRegisterationRequest(UserDTO userDTO) throws ValidationException {
		System.out.println("validateUserRegisterationRequest called !!");
		if (null == userDTO.getUsername() || userDTO.getUsername().isEmpty()) {
			throw new ValidationException(HttpStatusCode.VALIDATION_FAILED.getOrdinal(), "Username is mandatory!");
		} else if (null == userDTO.getEmail() || userDTO.getEmail().isEmpty()) {
			throw new ValidationException(HttpStatusCode.VALIDATION_FAILED.getOrdinal(), "Email is mandatory");
		}
	}
}
