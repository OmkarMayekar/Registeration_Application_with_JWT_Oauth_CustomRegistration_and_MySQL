package com.snapshotprojects.Bingofy.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Properties;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.snapshotprojects.Bingofy.entities.ApplicationUser;
import com.snapshotprojects.Bingofy.entities.Recipe;
import com.snapshotprojects.Bingofy.enums.ApplicationUserRole;
import com.snapshotprojects.Bingofy.enums.HttpStatusCode;
import com.snapshotprojects.Bingofy.exceptions.ValidationException;
import com.snapshotprojects.Bingofy.repositories.RecipeRepository;
import com.snapshotprojects.Bingofy.repositories.UserRepository;
import com.snapshotprojects.Bingofy.request.AddRecipeRequest;
import com.snapshotprojects.Bingofy.request.SendFeedbackRequest;
import com.snapshotprojects.Bingofy.responses.GetRecipeResponse;
import com.snapshotprojects.Bingofy.responses.ServiceResponse;
import com.snapshotprojects.Bingofy.utilityclasses.CustomResponseUtilityClass;
import com.snapshotprojects.Bingofy.utilityclasses.ListOfAccessingUsersEmail;
import java.io.UnsupportedEncodingException;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

@Service
public class OperationsService {

	@Autowired
	UserService userService;

	@Autowired
	UserRepository userRepository;

