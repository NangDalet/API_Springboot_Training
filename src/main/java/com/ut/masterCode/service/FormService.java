package com.ut.masterCode.service;

import com.ut.masterCode.model.base.BaseResult;
import com.ut.masterCode.model.base.Filter;
import com.ut.masterCode.model.base.ResponseMessage;
import com.ut.masterCode.model.request.Form.FormRequest;
import com.ut.masterCode.model.request.Form.FormUpdateRequest;
import org.apache.ibatis.annotations.Param;

import javax.servlet.http.HttpServletRequest;
import java.net.UnknownHostException;
import java.util.List;

public interface FormService {
    ResponseMessage<BaseResult> getList(Filter filter, HttpServletRequest httpServletRequest) throws UnknownHostException;
    ResponseMessage<BaseResult> getOne(Long id, HttpServletRequest httpServletRequest) throws UnknownHostException;
    ResponseMessage<BaseResult> insert(FormRequest formRequest, HttpServletRequest httpServletRequest) throws UnknownHostException;
    ResponseMessage<BaseResult> update(FormUpdateRequest formUpdateRequest, HttpServletRequest httpServletRequest) throws UnknownHostException;
    ResponseMessage<BaseResult> delete(Long id, HttpServletRequest httpServletRequest) throws UnknownHostException;
}
