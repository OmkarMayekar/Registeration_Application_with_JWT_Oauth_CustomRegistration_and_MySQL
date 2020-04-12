package com.snapshotprojects.Bingofy.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.snapshotprojects.Bingofy.entities.ApplicationUser;
import com.snapshotprojects.Bingofy.enums.HttpStatusCode;
import com.snapshotprojects.Bingofy.exceptions.ValidationException;
import com.snapshotprojects.Bingofy.responses.APIResponse;
import com.snapshotprojects.Bingofy.responses.ErrorResponse;
import com.snapshotprojects.Bingofy.responses.ServiceResponse;
import com.snapshotprojects.Bingofy.services.OperationsService;
import com.snapshotprojects.Bingofy.utilityclasses.ListOfAccessingUsersEmail;

@RestController
@RequestMapping("/operations")
public class OperationsController {

	@Autowired
	OperationsService operationsService;

	@PostMapping("/addUserToMyList")
	@ResponseBody
	public APIResponse<?> addUserToMyList(@Valid @RequestBody ListOfAccessingUsersEmail listAccessignUserEmail,
			HttpServletResponse httpResponse, HttpServletRequest request) throws Exception{
		System.out.println("addUserToMyList method called!!" + "listAccessignUserEmail" + listAccessignUserEmail
				+ " ownerUserEmail " + listAccessignUserEmail.getEmail());
		try {
			operationsService.validateAddUserToMyListRequest(listAccessignUserEmail, listAccessignUserEmail.getEmail());

			ServiceResponse serviceReponseForassignListToAnotherUsers = operationsService
					.assignAnotherUserAccessList(listAccessignUserEmail);

			return APIResponse.response(serviceReponseForassignListToAnotherUsers.getStatusCode(),
					serviceReponseForassignListToAnotherUsers);

		} catch (ValidationException e) {
			httpResponse.setStatus(e.getHttpCode());
			return APIResponse.response(e.getHttpCode(), new ErrorResponse(e.getMessage()));
		}
	}
	
	@PostMapping("/getUserGrantAccessList")
	@ResponseBody
	public APIResponse<?> getUserGrantAccessList(@RequestBody ApplicationUser username,
			HttpServletResponse httpResponse, HttpServletRequest request) throws Exception{
		System.out.println("getUserGrantAccessList method called!!" + " username " + username.getUsername());
		try {
			/* Validate the request */
			operationsService.validategetUserGrantAccessListRequest(username.getUsername());

			/* Call the service */
			Map<String,String> setOfGrantAccessToUUIDS  = operationsService.getApplicationUserGrantAccessToList(username.getUsername());
			if(!setOfGrantAccessToUUIDS.isEmpty()) {
				System.out.println("setOfGrantAccessToUUIDS is not Empty");
				return APIResponse.response(HttpStatusCode.SUCCESS.getOrdinal(),setOfGrantAccessToUUIDS);
			}else {
				System.out.println("setOfGrantAccessToUUIDS is Empty");
				return APIResponse.response(HttpStatusCode.CONFLICT.getOrdinal(),setOfGrantAccessToUUIDS);
			}
		} catch (ValidationException e) {
			httpResponse.setStatus(e.getHttpCode());
			return APIResponse.response(e.getHttpCode(), new ErrorResponse(e.getMessage()));
		}
	}
}