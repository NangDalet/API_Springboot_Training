package com.ut.masterCode.controller;

import com.ut.masterCode.base.UserAuthSession;
import com.ut.masterCode.helper.ResponseMessageUtils;
import com.ut.masterCode.model.base.BaseResult;
import com.ut.masterCode.model.base.ResponseMessage;
import com.ut.masterCode.model.filter.LeaveRequestFilter;
import com.ut.masterCode.model.request.LeaveRequest.LeaveRequestRequest;
import com.ut.masterCode.model.request.LeaveRequest.LeaveRequestUpdateRequest;
import com.ut.masterCode.service.LeaveRequestService;
import io.micrometer.core.annotation.Timed;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.net.UnknownHostException;

@RestController
@RequestMapping("/leave-request")
@Api(tags = "19. Leave Request", description = "Leave Request Resource")
@Timed
public class LeaveRequestController {
    @Autowired
    private LeaveRequestService leaveRequestService;

    @PostMapping("/list")
    @ApiOperation(value = "List leave Request by filter", notes = "Request date is array of string.", authorizations = {@Authorization(value = "Bearer")})
    public ResponseMessage<BaseResult> list(@RequestBody LeaveRequestFilter filter, HttpServletRequest httpServletRequest) throws UnknownHostException {
        // Check Header Token
        if (UserAuthSession.getUserAuth() == null) {
            return ResponseMessageUtils.makeResponse(false, 401, "unauthorized", "No Permission to access");
        }
        return leaveRequestService.getList(filter, httpServletRequest);
    }
    @PostMapping("/find/{id}")
    @ApiOperation(value = "Leave Request (Find) By Id", notes = "Request date is array of string.", authorizations = {@Authorization(value = "Bearer")})
    private ResponseMessage<BaseResult> getOne(@PathVariable("id") Long id, HttpServletRequest httpServletRequest) throws UnknownHostException {
        // Check Header Token
        if (UserAuthSession.getUserAuth() == null) {
            return ResponseMessageUtils.makeResponse(false, 401, "unauthorized", "No Permission to access");
        }
        return leaveRequestService.getOne(id, httpServletRequest);
    }
    @PostMapping(value = "/add", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Add new leave request", notes = "Request date is array of string.", authorizations = {@Authorization(value = "Bearer")})
    public ResponseMessage<BaseResult> add(@RequestBody LeaveRequestRequest leaveRequestRequest, HttpServletRequest httpServletRequest) throws UnknownHostException {
        // Check Header Token
        if (UserAuthSession.getUserAuth() == null) {
            return ResponseMessageUtils.makeResponse(false, 401, "unauthorized", "No Permission to access");
        }
        return leaveRequestService.insert(leaveRequestRequest, httpServletRequest);
    }
    @PostMapping(value = "/update", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Update leave request by id", notes = "statusCode: 400: Bad Request (I nvalid Parameter); 401: Unauthorized (Token Expired or Invalid); 200: Success", authorizations = {@Authorization(value = "Bearer")})
    public ResponseMessage<BaseResult> update(@RequestBody LeaveRequestUpdateRequest leaveRequestUpdateRequest, HttpServletRequest httpServletRequest) throws UnknownHostException {
        // Check Header Token
        if (UserAuthSession.getUserAuth() == null) {
            return ResponseMessageUtils.makeResponse(false, 401, "unauthorized", "No Permission to access");
        }
        return leaveRequestService.update(leaveRequestUpdateRequest, httpServletRequest);
    }
    @PostMapping("/delete/{id}")
    @ApiOperation(value = "Delete leave request by id", authorizations = {@Authorization(value = "Bearer")})
    public ResponseMessage<BaseResult> delete(@PathVariable("id") Long id, HttpServletRequest httpServletRequest) throws UnknownHostException {
        if (UserAuthSession.getUserAuth() == null) {
            return ResponseMessageUtils.makeResponse(false, 401, "unauthorized", "No Permission to access");
        }
        return leaveRequestService.delete(id, httpServletRequest);
    }
}
