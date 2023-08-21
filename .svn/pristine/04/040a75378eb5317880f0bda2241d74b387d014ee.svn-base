package com.ut.masterCode.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ut.masterCode.helper.ResponseMessageUtils;
import com.ut.masterCode.model.base.ResponseMessage;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.oauth2.provider.error.OAuth2AccessDeniedHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CustomOAuth2AccessDeniedHandler extends OAuth2AccessDeniedHandler {

  @Override
  public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException authException) throws IOException {
    response.setStatus(HttpServletResponse.SC_OK);
    response.setContentType("application/json");
    ResponseMessage<Object> responseMessage = ResponseMessageUtils.makeResponse(false, 403, "access_denied", authException.getMessage());
    String json = new ObjectMapper().writeValueAsString(responseMessage);
    response.getWriter().write(json);
  }
}
