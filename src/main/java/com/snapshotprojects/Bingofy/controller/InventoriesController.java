package com.snapshotprojects.Bingofy.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.snapshotprojects.Bingofy.entities.ApplicationUser;
import com.snapshotprojects.Bingofy.enums.HttpStatusCode;
import com.snapshotprojects.Bingofy.enums.ResponseFlag;
import com.snapshotprojects.Bingofy.exceptions.ValidationException;
import com.snapshotprojects.Bingofy.request.ItemsUpdationRequest;
import com.snapshotprojects.Bingofy.request.JsonObjectRequest;
import com.snapshotprojects.Bingofy.responses.APIResponse;
import com.snapshotprojects.Bingofy.responses.ServiceResponse;
import com.snapshotprojects.Bingofy.services.InventoryService;

@RestController
@RequestMapping("/inventories")
public class InventoriesController {

	@Autowired
	InventoryService inventoryService;

	@Autowired
	ServiceResponse addItemServiceResponse;

	@ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public APIResponse<?> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
		Map<HttpStatusCode, Object> errors = new HashMap<>();
		ex.getBindingResult().getFieldErrors()
				.forEach(error -> errors.put(HttpStatusCode.VALIDATION_FAILED, error.getDefaultMessage()));
		return APIResponse.response(HttpStatusCode.VALIDATION_FAILED.getOrdinal(), errors);
	}

	@GetMapping("/getAllInventoryAttributes")
	@ResponseBody
	@PreAuthorize("hasAuthority('nonadmin:write')")
	public APIResponse<?> getInventoryAttributes(HttpServletResponse httpResponse, HttpServletRequest request)
			throws ValidationException {
		List<Map<String, Object>> serviceReponseForRegisterUser = inventoryService.retrieveAllAttributes();
		return APIResponse.response(HttpStatusCode.SUCCESS.getOrdinal(), serviceReponseForRegisterUser);
	}
	
	@PostMapping("/getAllItems")
	@ResponseBody
	@PreAuthorize("hasAuthority('nonadmin:read')")
	public APIResponse<?> getAllItems(@RequestBody ApplicationUser username,HttpServletResponse httpResponse, HttpServletRequest request)
			throws ValidationException {
		Map<String, Object> serviceReponseForRegisterUser = inventoryService.retrieveAllItems(username);
		return APIResponse.response(HttpStatusCode.SUCCESS.getOrdinal(), serviceReponseForRegisterUser);
	}
	
	@PostMapping("/getAllCommonItems")
	@ResponseBody
	@PreAuthorize("hasAuthority('nonadmin:read')")
	public APIResponse<?> retrieveAllCommonItems(@RequestBody ApplicationUser username,HttpServletResponse httpResponse, HttpServletRequest request)
			throws ValidationException {
		Map<String, Object> serviceReponseForRegisterUser = inventoryService.retrieveAllCommonItems(username);
		return APIResponse.response(HttpStatusCode.SUCCESS.getOrdinal(), serviceReponseForRegisterUser);
	}

	@PostMapping("/assignExtraAttributesForItems")
	@ResponseBody
	@PreAuthorize("hasAuthority('nonadmin:write')")
	public APIResponse<?> assignExtraAttributesForItems(@RequestBody JsonObjectRequest json, HttpServletResponse httpResponse,
			HttpServletRequest request) throws ValidationException {
		ServiceResponse saveJSONObjectServiceResponse = inventoryService.saveJsonObject(json);
		return APIResponse.response(HttpStatusCode.SUCCESS.getOrdinal(), saveJSONObjectServiceResponse);
	}

	@PostMapping("/addNewItemsToUserList")
	@ResponseBody
	@PreAuthorize("hasAuthority('nonadmin:read')")
	public APIResponse<?> addNewItemsToUserList(@Valid @RequestBody ItemsUpdationRequest itemsUpdationRequest,
			HttpServletResponse httpResponse, HttpServletRequest request) {
		try {
			addItemServiceResponse = inventoryService.addItemsToUserList(itemsUpdationRequest);
			if (addItemServiceResponse.getserviceFlag() == ResponseFlag.USER_NOT_PRESENT) {
				return APIResponse.response(HttpStatusCode.UNAUTHORIZED.getOrdinal(), addItemServiceResponse);
			}
			return APIResponse.response(HttpStatusCode.SUCCESS.getOrdinal(), addItemServiceResponse);
		} catch (Exception e) {
			return APIResponse.response(HttpStatusCode.INTERNAL_SERVER_ERROR.getOrdinal(), "Technical Error");
		}
	}
	
	@PostMapping("/removeItemsFromUserList")
	@ResponseBody
	@PreAuthorize("hasAuthority('nonadmin:write')")
	public APIResponse<?> removeItemsFromUserList(@Valid @RequestBody ItemsUpdationRequest itemsUpdationRequest,
			HttpServletResponse httpResponse, HttpServletRequest request) {
		try {
			addItemServiceResponse = inventoryService.removeItemsFromUserList(itemsUpdationRequest);
			if (addItemServiceResponse.getserviceFlag() == ResponseFlag.USER_NOT_PRESENT) {
				return APIResponse.response(HttpStatusCode.UNAUTHORIZED.getOrdinal(), addItemServiceResponse);
			}
			return APIResponse.response(HttpStatusCode.SUCCESS.getOrdinal(), addItemServiceResponse);
		} catch (Exception e) {
			return APIResponse.response(HttpStatusCode.INTERNAL_SERVER_ERROR.getOrdinal(), "Technical Error");
		}
	}
	
	@PostMapping("/getAllExtraInventoryAttributes")
	@ResponseBody
	@PreAuthorize("hasAuthority('nonadmin:write')")
	public APIResponse<?> getAllExtraInventoryAttributes(@RequestBody ApplicationUser username,HttpServletResponse httpResponse, HttpServletRequest request)
			throws ValidationException {
		String userName = username.getUsername();
		System.out.println("username in controller ===>"+userName);
		Map<String, Object> serviceReponseForExtraInventoryAtttributes = inventoryService.getAllExtraInventoryAttributes(userName);
		return APIResponse.response(HttpStatusCode.SUCCESS.getOrdinal(), serviceReponseForExtraInventoryAtttributes);
	}
	
	@PostMapping("/saveAttributtesForItems")
	@ResponseBody
	@PreAuthorize("hasAuthority('nonadmin:write')")
	public APIResponse<?> getInventoryItemsForUser(@RequestBody ApplicationUser email, HttpServletResponse httpResponse,
			HttpServletRequest request) throws ValidationException {
		String userEmail = email.getEmail();
		Map<String, Object> serviceReponseForGetInventoryItems = inventoryService.retrieveAllItemsForUser(userEmail);
		return APIResponse.response(HttpStatusCode.SUCCESS.getOrdinal(), serviceReponseForGetInventoryItems);
	}
	
}