	@Autowired
	RecipeRepository recipeRepository;

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
			if (!accessingUsersEmailList.isEmpty()) {
				ListIterator<String> itr = accessingUsersEmailList.listIterator();
				while (itr.hasNext()) {
					String email = (String) itr.next();
					// GETTING THE ACCESSING USER OBJECT FROM DB FOR UUID RETRIVAL.
					ApplicationUser accessingUser = userRepository.findByEmail(email);
					// ADDING THE RETRIVED UUID FROM USERS INTO ARRAYLIST.
					accessingUsersUUIDList.add(accessingUser.getUuid());
				}
			}
			System.out.println("ACCESSING_USERS_WHO_ARE_NOW_GIVEN_ACCESS_TO_OWNER'S_LIST_ARE::UUID_LIST =====> "
					+ accessingUsersUUIDList);
			ownerUserGrantAccessToSet = ownerUser.getGrantAcessTo();
			ownerUserGrantAccessToSetBeforeUpdatingValues = ownerUserGrantAccessToSet;
			System.out.println("ownerUserGrantAccessToSetBeforeUpdatingValues===>"
					+ ownerUserGrantAccessToSetBeforeUpdatingValues);
			ownerUserGrantAccessToSet.addAll(accessingUsersUUIDList);
			ownerUser.setGrantAcessTo(ownerUserGrantAccessToSet);
			// Update the GrantAccess Set of Owner
			ApplicationUser updatedApplicationUser = userRepository.save(ownerUser);
			HashSet<String> ownerUserGrantAccessToSetAfterUpdatingValues = updatedApplicationUser.getGrantAcessTo();
			System.out.println(
					"ownerUserGrantAccessToSetAfterUpdatingValues===>" + ownerUserGrantAccessToSetAfterUpdatingValues);
			if (ownerUserGrantAccessToSetAfterUpdatingValues.toArray()
					.equals(ownerUserGrantAccessToSetBeforeUpdatingValues.toArray())) {
				System.out.println("OWNER USER LIST SHARING WAS UPDATED AND HE NEEDS TO BE NOTIFIED");
			}
			response = customResponseUtilityClass.buildSuccessResponseForOperationsService();
		} catch (Exception e) {
			System.out.println("Error in service :: assignAnotherUserAccessList method : " + e);
			response = customResponseUtilityClass.buildErrorResponseForOperationsService();
		}
		return response;
	}

	public Map<String, String> getApplicationUserGrantAccessToList(String username) {
		HashSet<String> grantAccessToList = new HashSet<>();
		ApplicationUser user = new ApplicationUser();
		List<ApplicationUser> listOfUsers = new ArrayList<>();
		Map<String, String> listOfUserNames = new HashMap<String, String>();
		try {
			user = userRepository.findByUsername(username);
			grantAccessToList = user.getGrantAcessTo();
			Iterator<String> itr = grantAccessToList.iterator();
			while (itr.hasNext()) {
				listOfUsers.add(userRepository.findUserByUUID(itr.next()));
			}
			ListIterator<ApplicationUser> LItr = listOfUsers.listIterator();
			while (LItr.hasNext()) {
				ApplicationUser userObject = LItr.next();
				listOfUserNames.put(userObject.getUsername(), userObject.getEmail());
			}
		} catch (Exception e) {
			System.out.println("Excetion occured in getApplicationUserGrantAccessToList method " + e);
		}
		return listOfUserNames;
	}

	public ServiceResponse addRecipe(@Valid AddRecipeRequest addRecipeRequest) {
		ApplicationUser user = new ApplicationUser();
		Recipe recipe = new Recipe();
		ServiceResponse response = new ServiceResponse();
		String username = null;
		String desc = null;
		String recipeTitle = null;
		ArrayList<String> sharingUserEmail = null;
		String recipeType = null;
		Long userid = null;
		try {
			username = addRecipeRequest.getUsername();
			user = userRepository.findByUsername(username);
			desc = addRecipeRequest.getRecipedesc();
			recipeTitle = addRecipeRequest.getRecipetitle();
			sharingUserEmail = addRecipeRequest.getSharinguseremail();
			recipeType = addRecipeRequest.getRecipetype();
			userid = user.getId();
			recipe.setRecipeDescription(desc);
			recipe.setRecipeTitle(recipeTitle);
			recipe.setSharingUseremail(sharingUserEmail);
			recipe.setRecipeType(recipeType);
			recipe.setUser(user);
			recipeRepository.save(recipe);
			response = customResponseUtilityClass.buildSuccessResponseForAddRecipesService();
		} catch (Exception e) {
			System.out.println("Exception occured in addRecipe Method " + e);
			response = customResponseUtilityClass.buildErrorResponseForAddRecipeService();
		}
		return response;
	}

	@SuppressWarnings("unused")
	public Map<String, Object> getRecipe(ApplicationUser userRequest) {
		ApplicationUser user = new ApplicationUser();
		List<Recipe> recipeList = new ArrayList<>();
		List<GetRecipeResponse> recipeResponse = new ArrayList<GetRecipeResponse>();
		List<ArrayList<GetRecipeResponse>> finalList = new ArrayList<>();
		Map<Long, Object> finalMap = new HashMap<Long, Object>();
		Map<String, Object> responseMap = new HashMap<String, Object>();
		ServiceResponse response = new ServiceResponse();
		Recipe recipe = new Recipe();
		String username = null;
		String desc = null;
		String recipeTitle = null;
		Long userid = null;
		try {
			username = userRequest.getUsername();
			user = userRepository.findByUsername(username);
			userid = user.getId();
			recipeList = recipeRepository.findRecipeByUserId(userid);
			ListIterator<Recipe> ltr = recipeList.listIterator();
			List<GetRecipeResponse> recipeResponseList = new ArrayList<>();
			while (ltr.hasNext()) {
				GetRecipeResponse getRecipeResponse = new GetRecipeResponse();
				Recipe r = ltr.next();
				getRecipeResponse.setId(r.getId());
				getRecipeResponse.setRecipeTitle(r.getRecipeTitle());
				getRecipeResponse.setRecipeDescription(r.getRecipeDescription());
				getRecipeResponse.setRecipeType(r.getRecipeType());
				getRecipeResponse.setSharingUseremail(r.getSharingUseremail());
				getRecipeResponse.setAdminUseremail(r.getUser().getEmail());
				getRecipeResponse.setAdminUsername(r.getUser().getUsername());
				recipeResponseList.add(getRecipeResponse);
			}
			response = customResponseUtilityClass.buildSuccessResponseForGetRecipesService();
			responseMap.put("response", response);
			responseMap.put("result", recipeResponseList);
		} catch (Exception e) {
			response = customResponseUtilityClass.buildErrorResponseForGetRecipeService();
			responseMap.put("response", response);
			responseMap.put("result", recipeList);
			System.out.println("Exception occured in getRecipe Method " + e);

		}
		return responseMap;
	}

	public List<String> retrieveAllNonAdminUsersInfo() {
		List<ApplicationUser> listOfUsers = userRepository.findAll();
		List<String> userInfo = new ArrayList<String>();
		ListIterator ltr = listOfUsers.listIterator();
		while (ltr.hasNext()) {
			ApplicationUser user = (ApplicationUser) ltr.next();
			if (user.getRole().name() == ApplicationUserRole.NONADMIN.name()) {
				userInfo.add(user.getEmail());
			}
		}
		return userInfo;
	}

	public ServiceResponse sendFeedback(@Valid SendFeedbackRequest sendFeedbackRequest) {
		String feedbackContent = null;
		String username = null;
		ApplicationUser user = null;
		try {
			username = sendFeedbackRequest.getUsername();
			feedbackContent = sendFeedbackRequest.getFeedback();
			user = userRepository.findByUsername(username);
			if (user != null) {
				sendEmail(username, feedbackContent);
			}
			response = customResponseUtilityClass.buildSuccessResponseForSendFeedbackService();
		} catch (Exception e) {
			System.out.println("Exception occured in sendFeedback Method " + e);
			response = customResponseUtilityClass.buildErrorResponseForSendFeedbackService();
		}
		return response;
	}

	public void sendEmail(String username, String feedbackContent) {
		final String gmailUsername = "mayekarom27@gmail.com";
		final String gmailPassword = "Omkar750601895666527";

		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.auth", "true");
		props.put("mail.debug", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.port", "465");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.socketFactory.fallback", "false");

		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(gmailUsername, gmailPassword);
			}
		});

		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("mayekarom27@gmail.com"));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("mayekarom27@gmail.com"));
			message.setSubject("================ Bingofy User Feedback ============= Username :- " + username);
			message.setText("The Content of the feedback is :- "+feedbackContent);

			Transport.send(message);

			System.out.println("Done");

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}

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

	public void validategetUserGrantAccessListRequest(String username) throws ValidationException {
		System.out.println("validategetUserGrantAccessListRequest called !!");
		if (null == username || username.isEmpty()) {
			throw new ValidationException(HttpStatusCode.VALIDATION_FAILED.getOrdinal(), "username is mandatory!");
		}
	}

}
