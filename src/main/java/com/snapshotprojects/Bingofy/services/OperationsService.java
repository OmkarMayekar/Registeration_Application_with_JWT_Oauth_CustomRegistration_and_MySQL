package com.snapshotprojects.Bingofy.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.snapshotprojects.Bingofy.entities.ApplicationUser;
import com.snapshotprojects.Bingofy.enums.HttpStatusCode;
import com.snapshotprojects.Bingofy.exceptions.ValidationException;
import com.snapshotprojects.Bingofy.repositories.UserRepository;
import com.snapshotprojects.Bingofy.responses.ServiceResponse;
import com.snapshotprojects.Bingofy.utilityclasses.CustomResponseUtilityClass;
import com.snapshotprojects.Bingofy.utilityclasses.ListOfAccessingUsersEmail;

@Service
public class OperationsService {

	@Autowired
	UserService userService;

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	CustomResponseUtilityClass customResponseUtilityClass;
	
	@Autowired
	ServiceResponse response;

	public ServiceResponse assignAnotherUserToAccessList(ListOfAccessingUsersEmail listAccessignUserEmail) {
		try {
			String ownerUserEmail = listAccessignUserEmail.getEmail();
			ArrayList<String> accessingUsersEmailList = listAccessignUserEmail.getListAccessignUserEmail();
			ApplicationUser ownerUser = null;
			ArrayList<String> accessingUsersUUIDList = new ArrayList<>();
			HashSet<String> ownerUserGrantAccessToSet = new HashSet<>();
			HashSet<String> ownerUserGrantAccessToSetBeforeUpdatingValues = new HashSet<>();
			System.out.println("ownerUserEmail ===>" + ownerUserEmail);
			System.out.println("acessingUserEmailList===>" + accessingUsersEmailList);
			if (ownerUserEmail != null) {
				// GETTING THE OWNER USER OBJECT FROM REQUEST AND SEARCHING IN DB.
				ownerUser = userRepository.findByEmail(ownerUserEmail);
			}
			if(!accessingUsersEmailList.isEmpty()) 
			{
				ListIterator<String> itr = accessingUsersEmailList.listIterator();
				while (itr.hasNext()) {
					String email = (String) itr.next();
					// GETTING THE ACCESSING USER OBJECT FROM DB FOR UUID RETRIVAL.
					ApplicationUser accessingUser = userRepository.findByEmail(email);
					// ADDING THE RETRIVED UUID FROM USERS INTO ARRAYLIST.
					accessingUsersUUIDList.add(accessingUser.getUuid());
				}
			}
			System.out.println("ACCESSING_USERS_WHO_ARE_NOW_GIVEN_ACCESS_TO_OWNER'S_LIST_ARE::UUID_LIST =====> " + accessingUsersUUIDList);
			ownerUserGrantAccessToSet = ownerUser.getGrantAcessTo();
			ownerUserGrantAccessToSetBeforeUpdatingValues = ownerUserGrantAccessToSet;
			System.out.println("ownerUserGrantAccessToSetBeforeUpdatingValues===>"+ownerUserGrantAccessToSetBeforeUpdatingValues);
			ownerUserGrantAccessToSet.addAll(accessingUsersUUIDList);
			ownerUser.setGrantAcessTo(ownerUserGrantAccessToSet);
			//Update the GrantAccess Set of Owner
			ApplicationUser updatedApplicationUser = userRepository.save(ownerUser);
			HashSet<String> ownerUserGrantAccessToSetAfterUpdatingValues = updatedApplicationUser.getGrantAcessTo();
			System.out.println("ownerUserGrantAccessToSetAfterUpdatingValues===>"+ownerUserGrantAccessToSetAfterUpdatingValues);
			if(ownerUserGrantAccessToSetAfterUpdatingValues.toArray().equals(ownerUserGrantAccessToSetBeforeUpdatingValues.toArray())) 
			{
				System.out.println("OWNER USER LIST SHARING WAS UPDATED AND HE NEEDS TO BE NOTIFIED");
			}
			response = customResponseUtilityClass.buildSuccessResponseForOperationsService();
		} catch (Exception e) {
			System.out.println("Error in service :: assignAnotherUserAccessList method : " + e);
			response = customResponseUtilityClass.buildErrorResponseForOperationsService();
		}
		return response;
	}
	
	public  Map<String,String> getApplicationUserGrantAccessToList(String username)
	{
		HashSet<String> grantAccessToList = new HashSet<>();
		ApplicationUser user = new ApplicationUser();
		List<ApplicationUser> listOfUsers = new ArrayList<>();
		Map<String,String> listOfUserNames = new HashMap<String, String>();
		try {
			user = userRepository.findByUsername(username);
			grantAccessToList = user.getGrantAcessTo();
			Iterator<String> itr = grantAccessToList.iterator();
			while(itr.hasNext()) 
			{
				listOfUsers.add(userRepository.findUserByUUID(itr.next()));
			}
			ListIterator<ApplicationUser> LItr = listOfUsers.listIterator();
			while(LItr.hasNext())
			{	
				ApplicationUser userObject = LItr.next();
				listOfUserNames.put(userObject.getUsername(),userObject.getEmail());
			}
		}catch(Exception e) {
			System.out.println("Excetion occured in getApplicationUserGrantAccessToList method "+e);
		}
		return listOfUserNames;
	}
	
	public void validateAddUserToMyListRequest(ListOfAccessingUsersEmail listAccessignUserEmail, String ownerUserEmail)
			throws ValidationException {
		System.out.println("validateAddUserToMyListRequest called !!");
		if (null == listAccessignUserEmail || listAccessignUserEmail.getListAccessignUserEmail().isEmpty()) {
			throw new ValidationException(HttpStatusCode.VALIDATION_FAILED.getOrdinal(),
					"listAccessignUserEmail is mandatory!");
		} else if (null == ownerUserEmail || ownerUserEmail.isEmpty()) {
			throw new ValidationException(HttpStatusCode.VALIDATION_FAILED.getOrdinal(),
					"ownerUserEmail is mandatory!");
		}
	}
	
	public void validategetUserGrantAccessListRequest(String username)
			throws ValidationException {
		System.out.println("validategetUserGrantAccessListRequest called !!");
		if (null == username || username.isEmpty()) {
			throw new ValidationException(HttpStatusCode.VALIDATION_FAILED.getOrdinal(),
					"username is mandatory!");
		}
	}		
}
