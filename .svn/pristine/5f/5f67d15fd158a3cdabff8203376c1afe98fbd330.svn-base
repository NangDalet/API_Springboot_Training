package com.ut.masterCode.service;

import com.ut.masterCode.model.Notification;
import com.ut.masterCode.model.base.BaseResult;
import com.ut.masterCode.model.base.Filter;
import com.ut.masterCode.model.base.ResponseMessage;
import com.ut.masterCode.model.request.Login.Notification.UserDeviceNotificationRequest;

import javax.servlet.http.HttpServletRequest;
import java.net.UnknownHostException;

public interface NotificationService {

  ResponseMessage<BaseResult> getList(Filter filter, HttpServletRequest httpServletRequest) throws UnknownHostException;

  ResponseMessage<BaseResult> insert(UserDeviceNotificationRequest userDeviceNotificationRequest);

  ResponseMessage<BaseResult> readAll(HttpServletRequest httpServletRequest) throws UnknownHostException;

  ResponseMessage<BaseResult> pushNotification(Notification notification);

}