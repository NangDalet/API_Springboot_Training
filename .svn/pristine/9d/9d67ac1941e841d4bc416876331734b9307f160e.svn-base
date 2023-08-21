package com.ut.masterCode.service;

import com.ut.masterCode.model.ModuleTypeFilter;
import com.ut.masterCode.model.base.BaseResult;
import com.ut.masterCode.model.base.ResponseMessage;

import javax.servlet.http.HttpServletRequest;
import java.net.UnknownHostException;

public interface ModuleTypeService {

  ResponseMessage<BaseResult> getList(ModuleTypeFilter filter, HttpServletRequest httpServletRequest) throws UnknownHostException;

  ResponseMessage<BaseResult> getOne(Long id, HttpServletRequest httpServletRequest) throws UnknownHostException;

}