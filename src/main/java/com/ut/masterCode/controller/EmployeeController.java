package com.ut.masterCode.controller;

import com.ut.masterCode.base.UserAuthSession;
import com.ut.masterCode.helper.ResponseMessageUtils;
import com.ut.masterCode.model.base.BaseResult;
import com.ut.masterCode.model.base.Filter;
import com.ut.masterCode.model.base.ResponseMessage;
import com.ut.masterCode.model.request.Login.CompanyRequest;
import com.ut.masterCode.model.request.Login.Employee.EmployeeRequest;
import com.ut.masterCode.model.request.Login.Employee.EmployeeUpdateRequest;
import com.ut.masterCode.model.request.Login.User.CompanyUpdateRequest;
import com.ut.masterCode.service.CompanyService;
import com.ut.masterCode.service.EmployeeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/Employee")
@Api(tags = "17. Employee",description = "Module Employee Resource")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;
    @PostMapping(value = "insert", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Employee (insert)", notes = "statusCode: 400: Bad Request (Invalid Parameter); 401:Authorization", authorizations = {@Authorization(value = "Bearer")})
    public ResponseMessage<BaseResult> InsertEmployeeFormData(@RequestBody EmployeeRequest employeeRequest){
        // Check Header Token
        if (UserAuthSession.getUserAuth() == null) {
            return ResponseMessageUtils.makeResponse(false, 401, "unauthorized", "No Permission to access");
        }
        System.out.println(employeeRequest);
        return employeeService.insert(employeeRequest);
    }
    //JSON
    @PostMapping(value = "update", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Employee (Update)", notes = "statusCode: 400: Bad Request (Invalid Parameter); 401:Authorization", authorizations = {@Authorization(value = "Bearer")})
    public ResponseMessage<BaseResult> UpdateEmployee(@RequestBody EmployeeUpdateRequest employeeUpdateRequest){
        // Check Header Token
        if (UserAuthSession.getUserAuth() == null) {
            return ResponseMessageUtils.makeResponse(false, 401, "unauthorized", "No Permission to access");
        }
        System.out.println("Controller");
        System.out.println(employeeUpdateRequest);
        return employeeService.update(employeeUpdateRequest);
    }
    @PostMapping("delete/{id}")
    @ApiOperation(value = "Employee (Delete)", notes = "statusCode: 400: Bad Request (Invalid Parameter); 401:Authorization", authorizations = {@Authorization(value = "Bearer")})
    public ResponseMessage<BaseResult>delete(@PathVariable("id")Long id) {
        //check header token
        if (UserAuthSession.getUserAuth() == null) {
            return ResponseMessageUtils.makeResponse(false, 401, "unauthorized", "No Permission to access");
        }
        return employeeService.delete(id);
    }
    @PostMapping("list")
    @ApiOperation(value = "Employee (List)", notes = "statusCode: 400: Bad Request (Invalid Parameter); 401:Authorization", authorizations = {@Authorization(value = "Bearer")})
    public ResponseMessage<BaseResult> list(@RequestBody Filter filter){
        // Check Header Token
        if (UserAuthSession.getUserAuth() == null) {
            return ResponseMessageUtils.makeResponse(false, 401, "unauthorized", "No Permission to access");
        }
        return employeeService.list(filter);
    }
    @PostMapping("getOne/{id}")
    @ApiOperation(value = "Employee (List one)", notes = "statusCode: 400: Bad Request (Invalid Parameter); 401:Authorization", authorizations = {@Authorization(value = "Bearer")})
    public ResponseMessage<BaseResult>getOne(@PathVariable("id") Long id){
        // Check Header Token
        if (UserAuthSession.getUserAuth() == null) {
            return ResponseMessageUtils.makeResponse(false, 401, "unauthorized", "No Permission to access");
        }
        return employeeService.getOne(id);
    }
}
