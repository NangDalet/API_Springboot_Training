package com.ut.masterCode.service;

import com.ut.masterCode.model.Users.User;
import com.ut.masterCode.model.base.BaseResult;
import com.ut.masterCode.model.base.Filter;
import com.ut.masterCode.model.base.ResponseMessage;
import com.ut.masterCode.model.request.Login.User.EditProfileRequest;
import com.ut.masterCode.model.response.User.UserResponse;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.net.UnknownHostException;

public interface UserService {

  User getUserAuth();

  ResponseMessage<BaseResult> getList(Filter filter, HttpServletRequest httpServletRequest) throws UnknownHostException;

  ResponseMessage<BaseResult> getOne(Long id, HttpServletRequest httpServletRequest) throws UnknownHostException;

  ResponseMessage<BaseResult> insert(User user, MultipartFile file, HttpServletRequest httpServletRequest) throws UnknownHostException;

  ResponseMessage<BaseResult> update(User user, MultipartFile file, HttpServletRequest httpServletRequest) throws UnknownHostException;

  ResponseMessage<BaseResult> editProfile(EditProfileRequest editProfileRequest, HttpServletRequest httpServletRequest) throws UnknownHostException;

  ResponseMessage<String> changePassword(String oldPassword, String newPassword, HttpServletRequest httpServletRequest) throws UnknownHostException;

  ResponseMessage<String> delete(Long id, HttpServletRequest httpServletRequest) throws UnknownHostException;

  ResponseMessage<UserResponse> me(HttpServletRequest httpServletRequest) throws UnknownHostException;

}