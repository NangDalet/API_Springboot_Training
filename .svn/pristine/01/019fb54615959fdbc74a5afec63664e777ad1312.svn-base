package com.ut.masterCode.social;

import com.ut.masterCode.social.util.CookieUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

import static com.ut.masterCode.social.HttpCookieOAuth2AuthorizationRequestRepository.REDIRECT_URI_PARAM_COOKIE_NAME;

@Component
public class OAuth2AuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

  @Autowired
  OAuth2AuthorizedClientService clientService;

  @Autowired
  Environment environment;

  private final HttpCookieOAuth2AuthorizationRequestRepository httpCookieOAuth2AuthorizationRequestRepository;

  @Autowired
  OAuth2AuthenticationSuccessHandler(HttpCookieOAuth2AuthorizationRequestRepository httpCookieOAuth2AuthorizationRequestRepository) {
    this.httpCookieOAuth2AuthorizationRequestRepository = httpCookieOAuth2AuthorizationRequestRepository;
  }

  @Override
  public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
    String targetUrl = determineTargetUrl(request, response, authentication);

    if (response.isCommitted()) {
      logger.debug("Response has already been committed. Unable to redirect to " + targetUrl);
      return;
    }

    clearAuthenticationAttributes(request, response);
    getRedirectStrategy().sendRedirect(request, response, targetUrl);
  }

  protected String determineTargetUrl(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
    Optional<String> redirectUri = CookieUtils.getCookie(request, REDIRECT_URI_PARAM_COOKIE_NAME).map(Cookie::getValue);
    String targetUrl = redirectUri.orElse(getDefaultTargetUrl());

    String token = createToken(authentication);

    return UriComponentsBuilder.fromUriString(targetUrl)
      .queryParam("token", token)
      .build().toUriString();
  }

  protected void clearAuthenticationAttributes(HttpServletRequest request, HttpServletResponse response) {
    super.clearAuthenticationAttributes(request);
    httpCookieOAuth2AuthorizationRequestRepository.removeAuthorizationRequestCookies(request, response);
  }

  protected String createToken(Authentication authentication) {
    UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();

    RestTemplate restTemplate = new RestTemplate();

    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

    MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
    map.add("grant_type", "password");
    map.add("client_id", "ut-client");
    map.add("client_secret", "ut-secret");
    map.add("username", userPrincipal.getEmail());
    map.add("password", "1WRR21CU93opjgwjgQLUJV0CwhzmmLS8L2oT23Dk");

    HttpEntity<MultiValueMap<String, String>> requestOauthToken = new HttpEntity<>(map, headers);

    String http = environment.getProperty("server.http");
    String context = environment.getProperty("server.servlet.context-path");
    String accessToken = "";

    try {
      ResponseEntity<String> responseOauthToken = restTemplate.postForEntity(http + context + "/oauth/token", requestOauthToken, String.class);
      JSONObject jsonObject = new JSONObject(responseOauthToken.getBody());
      accessToken = jsonObject.getString("access_token");
    } catch (Exception e) {
      e.printStackTrace();
    }

    return accessToken;
  }
}
