package com.ut.masterCode.controller;

import com.ut.masterCode.base.UserAuthSession;
import com.ut.masterCode.helper.ResponseMessageUtils;
import com.ut.masterCode.model.RoleData;
import com.ut.masterCode.model.base.BaseResult;
import com.ut.masterCode.model.base.Filter;
import com.ut.masterCode.model.base.ResponseMessage;
import com.ut.masterCode.model.request.Login.CompanyRequest;
import com.ut.masterCode.model.request.Login.User.CompanyUpdateRequest;
import com.ut.masterCode.service.CompanyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.awt.*;
import java.net.UnknownHostException;

@RestController
@RequestMapping("/Company")
@Api(tags = "16. Company",description = "Module Company Resource")
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    @PostMapping(value = "insert", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Company (insert)", notes = "statusCode: 400: Bad Request (Invalid Parameter); 401:Authorization", authorizations = {@Authorization(value = "Bearer")})
    public ResponseMessage<BaseResult> InsertCompanyFormData(@RequestBody CompanyRequest companyRequest){
        // Check Header Token
        if (UserAuthSession.getUserAuth() == null) {
            return ResponseMessageUtils.makeResponse(false, 401, "unauthorized", "No Permission to access");
        }
        System.out.println(companyRequest);
        return companyService.insert(companyRequest);
    }
    //JSON
    @PostMapping(value = "update", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Company (Update)", notes = "statusCode: 400: Bad Request (Invalid Parameter); 401:Authorization", authorizations = {@Authorization(value = "Bearer")})
    public ResponseMessage<BaseResult> UpdateCompany(@RequestBody CompanyUpdateRequest companyUpdateRequest){
        // Check Header Token
        if (UserAuthSession.getUserAuth() == null) {
            return ResponseMessageUtils.makeResponse(false, 401, "unauthorized", "No Permission to access");
        }
        System.out.println("Controller");
        System.out.println(companyUpdateRequest);
        return companyService.update(companyUpdateRequest);
    }
    @PostMapping("delete/{id}")
    @ApiOperation(value = "Company (Delete)", notes = "statusCode: 400: Bad Request (Invalid Parameter); 401:Authorization", authorizations = {@Authorization(value = "Bearer")})
    public ResponseMessage<BaseResult>delete(@PathVariable("id")Long id) {
        //check header token
        if (UserAuthSession.getUserAuth() == null) {
            return ResponseMessageUtils.makeResponse(false, 401, "unauthorized", "No Permission to access");
        }
        return companyService.delete(id);
    }
    @PostMapping("list")
    @ApiOperation(value = "Company (List)", notes = "statusCode: 400: Bad Request (Invalid Parameter); 401:Authorization", authorizations = {@Authorization(value = "Bearer")})
    public ResponseMessage<BaseResult> list(@RequestBody Filter filter){
        // Check Header Token
        if (UserAuthSession.getUserAuth() == null) {
            return ResponseMessageUtils.makeResponse(false, 401, "unauthorized", "No Permission to access");
        }
        return companyService.list(filter);
    }
    @PostMapping("getOne/{id}")
    @ApiOperation(value = "Company (List one)", notes = "statusCode: 400: Bad Request (Invalid Parameter); 401:Authorization", authorizations = {@Authorization(value = "Bearer")})
    public ResponseMessage<BaseResult>getOne(@PathVariable("id") Long id){
        // Check Header Token
        if (UserAuthSession.getUserAuth() == null) {
            return ResponseMessageUtils.makeResponse(false, 401, "unauthorized", "No Permission to access");
        }
        return companyService.getOne(id);
    }

}


