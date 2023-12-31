package com.ut.masterCode.controller;

import com.ut.masterCode.base.UserAuthSession;
import com.ut.masterCode.helper.ResponseMessageUtils;
import com.ut.masterCode.model.base.BaseResult;
import com.ut.masterCode.model.base.Filter;
import com.ut.masterCode.model.base.ResponseMessage;
import com.ut.masterCode.model.filter.LeaveRequestFilter;
import com.ut.masterCode.model.filter.ProductFilter;
import com.ut.masterCode.model.request.Login.CompanyRequest;
import com.ut.masterCode.model.request.Login.Product.ProductRequest;
import com.ut.masterCode.model.request.Login.Product.ProductUpdateRequest;
import com.ut.masterCode.model.request.Login.User.CompanyUpdateRequest;
import com.ut.masterCode.service.CompanyService;
import com.ut.masterCode.service.ProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.net.UnknownHostException;

@RestController
@RequestMapping("/Product")
@Api(tags = "18. Product", description = "Module Product Resource")
public class ProductController {
    @Autowired
    private ProductService productService;

    @PostMapping("/list")
    @ApiOperation(value = "List product by filter", notes = "Request size is array of string.", authorizations = {@Authorization(value = "Bearer")})
    public ResponseMessage<BaseResult> list(@RequestBody ProductFilter filter, HttpServletRequest httpServletRequest) throws UnknownHostException {
        // Check Header Token
        if (UserAuthSession.getUserAuth() == null) {
            return ResponseMessageUtils.makeResponse(false, 401, "unauthorized", "No Permission to access");
        }
        return productService.getList(filter, httpServletRequest);
    }
    @PostMapping("/find/{id}")
    @ApiOperation(value = "Product Request (Find) By Id", notes = "Product size is array of string.", authorizations = {@Authorization(value = "Bearer")})
    private ResponseMessage<BaseResult> getOne(@PathVariable("id") Long id, HttpServletRequest httpServletRequest) throws UnknownHostException {
        // Check Header Token
        if (UserAuthSession.getUserAuth() == null) {
            return ResponseMessageUtils.makeResponse(false, 401, "unauthorized", "No Permission to access");
        }
        return productService.getOne(id, httpServletRequest);
    }

    @PostMapping(value = "insert", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Product (insert)", notes = "statusCode: 400: Bad Request (Invalid Parameter); 401:Authorization", authorizations = {@Authorization(value = "Bearer")})
    public ResponseMessage<BaseResult> InsertProductFormData(@RequestBody ProductRequest productRequest, HttpServletRequest httpServletRequest) throws UnknownHostException {
        // Check Header Token
        if (UserAuthSession.getUserAuth() == null) {
            return ResponseMessageUtils.makeResponse(false, 401, "unauthorized", "No Permission to access");
        }
        System.out.println(productRequest);
        return productService.insert(productRequest,httpServletRequest);
    }

    //JSON
    @PostMapping(value = "update", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Product (Update)", notes = "statusCode: 400: Bad Request (Invalid Parameter); 401:Authorization", authorizations = {@Authorization(value = "Bearer")})
    public ResponseMessage<BaseResult> UpdateProduct(@RequestBody ProductUpdateRequest productUpdateRequest, HttpServletRequest httpServletRequest) throws UnknownHostException {
        // Check Header Token
       if (UserAuthSession.getUserAuth() == null) {
            return ResponseMessageUtils.makeResponse(false, 401, "unauthorized", "No Permission to access");
        }
        return productService.update(productUpdateRequest, httpServletRequest);
    }

    @PostMapping("/delete/{id}")
    @ApiOperation(value = "Delete product request by id", authorizations = {@Authorization(value = "Bearer")})
    public ResponseMessage<BaseResult> delete(@PathVariable("id") Long id, HttpServletRequest httpServletRequest) throws UnknownHostException {
        if (UserAuthSession.getUserAuth() == null) {
            return ResponseMessageUtils.makeResponse(false, 401, "unauthorized", "No Permission to access");
        }
        return productService.delete(id, httpServletRequest);
    }
}
