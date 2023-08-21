package com.ut.masterCode.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ut.masterCode.helper.ResponseMessageUtils;
import com.ut.masterCode.model.base.ResponseMessage;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

  @Override
  public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
    response.setStatus(HttpServletResponse.SC_OK);
    response.setContentType("application/json");
    ResponseMessage<Object> responseMessage = ResponseMessageUtils.makeResponse(false, 401, "Unauthorized", authException.getMessage());
    String json = new ObjectMapper().writeValueAsString(responseMessage);
    response.getWriter().write(json);
  }

}
