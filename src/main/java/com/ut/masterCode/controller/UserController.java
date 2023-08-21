package com.ut.masterCode.controller;

import com.ut.masterCode.base.UserAuthSession;
import com.ut.masterCode.helper.ResponseMessageUtils;
import com.ut.masterCode.model.base.BaseResult;
import com.ut.masterCode.model.base.ResponseMessage;
import com.ut.masterCode.model.request.Login.User.EditProfileRequest;
import com.ut.masterCode.model.response.User.UserResponse;
import com.ut.masterCode.service.UserService;
import io.micrometer.core.annotation.Timed;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.net.UnknownHostException;

@RestController
@RequestMapping("/user")
@Api(tags = "02. User", description = "User Resource")
@Timed
public class UserController {

    @Autowired
    private UserService userService;

//    @PostMapping("/list")
//    @ApiOperation(value = "List user by filter", notes = "statusCode: 400: Bad Request (Invalid Parameter); 401: Unauthorized (Token Expired or Invalid); 200: Success", authorizations = {@Authorization(value = "Bearer")})
//    public ResponseMessage<BaseResult> list(@RequestBody UserFilter filter, HttpServletRequest httpServletRequest) throws UnknownHostException {
//        // Check Header Token
//        if (UserAuthSession.getUserAuth() == null) {
//            return ResponseMessageUtils.makeResponse(false, 401, "unauthorized", "No Permission to access");
//        }
//        return userService.getList(filter, httpServletRequest);
//    }

//    @PostMapping("/find/{id}")
//    @ApiOperation(value = "Find user by id", notes = "statusCode: 400: Bad Request (Invalid Parameter); 401: Unauthorized (Token Expired or Invalid); 200: Success", authorizations = {@Authorization(value = "Bearer")})
//    public ResponseMessage<BaseResult> findById(@PathVariable("id") Long id, HttpServletRequest httpServletRequest) throws UnknownHostException {
//        // Check Header Token
//        if (UserAuthSession.getUserAuth() == null) {
//            return ResponseMessageUtils.makeResponse(false, 401, "unauthorized", "No Permission to access");
//        }
//        return userService.getOne(id, httpServletRequest);
//    }

//    @PostMapping(value = "/add", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
//    @ApiOperation(value = "Add new user", notes = "statusCode: 400: Bad Request (Invalid Parameter); 401: Unauthorized (Token Expired or Invalid); 200: Success", authorizations = {@Authorization(value = "Bearer")})
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "fullName", dataType = "string", paramType = "form", required = true),
//            @ApiImplicitParam(name = "username", dataType = "string", paramType = "form", required = true),
//            @ApiImplicitParam(name = "employeeId", dataType = "long", paramType = "form", required = true),
//            @ApiImplicitParam(name = "password", dataType = "string", paramType = "form", required = true),
//            @ApiImplicitParam(name = "userGroup", dataType = "string", paramType = "form", required = true, allowMultiple = true)
//    })
//    public ResponseMessage<BaseResult> add(@ApiIgnore User user, BindingResult bindingResult, @RequestPart(name = "file_signature", required = false) MultipartFile file, HttpServletRequest httpServletRequest) throws UnknownHostException {
//        // Check Header Token
//        if (UserAuthSession.getUserAuth() == null) {
//            return ResponseMessageUtils.makeResponse(false, 401, "unauthorized", "No Permission to access");
//        }
//        // Check Validation
//        if (bindingResult.hasErrors()) {
//            return ResponseMessageUtils.makeResponse(false, bindingResult);
//        }
//        return userService.insert(user, file, httpServletRequest);
//    }

