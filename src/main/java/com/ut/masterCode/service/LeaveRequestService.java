package com.ut.masterCode.service;

import com.ut.masterCode.model.LeaveRequest;
import com.ut.masterCode.model.base.BaseResult;
import com.ut.masterCode.model.base.Filter;
import com.ut.masterCode.model.base.ResponseMessage;
import com.ut.masterCode.model.filter.LeaveRequestFilter;
import com.ut.masterCode.model.request.LeaveRequest.LeaveRequestRequest;
import com.ut.masterCode.model.request.LeaveRequest.LeaveRequestUpdateRequest;

import javax.servlet.http.HttpServletRequest;
import java.net.UnknownHostException;

public interface LeaveRequestService {
    ResponseMessage<BaseResult> getList(LeaveRequestFilter filter, HttpServletRequest httpServletRequest) throws UnknownHostException;

    ResponseMessage<BaseResult> getOne(Long id, HttpServletRequest httpServletRequest) throws UnknownHostException;

    ResponseMessage<BaseResult> insert(LeaveRequestRequest leaveRequestRequest, HttpServletRequest httpServletRequest) throws UnknownHostException;

    ResponseMessage<BaseResult> update(LeaveRequestUpdateRequest leaveRequestUpdateRequest, HttpServletRequest httpServletRequest) throws UnknownHostException;

    ResponseMessage<BaseResult> delete(Long id, HttpServletRequest httpServletRequest) throws UnknownHostException;
}
