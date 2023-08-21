package com.ut.masterCode.model.base;

import lombok.Data;

@Data
public class OauthToken {

  private String accessToken;
  private String tokenType;
  private String refreshToken;
  private Long expiresIn;
  private String scope;
  private Long statusLogin;
}