//    @PostMapping(value = "/update", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
//    @ApiOperation(value = "Update user by id", notes = "statusCode: 400: Bad Request (Invalid Parameter); 401: Unauthorized (Token Expired or Invalid); 200: Success", authorizations = {@Authorization(value = "Bearer")})
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "id", dataType = "long", paramType = "form", required = true),
//            @ApiImplicitParam(name = "employeeId", dataType = "long", paramType = "form", required = true),
//            @ApiImplicitParam(name = "username", dataType = "string", paramType = "form", required = true),
//            @ApiImplicitParam(name = "password", dataType = "string", paramType = "form", required = true),
//            @ApiImplicitParam(name = "userGroup", dataType = "string", paramType = "form", required = true, allowMultiple = true)
//    })
//    public ResponseMessage<BaseResult> update(@ApiIgnore User user, BindingResult bindingResult, @RequestPart(name = "file_signature", required = false) MultipartFile file, HttpServletRequest httpServletRequest) throws UnknownHostException {
//        // Check Header Token
//        if (UserAuthSession.getUserAuth() == null) {
//            return ResponseMessageUtils.makeResponse(false, 401, "unauthorized", "No Permission to access");
//        }
//        // Check Validation
//        if (bindingResult.hasErrors()) {
//            return ResponseMessageUtils.makeResponse(false, bindingResult);
//        }
//        return userService.update(user, file, httpServletRequest);
//    }

    @PostMapping(value = "/edit-profile", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Edit user profile by employee id", notes = "Photo Type: 0. by upload; 1. by webcam; 2. by java; employeeGender: F. Female, M. Male", authorizations = {@Authorization(value = "Bearer")})
    public ResponseMessage<BaseResult> updateUser(@RequestBody EditProfileRequest editProfileRequest, HttpServletRequest httpServletRequest) throws UnknownHostException {
        // Check Header Token
        if (UserAuthSession.getUserAuth() == null) {
            return ResponseMessageUtils.makeResponse(false, 401, "unauthorized", "No Permission to access");
        }
        return userService.editProfile(editProfileRequest, httpServletRequest);
    }

//    @PostMapping("/delete/{id}")
//    @ApiOperation(value = "Delete user by id", notes = "statusCode: 400: Bad Request (Invalid Parameter); 401: Unauthorized (Token Expired or Invalid); 200: Success", authorizations = {@Authorization(value = "Bearer")})
//    public ResponseMessage<String> delete(@PathVariable("id") Long id, HttpServletRequest httpServletRequest) throws UnknownHostException {
//        // Check Header Token
//        if (UserAuthSession.getUserAuth() == null) {
//            return ResponseMessageUtils.makeResponse(false, 401, "unauthorized", "No Permission to access");
//        }
//        return userService.delete(id, httpServletRequest);
//    }

    @PostMapping("/me")
    @ApiOperation(value = "Get logged in user", notes = "Photo Type: 0. by upload; 1. by webcam; 2. by java; employeeGender: F. Female, M. Male", authorizations = {@Authorization(value = "Bearer")})
    public ResponseMessage<UserResponse> me(HttpServletRequest httpServletRequest) throws UnknownHostException {
        // Check Header Token
        if (UserAuthSession.getUserAuth() == null) {
            return ResponseMessageUtils.makeResponse(false, 401, "unauthorized", "No Permission to access");
        }
        return userService.me(httpServletRequest);
    }

    @PostMapping(value = "/change-password", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ApiOperation(value = "Change password", notes = "Change password", authorizations = {@Authorization(value = "Bearer")})
    @ApiImplicitParams({
            @ApiImplicitParam(name = "oldPassword", dataType = "string", paramType = "form", required = true),
            @ApiImplicitParam(name = "newPassword", dataType = "string", paramType = "form", required = true)
    })
    public ResponseMessage<String> changePassword(@RequestParam("oldPassword") String oldPassword, @RequestParam("newPassword") String newPassword, HttpServletRequest httpServletRequest) throws UnknownHostException {
        // Check Header Token
        if (UserAuthSession.getUserAuth() == null) {
            return ResponseMessageUtils.makeResponse(false, 401, "unauthorized", "No Permission to access");
        }
        return userService.changePassword(oldPassword, newPassword,httpServletRequest);
    }

}