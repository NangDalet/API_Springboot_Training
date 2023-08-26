package com.ut.masterCode.controller;

import com.ut.masterCode.base.UserAuthSession;
import com.ut.masterCode.helper.ResponseMessageUtils;
import com.ut.masterCode.model.base.BaseResult;
import com.ut.masterCode.model.base.Filter;
import com.ut.masterCode.model.base.ResponseMessage;
import com.ut.masterCode.model.request.Form.FormRequest;
import com.ut.masterCode.model.request.Form.FormUpdateRequest;
import com.ut.masterCode.model.request.Login.Product.ProductRequest;
import com.ut.masterCode.service.FormService;
import com.ut.masterCode.service.ProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.net.UnknownHostException;

@RestController
@RequestMapping("/Form")
@Api(tags = "20. Form", description = "Module Form Resource")
public class FormController {
    @Autowired
    private FormService formService;
    @PostMapping("/list")
    @ApiOperation(value = "List form by filter", notes = "List form by filter", authorizations = {@Authorization(value = "Bearer")})
    public ResponseMessage<BaseResult> list(@RequestBody Filter filter, HttpServletRequest httpServletRequest) throws UnknownHostException {
        return formService.getList(filter, httpServletRequest);
    }
    @PostMapping("/find/{id}")
    @ApiOperation(value = "Find form by id", authorizations = {@Authorization(value = "Bearer")})
    public ResponseMessage<BaseResult> findById(@PathVariable("id") Long id, HttpServletRequest httpServletRequest) throws UnknownHostException {
        return formService.getOne(id, httpServletRequest);
    }
    @PostMapping(value = "insert", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Form (insert)", notes = "statusCode: 400: Bad Request (Invalid Parameter); 401:Authorization", authorizations = {@Authorization(value = "Bearer")})
    public ResponseMessage<BaseResult> InsertForm(@RequestBody FormRequest formRequest, HttpServletRequest httpServletRequest) throws UnknownHostException {
        // Check Header Token
        if (UserAuthSession.getUserAuth() == null) {
            return ResponseMessageUtils.makeResponse(false, 401, "unauthorized", "No Permission to access");
        }
        System.out.println(formRequest);
        return formService.insert(formRequest,httpServletRequest);
    }
    @PostMapping(value = "/update", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Update form by id", notes = "Form: [Form Type: 1.Normal, 2.Special] Sub Form: [Status: 1.Optional, 2.Require]", authorizations = {@Authorization(value = "Bearer")})
    public ResponseMessage<BaseResult> update(@RequestBody FormUpdateRequest formUpdateRequest, BindingResult bindingResult, HttpServletRequest httpServletRequest) throws UnknownHostException {
        return formService.update(formUpdateRequest, httpServletRequest);
    }
    @PostMapping("/delete/{id}")
    @ApiOperation(value = "Delete form by id", notes = "Delete form", authorizations = {@Authorization(value = "Bearer")})
    public ResponseMessage<BaseResult> delete(@PathVariable("id") Long id, HttpServletRequest httpServletRequest) throws UnknownHostException {
        return formService.delete(id, httpServletRequest);
    }
}
