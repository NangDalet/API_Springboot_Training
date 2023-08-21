package com.ut.masterCode.social;

import com.ut.masterCode.mapper.primary.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.stereotype.Service;

@Service
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

  @Autowired
  private UserMapper userMapper;

//  @Override
//  public OAuth2User loadUser(OAuth2UserRequest oAuth2UserRequest) throws OAuth2AuthenticationException {
//    OAuth2User oAuth2User = super.loadUser(oAuth2UserRequest);
//
//    try {
//      return processOAuth2User(oAuth2UserRequest, oAuth2User);
//    } catch (AuthenticationException ex) {
//      throw ex;
//    } catch (Exception ex) {
//      // Throwing an instance of AuthenticationException will trigger the OAuth2AuthenticationFailureHandler
//      throw new InternalAuthenticationServiceException(ex.getMessage(), ex.getCause());
//    }
//  }
//
//  private OAuth2User processOAuth2User(OAuth2UserRequest oAuth2UserRequest, OAuth2User oAuth2User) {
//    OAuth2UserInfo oAuth2UserInfo = OAuth2UserInfoFactory.getOAuth2UserInfo(oAuth2UserRequest.getClientRegistration().getRegistrationId(), oAuth2User.getAttributes());
//    if (StringUtils.isEmpty(oAuth2UserInfo.getEmail())) {
//      throw new OAuth2AuthenticationProcessingException("Email not found from OAuth2 provider");
//    }
//
//    User user;
//    List<User> userList = userMapper.getOneByEmail(oAuth2UserInfo.getEmail());
//    if (userList.size() > 0) {
//      user = updateExistingUser(userList.get(0), oAuth2UserRequest, oAuth2UserInfo);
//    } else {
//      user = registerNewUser(oAuth2UserRequest, oAuth2UserInfo);
//    }
//
//    user.setProvider(AuthProvider.valueOf(oAuth2UserRequest.getClientRegistration().getRegistrationId()));
//    return UserPrincipal.create(user, oAuth2User.getAttributes());
//  }
//
//  private User registerNewUser(OAuth2UserRequest oAuth2UserRequest, OAuth2UserInfo oAuth2UserInfo) {
//    User user = new User();
//    user.setUsername(oAuth2UserInfo.getEmail());
//    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//    user.setPassword(passwordEncoder.encode("1WRR21CU93opjgwjgQLUJV0CwhzmmLS8L2oT23Dk"));
//    user.setName(oAuth2UserInfo.getName());
//    user.setEmail(oAuth2UserInfo.getEmail());
//
//    user.setProvider(AuthProvider.valueOf(oAuth2UserRequest.getClientRegistration().getRegistrationId()));
//    user.setProviderId(oAuth2UserInfo.getId());
//    user.setProviderImageUrl(oAuth2UserInfo.getImageUrl());
//    user.setProviderData(oAuth2UserInfo.getAttributes().toString());
//    user.setIsActive(3);
//
//    userMapper.insert(user);
//
//    // assign default role to user
////    roleMapper.insertUserRole(user.getId(), 1L);
//
//    return user;
//  }
//
//  private User updateExistingUser(User existingUser, OAuth2UserRequest oAuth2UserRequest, OAuth2UserInfo oAuth2UserInfo) {
//    existingUser.setFirstName(oAuth2UserInfo.getName());
//
//    existingUser.setProvider(AuthProvider.valueOf(oAuth2UserRequest.getClientRegistration().getRegistrationId()));
//    existingUser.setProviderId(oAuth2UserInfo.getId());
//    existingUser.setProviderImageUrl(oAuth2UserInfo.getImageUrl());
//    existingUser.setProviderData(oAuth2UserInfo.getAttributes().toString());
//
//    userMapper.update(existingUser);
//
//    return existingUser;
//  }

}
