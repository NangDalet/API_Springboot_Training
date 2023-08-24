package com.ut.masterCode.service;

import com.ut.masterCode.model.base.BaseResult;
import com.ut.masterCode.model.base.ResponseMessage;
import com.ut.masterCode.model.request.Form.FormRequest;
import com.ut.masterCode.model.request.Form.FormUpdateRequest;

import javax.servlet.http.HttpServletRequest;
import java.net.UnknownHostException;

public interface FormService {
    ResponseMessage<BaseResult> insert(FormRequest formRequest, HttpServletRequest httpServletRequest) throws UnknownHostException;
    ResponseMessage<BaseResult> update(FormUpdateRequest formUpdateRequest, HttpServletRequest httpServletRequest) throws UnknownHostException;
}
