package com.snapshotprojects.Bingofy.responses;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.snapshotprojects.Bingofy.enums.HttpStatusCode;
import com.snapshotprojects.Bingofy.enums.ResponseFlag;

@Component
public class RestAuthenticationEntryPoint implements AuthenticationEntryPoint {
	
	@Autowired
	ServiceResponse serviceresponse;
	
	@Override
	    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
		ServiceResponse serviceResponse = buildServiceResponse();
		APIResponse<?> r = APIResponse.response(serviceResponse.getStatusCode(), serviceResponse);
		OutputStream out = httpServletResponse.getOutputStream();
		ObjectMapper mapper = new ObjectMapper();
		mapper.writeValue(out, r);
		out.flush();
	    }


	public ServiceResponse buildServiceResponse() {
		serviceresponse.setStatusCode(HttpStatusCode.SUCCESS.getOrdinal());
		serviceresponse.setserviceFlag(ResponseFlag.INVALID_CREDS);
		serviceresponse.setErrorMessage("Invalid_Credentials");
		return serviceresponse;
	}
	
}
