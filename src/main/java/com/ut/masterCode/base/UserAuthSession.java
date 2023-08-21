package com.ut.masterCode.base;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

public class UserAuthSession {

  private UserAuthSession() {
  }

  public static User getUserAuth() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    if (authentication == null || authentication != null && authentication instanceof AnonymousAuthenticationToken) {
      return null;
    }
    return (User) authentication.getPrincipal();
  }

}
