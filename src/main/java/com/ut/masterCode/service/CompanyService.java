package com.ut.masterCode.service;

import com.ut.masterCode.model.Company;
import com.ut.masterCode.model.base.BaseResult;
import com.ut.masterCode.model.base.Filter;
import com.ut.masterCode.model.base.ResponseMessage;
import com.ut.masterCode.model.request.Login.CompanyRequest;
import com.ut.masterCode.model.request.Login.User.CompanyUpdateRequest;
import org.springframework.stereotype.Service;


public interface CompanyService {

    ResponseMessage<BaseResult> insert(CompanyRequest companyRequest);

    ResponseMessage<BaseResult> update(CompanyUpdateRequest companyUpdateRequest);

    ResponseMessage<BaseResult> delete(Long id);

    ResponseMessage<BaseResult> list(Filter filter);

    ResponseMessage<BaseResult> getOne(Long id);
}
