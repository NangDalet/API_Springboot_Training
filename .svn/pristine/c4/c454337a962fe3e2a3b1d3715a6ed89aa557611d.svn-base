package com.ut.masterCode.service;

import com.ut.masterCode.model.RoleData;
import com.ut.masterCode.model.RoleDataUpdate;
import com.ut.masterCode.model.base.BaseResult;
import com.ut.masterCode.model.base.Filter;
import com.ut.masterCode.model.base.ResponseMessage;
import org.springframework.validation.BindingResult;

import javax.servlet.http.HttpServletRequest;
import java.net.UnknownHostException;

public interface RoleService {

  ResponseMessage<BaseResult> getList(Filter filter, HttpServletRequest httpServletRequest) throws UnknownHostException;

  ResponseMessage<BaseResult> getOne(Long id, HttpServletRequest httpServletRequest) throws UnknownHostException;

  ResponseMessage<BaseResult> menu(HttpServletRequest httpServletRequest) throws UnknownHostException;

  ResponseMessage<BaseResult> insert(RoleData roleData, BindingResult bindingResult, HttpServletRequest httpServletRequest) throws UnknownHostException;

  ResponseMessage<BaseResult> update(RoleDataUpdate roleDataUpdate, BindingResult bindingResult, HttpServletRequest httpServletRequest) throws UnknownHostException;

  ResponseMessage<BaseResult> delete(Long id, HttpServletRequest httpServletRequest) throws UnknownHostException;

}