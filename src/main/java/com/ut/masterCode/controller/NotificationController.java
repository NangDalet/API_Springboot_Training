package com.ut.masterCode.controller;

import com.ut.masterCode.base.UserAuthSession;
import com.ut.masterCode.helper.ResponseMessageUtils;
import com.ut.masterCode.model.Notification;
import com.ut.masterCode.model.base.BaseResult;
import com.ut.masterCode.model.base.Filter;
import com.ut.masterCode.model.base.ResponseMessage;
import com.ut.masterCode.model.request.Login.Notification.UserDeviceNotificationRequest;
import com.ut.masterCode.service.NotificationService;
import io.micrometer.core.annotation.Timed;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.net.UnknownHostException;

@RestController
@RequestMapping("/notification")
@Api(tags = "13. Notification", description = "Notification Resource")
@Timed
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    @PostMapping("/list")
    @ApiOperation(value = "List notification by filter", notes = "statusCode: 400: Bad Request (Invalid Parameter); 401: Unauthorized (Token Expired or Invalid); 200: Success", authorizations = {@Authorization(value = "Bearer")})
    public ResponseMessage<BaseResult> list(@RequestBody Filter filter, HttpServletRequest httpServletRequest) throws UnknownHostException {
        // Check Header Token
        if (UserAuthSession.getUserAuth() == null) {
            return ResponseMessageUtils.makeResponse(false, 401, "unauthorized", "No Permission to access");
        }
        return notificationService.getList(filter, httpServletRequest);
    }

    @PostMapping(value = "/register", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Notification Device Token (Register)", notes = "Language: 1.Khmer, 2.English; App Type: 1.Android, 2.IOS", authorizations = {@Authorization(value = "Bearer")})
    public ResponseMessage<BaseResult> add(@RequestBody UserDeviceNotificationRequest userDeviceNotificationRequest) {
        // Check Header Token
        if (UserAuthSession.getUserAuth() == null){
            return ResponseMessageUtils.makeResponse(false, 401, "unauthorized", "No Permission to access");
        }

        System.out.println(userDeviceNotificationRequest);
        return notificationService.insert(userDeviceNotificationRequest);
    }

    @PostMapping("readAll")
    @ApiOperation(value = "Read all notification by user id", notes = "Read all notification by user id", authorizations = {@Authorization(value = "Bearer")})
    public ResponseMessage<BaseResult> returnPatient(HttpServletRequest httpServletRequest) throws UnknownHostException {
        return notificationService.readAll(httpServletRequest);
    }

    @PostMapping(value = "/pushNotification", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Push Notification", notes = "Notification Type: 1:Create Patient, 2:Return Patient, 3:VitalSign Patient, 4:Return Appointment\t", authorizations = {@Authorization(value = "Bearer")})
    public ResponseMessage<BaseResult> pushNotification(@RequestBody Notification notification) {
        // Check Header Token
        if (UserAuthSession.getUserAuth() == null){
            return ResponseMessageUtils.makeResponse(false, 401, "unauthorized", "No Permission to access");
        }
        return notificationService.pushNotification(notification);
    }

}