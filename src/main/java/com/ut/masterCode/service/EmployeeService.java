package com.ut.masterCode.service;

import com.ut.masterCode.model.base.BaseResult;
import com.ut.masterCode.model.base.Filter;
import com.ut.masterCode.model.base.ResponseMessage;
import com.ut.masterCode.model.request.Login.Employee.EmployeeRequest;
import com.ut.masterCode.model.request.Login.Employee.EmployeeUpdateRequest;
import org.springframework.stereotype.Service;

public interface EmployeeService {
    ResponseMessage<BaseResult> insert(EmployeeRequest employeeRequest);

    ResponseMessage<BaseResult> update(EmployeeUpdateRequest employeeUpdateRequest);

    ResponseMessage<BaseResult> delete(Long id);

    ResponseMessage<BaseResult> list(Filter filter);

    ResponseMessage<BaseResult> getOne(Long id);
}
