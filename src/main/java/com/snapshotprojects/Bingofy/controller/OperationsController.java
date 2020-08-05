package com.snapshotprojects.Bingofy.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.snapshotprojects.Bingofy.entities.ApplicationUser;
import com.snapshotprojects.Bingofy.enums.HttpStatusCode;
import com.snapshotprojects.Bingofy.exceptions.ValidationException;
import com.snapshotprojects.Bingofy.request.AddRecipeRequest;
import com.snapshotprojects.Bingofy.request.SendFeedbackRequest;
import com.snapshotprojects.Bingofy.responses.APIResponse;
import com.snapshotprojects.Bingofy.responses.ErrorResponse;
import com.snapshotprojects.Bingofy.responses.ServiceResponse;
import com.snapshotprojects.Bingofy.services.OperationsService;
import com.snapshotprojects.Bingofy.utilityclasses.ListOfAccessingUsersEmail;
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/operations")
public class OperationsController {

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
	
	@PostMapping("/addUserToMyList")
	@PreAuthorize("hasAuthority('nonadmin:write')")
	@ResponseBody
	public APIResponse<?> addUserToMyList(@Valid @RequestBody ListOfAccessingUsersEmail listAccessignUserEmail,
			HttpServletResponse httpResponse, HttpServletRequest request) throws Exception {
		try {
			operationsService.validateAddUserToMyListRequest(listAccessignUserEmail, listAccessignUserEmail.getEmail());
			ServiceResponse serviceReponseForassignListToAnotherUsers = operationsService
					.assignAnotherUserToAccessList(listAccessignUserEmail);
			return APIResponse.response(serviceReponseForassignListToAnotherUsers.getStatusCode(),
					serviceReponseForassignListToAnotherUsers);

		} catch (ValidationException e) {
			httpResponse.setStatus(e.getHttpCode());
			return APIResponse.response(e.getHttpCode(), new ErrorResponse(e.getMessage()));
		}
	}
	
	@PostMapping("/sendFeedback")
	@PreAuthorize("hasAuthority('nonadmin:write')")
	@ResponseBody
	public APIResponse<?> sendFeedback(@Valid @RequestBody SendFeedbackRequest sendFeedbackRequest,
			HttpServletResponse httpResponse, HttpServletRequest request) throws Exception {
		try {
			ServiceResponse serviceReponseForassignListToAnotherUsers = operationsService
					.sendFeedback(sendFeedbackRequest);
			return APIResponse.response(serviceReponseForassignListToAnotherUsers.getStatusCode(),
					serviceReponseForassignListToAnotherUsers);
		} catch (Exception e) {
			httpResponse.setStatus(HttpStatus.SERVICE_UNAVAILABLE.ordinal());
			return APIResponse.response(HttpStatus.SERVICE_UNAVAILABLE.ordinal(), new ErrorResponse(e.getMessage()));
		}
	}

	@PostMapping("/addRecipe")
	@PreAuthorize("hasAuthority('nonadmin:write')")
	@ResponseBody
	public APIResponse<?> addRecipe(@Valid @RequestBody AddRecipeRequest addRecipeRequest,
			HttpServletResponse httpResponse, HttpServletRequest request) throws Exception {
		try {
			ServiceResponse serviceReponseForaddRecipe = operationsService
					.addRecipe(addRecipeRequest);
			return APIResponse.response(serviceReponseForaddRecipe.getStatusCode(),
					serviceReponseForaddRecipe);

		} catch (Exception e) {
			return APIResponse.response(HttpStatusCode.INTERNAL_SERVER_ERROR.getOrdinal(), new ErrorResponse(e.getMessage()));
		}
	}

	@PostMapping("/getRecipe")
	@PreAuthorize("hasAuthority('nonadmin:write')")
	@ResponseBody
	public APIResponse<?> getRecipe(@Valid @RequestBody ApplicationUser username,
			HttpServletResponse httpResponse, HttpServletRequest request) throws Exception {
		try {
			Map<String,Object> serviceReponseForgetRecipe = operationsService
					.getRecipe(username);
			return APIResponse.response(HttpStatusCode.SUCCESS.getOrdinal(),
					serviceReponseForgetRecipe);

		} catch (Exception e) {
			return APIResponse.response(HttpStatusCode.INTERNAL_SERVER_ERROR.getOrdinal(), new ErrorResponse(e.getMessage()));
		}
	}
	
	@PostMapping("/getUserGrantAccessList")
	@ResponseBody
	@PreAuthorize("hasAuthority('nonadmin:write')")
	public APIResponse<?> getUserGrantAccessList(@RequestBody ApplicationUser username,
			HttpServletResponse httpResponse, HttpServletRequest request) throws Exception {
		try {
			operationsService.validategetUserGrantAccessListRequest(username.getUsername());
			Map<String, String> setOfGrantAccessToUUIDS = operationsService
					.getApplicationUserGrantAccessToList(username.getUsername());
			if (!setOfGrantAccessToUUIDS.isEmpty()) {
				return APIResponse.response(HttpStatusCode.SUCCESS.getOrdinal(), setOfGrantAccessToUUIDS);
			} else {
				return APIResponse.response(HttpStatusCode.CONFLICT.getOrdinal(), setOfGrantAccessToUUIDS);
			}
		} catch (ValidationException e) {
			httpResponse.setStatus(e.getHttpCode());
			return APIResponse.response(e.getHttpCode(), new ErrorResponse(e.getMessage()));
		}
	}
}
