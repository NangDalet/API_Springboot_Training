package com.ut.masterCode.controller;

import com.ut.masterCode.model.RoleData;
import com.ut.masterCode.model.RoleDataUpdate;
import com.ut.masterCode.model.base.BaseResult;
import com.ut.masterCode.model.base.Filter;
import com.ut.masterCode.model.base.ResponseMessage;
import com.ut.masterCode.service.RoleService;
import io.micrometer.core.annotation.Timed;
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
@RequestMapping("/role")
@Api(tags = "03. Role", description = "Role Resource")
@Timed
public class RoleController {

    @Autowired
    private RoleService roleService;

    @PostMapping("/list")
    @ApiOperation(value = "List role by filter", notes = "List role by filter", authorizations = {@Authorization(value = "Bearer")})
    public ResponseMessage<BaseResult> list(@RequestBody Filter filter, HttpServletRequest httpServletRequest) throws UnknownHostException {
        return roleService.getList(filter, httpServletRequest);
    }

    @PostMapping("/find/{id}")
    @ApiOperation(value = "Find role by id", authorizations = {@Authorization(value = "Bearer")})
    public ResponseMessage<BaseResult> findById(@PathVariable("id") Long id, HttpServletRequest httpServletRequest) throws UnknownHostException {
        return roleService.getOne(id, httpServletRequest);
    }

    @PostMapping(value = "/add", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Add new role", notes = "Add new role", authorizations = {@Authorization(value = "Bearer")})
    public ResponseMessage<BaseResult> add(@RequestBody RoleData roleData, BindingResult bindingResult, HttpServletRequest httpServletRequest) throws UnknownHostException {
        return roleService.insert(roleData, bindingResult, httpServletRequest);
    }

    @PostMapping(value = "/update", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Update role by id", notes = "Update role", authorizations = {@Authorization(value = "Bearer")})
    public ResponseMessage<BaseResult> update(@RequestBody RoleDataUpdate roleDataUpdate, BindingResult bindingResult, HttpServletRequest httpServletRequest) throws UnknownHostException {
        return roleService.update(roleDataUpdate, bindingResult, httpServletRequest);
    }

    @PostMapping("/menu")
    @ApiOperation(value = "list menu", notes = "list menu", authorizations = {@Authorization(value = "Bearer")})
    public ResponseMessage<BaseResult> menu(HttpServletRequest httpServletRequest) throws UnknownHostException {
        return roleService.menu(httpServletRequest);
    }

    @PostMapping("/delete/{id}")
    @ApiOperation(value = "Delete role by id", notes = "Delete role", authorizations = {@Authorization(value = "Bearer")})
    public ResponseMessage<BaseResult> delete(@PathVariable("id") Long id, HttpServletRequest httpServletRequest) throws UnknownHostException {
        return roleService.delete(id, httpServletRequest);
    }
}