package com.snapshotprojects.Bingofy.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.snapshotprojects.Bingofy.entities.ApplicationUser;
import com.snapshotprojects.Bingofy.repositories.UserRepository;
import com.snapshotprojects.Bingofy.request.ItemsUpdationRequest;
import com.snapshotprojects.Bingofy.responses.ServiceResponse;
import com.snapshotprojects.Bingofy.utilityclasses.CustomResponseUtilityClass;

@Service
public class InventoryService {
	@Autowired
	public UserRepository userRepository;

	@Autowired
	public ServiceResponse response;

	@Autowired
	CustomResponseUtilityClass customResponseUtilityClass;

	public List<Map<String, Object>> retrieveAllAttributes() {
		ArrayList<Integer> kgValues = new ArrayList<>();
		ArrayList<String> colourValues = new ArrayList<>();

		for (int i = 0; i < 10; i++) {
			kgValues.add(i);
		}
		colourValues.add("red");
		colourValues.add("orange");
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("KG", kgValues);
		map.put("COLOUR", colourValues);
		list.add(map);
		return list;
	}

	public ServiceResponse addItemsToUserList(ItemsUpdationRequest itemsUpdationRequest) {
		try {
			ApplicationUser user = null;
			List<String> listToBeUpdated = new ArrayList<String>();
			ArrayList<String> listOfItemsToBeAdded = itemsUpdationRequest.getListOfItemsToBeAdded();
			String userEmail = itemsUpdationRequest.getEmail();
			System.out.println("listOfItemsToBeAdded list of items is : " + listOfItemsToBeAdded);
			System.out.println("listOfItemsToBeAdded username is : " + userEmail);
			user = userRepository.findByEmail(userEmail);
			if (user == null) {
				response = customResponseUtilityClass.buildUserNotPresentResponse();
			}
			System.out.println("user in method :: addItemsToUserList is : " + user);
			listToBeUpdated = user.getUserList();
			System.out.println("listToBeUpdated is : " + listToBeUpdated);
			listToBeUpdated.addAll(listOfItemsToBeAdded);
			System.out.println("List after updation : " + listToBeUpdated);
			user.setUserList(listToBeUpdated);
			System.out.println("listToBeUpdated after adding new Items is : " + listToBeUpdated);
			System.out.println("New user object created is : " + user);
			userRepository.save(user);
			response = customResponseUtilityClass.buildSuccessReponseForItemsUpdation();
			return response;
		} catch (NullPointerException e) {
			System.out.println("NullPointer Exception in InventoryService :: addItemsToUserList method : " + e);
			response = customResponseUtilityClass.buildUserNotPresentResponse();
		} catch (Exception e) {
			System.out.println("Exception in InventoryService :: addItemsToUserList method : " + e);
			response = customResponseUtilityClass.buildErrorResponseForItemsUpdation();
		}
		return response;
	}

	public Map<String,Object> retrieveAllItemsForUser(String email) {
		ApplicationUser userObject = new ApplicationUser();
		List<String> userList = new ArrayList<>();
		Map<String,Object> responseMap = new HashMap<>();
		userObject = userRepository.findByEmail(email);
		if (userObject == null) {
			response = customResponseUtilityClass.buildUserNotPresentResponse();
			responseMap.put("response", response);
			responseMap.put("userlist", userList);
		}else 
		{
		System.out.println("userObject from retrieveAllItemsForUser method is : " + userObject);
		userList = userObject.getUserList();
		System.out.println("userList retrived for user with email " + userObject.getEmail() + " is " + userList);
		response = customResponseUtilityClass.buildSuccessReponseForGetItemsForUsers();
		responseMap.put("response", response);
		responseMap.put("userlist", userList);
		}
		return responseMap;
	}
}