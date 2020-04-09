package com.snapshotprojects.Bingofy.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.snapshotprojects.Bingofy.entities.ApplicationUser;
import com.snapshotprojects.Bingofy.enums.ApplicationUserRole;
import com.snapshotprojects.Bingofy.enums.HttpStatusCode;
import com.snapshotprojects.Bingofy.enums.ResponseFlag;
import com.snapshotprojects.Bingofy.exceptions.ValidationException;
import com.snapshotprojects.Bingofy.repositories.UserRepository;
import com.snapshotprojects.Bingofy.responses.ServiceResponse;

@Service
public class OnBoardingService {
	@Autowired
	UserService userService;

	@Autowired
	UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	ServiceResponse response;

	public ServiceResponse registerUser(String email, String username, String password, ApplicationUserRole role)
			throws ValidationException {
		try {
			System.out.println(
					"Paramters is in Service +" + email + " Username " + " password " + password + " role " + role);
			ApplicationUser existingUser = userRepository.findByEmail(email);
			if (existingUser == null) {
				String encodedPassword = passwordEncoder.encode(password);
				ApplicationUser applicationUser = createApplicationUser(email, username, role, encodedPassword);
				System.out.println("application user Object being saved is :" + applicationUser.toString());
				userService.save(applicationUser);
				response = buildSuccessServiceResponse();
			} else {
				System.out.println("existing User Found====>" + existingUser);
				response.setStatusCode(HttpStatusCode.VALIDATION_FAILED.getOrdinal());
				response.setserviceFlag(ResponseFlag.USER_ALREADY_EXISTS);
				response.setErrorMessage("User already exists");
				return response;
			}
		} catch (Exception e) {
			System.out.println("Error in service :: registerUser method : " + e);
			response = buildErrorServiceResponse(e);
		}
		return response;
	}

	private ApplicationUser createApplicationUser(String email, String username, ApplicationUserRole role,
			String encodedPassword) {
		ApplicationUser applicationUser = new ApplicationUser();
		applicationUser.setUsername(username);
		applicationUser.setPassword(encodedPassword);
		applicationUser.setEmail(email);
		applicationUser.setAccountNonExpired(true);
		applicationUser.setAccountNonLocked(true);
		applicationUser.setCredentialsNonExpired(true);
		applicationUser.setEnabled(true);
		applicationUser.setRole(role);
		/*
		 * if (role == true) { applicationUser.setGrantedAuthorities(
		 * ApplicationUserRole.ADMIN.getGrantedAuthorities()); } else {
		 * applicationUser.setGrantedAuthorities(
		 * ApplicationUserRole.NONADMIN.getGrantedAuthorities()); }
		 */
		return applicationUser;
	}

	public ServiceResponse buildSuccessServiceResponse() {
		response.setStatusCode(HttpStatusCode.SUCCESS.getOrdinal());
		response.setserviceFlag(ResponseFlag.REGISTRATION_SUCCESSFULL);
		response.setErrorMessage("User registered sucessfully");
		return response;
	}

	public ServiceResponse buildErrorServiceResponse(Exception e) {
		response.setStatusCode(HttpStatusCode.INTERNAL_SERVER_ERROR.getOrdinal());
		response.setserviceFlag(ResponseFlag.REGISTRATION_UNSUCCESSFULL);
		if (e.getClass().toString().trim() == "class javax.validation.ConstraintViolationException") {
			response.setErrorMessage("Internal Server Error");
		} else {
			response.setErrorMessage("Input Validation Error");
		}
		return response;
	}
}
