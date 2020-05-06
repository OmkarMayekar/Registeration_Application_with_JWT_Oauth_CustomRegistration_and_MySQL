package com.snapshotprojects.Bingofy.utilityclasses;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.snapshotprojects.Bingofy.enums.HttpStatusCode;
import com.snapshotprojects.Bingofy.enums.ResponseFlag;
import com.snapshotprojects.Bingofy.repositories.UserRepository;
import com.snapshotprojects.Bingofy.responses.ServiceResponse;

@Component
public class CustomResponseUtilityClass {
	@Autowired
	public UserRepository userRepository;
	@Autowired
	public ServiceResponse response;

	public ServiceResponse buildUserNotPresentResponse() {
		response.setStatusCode(HttpStatusCode.UNAUTHORIZED.getOrdinal());
		response.setserviceFlag(ResponseFlag.USER_NOT_PRESENT);
		response.setErrorMessage("User not found in database");
		return response;
	}
	
	public ServiceResponse buildJsonObjectSavedResponse() {
		response.setStatusCode(HttpStatusCode.SUCCESS.getOrdinal());
		response.setserviceFlag(ResponseFlag.DATABASE_UPDATED);
		response.setErrorMessage("");
		return response;
	}
	
	public ServiceResponse buildJsonObjectRetrivedResponse() {
		response.setStatusCode(HttpStatusCode.SUCCESS.getOrdinal());
		response.setserviceFlag(ResponseFlag.JSON_RETRIVED);
		response.setErrorMessage("");
		return response;
	}

	public ServiceResponse buildSuccessReponseForItemsUpdation() {
		response.setStatusCode(HttpStatusCode.SUCCESS.getOrdinal());
		response.setserviceFlag(ResponseFlag.ITEMS_UPDATED);
		response.setErrorMessage("Items updated successfully");
		return response;
	}

	public ServiceResponse buildErrorResponseForItemsUpdation() {
		response.setStatusCode(HttpStatusCode.INTERNAL_SERVER_ERROR.getOrdinal());
		response.setserviceFlag(ResponseFlag.ITEMS_WERE_NOT_UPDATED);
		response.setErrorMessage("Items were not updated successfully");
		return response;
	}
	
	public ServiceResponse buildSuccessReponseForGetItemsForUsers() {
		response.setStatusCode(HttpStatusCode.SUCCESS.getOrdinal());
		response.setserviceFlag(ResponseFlag.ITEMS_RETRIVED_SUCCESSFULLY);
		response.setErrorMessage("");
		return response;
	}
	
	public ServiceResponse buildSuccessResponseForOnBoardingService() {
		response.setStatusCode(HttpStatusCode.SUCCESS.getOrdinal());
		response.setserviceFlag(ResponseFlag.REGISTRATION_SUCCESSFULL);
		response.setErrorMessage("");
		return response;
	}

	public ServiceResponse buildErrorResponseForOnBoardingService(Exception e) {
		response.setStatusCode(HttpStatusCode.INTERNAL_SERVER_ERROR.getOrdinal());
		response.setserviceFlag(ResponseFlag.REGISTRATION_UNSUCCESSFULL);
		if (e.getClass().toString().trim() == "class javax.validation.ConstraintViolationException") {
			response.setErrorMessage("Internal Server Error");
		} else {
			response.setErrorMessage("Input Validation Error");
		}
		return response;
	}

	public ServiceResponse buildSuccessResponseForOperationsService() {
		response.setStatusCode(HttpStatusCode.CREATED.getOrdinal());
		response.setserviceFlag(ResponseFlag.USERS_GIVEN_ACCESS);
		response.setErrorMessage("Users are given access to ownser's list sucessfully");
		return response;
	}

	public ServiceResponse buildErrorResponseForOperationsService() {
		response.setStatusCode(HttpStatusCode.INTERNAL_SERVER_ERROR.getOrdinal());
		response.setserviceFlag(ResponseFlag.USERS_NOT_GIVEN_ACCESS);
		response.setErrorMessage("Users are not given access to ownser's list");
		return response;
	}

}