package com.ut.masterCode.service;

import com.ut.masterCode.model.base.BaseResult;
import com.ut.masterCode.model.base.Filter;
import com.ut.masterCode.model.base.ResponseMessage;
import com.ut.masterCode.model.filter.LeaveRequestFilter;
import com.ut.masterCode.model.request.LeaveRequest.LeaveRequestUpdateRequest;
import com.ut.masterCode.model.request.Login.Product.ProductRequest;
import com.ut.masterCode.model.request.Login.Product.ProductUpdateRequest;
import com.ut.masterCode.model.request.Login.User.CompanyUpdateRequest;
import org.springframework.stereotype.Repository;

import javax.servlet.http.HttpServletRequest;
import java.net.UnknownHostException;

public interface ProductService {
    ResponseMessage<BaseResult> getList(LeaveRequestFilter filter, HttpServletRequest httpServletRequest) throws UnknownHostException;
    ResponseMessage<BaseResult> getOne(Long id, HttpServletRequest httpServletRequest) throws UnknownHostException;
    ResponseMessage<BaseResult> insert(ProductRequest productRequest, HttpServletRequest httpServletRequest) throws UnknownHostException;
    ResponseMessage<BaseResult> update(ProductUpdateRequest productUpdateRequest, HttpServletRequest httpServletRequest) throws UnknownHostException;
    ResponseMessage<BaseResult> delete(Long id, HttpServletRequest httpServletRequest) throws UnknownHostException;
}
