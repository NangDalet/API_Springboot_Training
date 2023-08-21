package com.ut.masterCode.service;

import com.ut.masterCode.helper.ResponseMessageUtils;
import com.ut.masterCode.mapper.primary.ModuleMapper;
import com.ut.masterCode.mapper.primary.ModuleTypeMapper;
import com.ut.masterCode.mapper.primary.PermissionMapper;
import com.ut.masterCode.mapper.primary.RoleMapper;
import com.ut.masterCode.model.*;
import com.ut.masterCode.model.Users.UserList;
import com.ut.masterCode.model.base.BaseResult;
import com.ut.masterCode.model.base.Filter;
import com.ut.masterCode.model.base.Pagination;
import com.ut.masterCode.model.base.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import javax.servlet.http.HttpServletRequest;
import java.net.UnknownHostException;
import java.time.LocalTime;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

  @Autowired
  private RoleMapper roleMapper;

  @Autowired
  private PermissionMapper permissionMapper;

  @Autowired
  private ModuleTypeMapper moduleTypeMapper;

  @Autowired
  private ModuleMapper moduleMapper;

  @Autowired
  private UserService userService;

  @Autowired
  private MessageService messageService;

  @Autowired
  private ActivityLogService activityLogService;

  public ResponseMessage<BaseResult> getList(Filter filter, HttpServletRequest httpServletRequest) throws UnknownHostException {
    LocalTime startDuration = LocalTime.now();
    Long line = 1033L;
    try {
      // Check Permission
      Long userId = userService.getUserAuth().getId();
      if (permissionMapper.checkPermission(userId, "System Role (View)") == 0) {
        return ResponseMessageUtils.makeResponseByPermission(true, messageService.message("No Permission access.", false));
      }

      Pagination pagination = new Pagination();
      pagination.setPage(filter.getPage());
      pagination.setRowsPerPage(filter.getRowsPerPage());
      pagination.setTotal(roleMapper.countList(filter));

      filter.setPage((filter.getPage() - 1) * filter.getRowsPerPage());

      List<Role> roleList = roleMapper.getList(filter);
      if(!roleList.isEmpty()){
        for (Role role : roleList) {
          Long id = role.getId();
          role.setUserList(roleMapper.getUser(id));

          ModuleTypeFilter moduleTypeFilter = new ModuleTypeFilter();
          moduleTypeFilter.setPage(0);
          moduleTypeFilter.setRowsPerPage(10000);
          List<ModuleType> moduleTypeList = moduleTypeMapper.getList(moduleTypeFilter);
          if (moduleTypeList != null) {
            for (ModuleType moduleType : moduleTypeList) {
              Long moduleTypeId = moduleType.getId();
              moduleType.setModuleList(moduleMapper.getOneByRoleId(moduleTypeId, id));
            }
          }
          role.setModuleTypeList(moduleTypeList);
        }
      }
      /*System Activity*/
      LocalTime endDuration = LocalTime.now();
      activityLogService.insert("/role/list",null,null,"System Role","System Role (View)","View",1,"Success",startDuration,endDuration, httpServletRequest);
      return ResponseMessageUtils.makeResponse(true, messageService.message("Success", roleList, pagination, true));
    } catch (Exception error) {
      /*System Activity*/
      LocalTime endDuration = LocalTime.now();
      activityLogService.insert("/role/list",line, error.toString(),"System Role","System Role (View)","View",2,"Error",startDuration,endDuration, httpServletRequest);
      return ResponseMessageUtils.makeResponse(true, messageService.message("Error", null, false));
    }
  }

  public ResponseMessage<BaseResult> getOne(Long id, HttpServletRequest httpServletRequest) throws UnknownHostException {
    LocalTime startDuration = LocalTime.now();
    Long line = 1033L;
    try {
      // Check Permission
      Long userId = userService.getUserAuth().getId();
      if (permissionMapper.checkPermission(userId, "System Role (View)") == 0) {
        return ResponseMessageUtils.makeResponseByPermission(true, messageService.message("No Permission access.", false));
      }

      List<Role> roles = roleMapper.getOne(id);
      if(!roles.isEmpty()){
        Long roleId = roles.get(0).getId();
        roles.get(0).setUserList(roleMapper.getUser(roleId));

        ModuleTypeFilter moduleTypeFilter = new ModuleTypeFilter();
        moduleTypeFilter.setPage(0);
        moduleTypeFilter.setRowsPerPage(10000);
        List<ModuleType> moduleTypeList = moduleTypeMapper.getList(moduleTypeFilter);
        if(moduleTypeList != null){
          for (ModuleType moduleType : moduleTypeList) {
            Long moduleTypeId = moduleType.getId();
            moduleType.setModuleList(moduleMapper.getOneByRoleId(moduleTypeId, roleId));
          }
        }
        roles.get(0).setModuleTypeList(moduleTypeList);
      }
      /*System Activity*/
      LocalTime endDuration = LocalTime.now();
      activityLogService.insert("/role/find/{id}",null,null,"System Role","System Role (View)","View",1,"Success",startDuration,endDuration, httpServletRequest);
      return ResponseMessageUtils.makeResponse(true, messageService.message("Success", roles, true));
    } catch (Exception error) {
      /*System Activity*/
      LocalTime endDuration = LocalTime.now();
      activityLogService.insert("/role/find/{id}",line, error.toString(),"System Role","System Role (View)","View",2,"Error",startDuration,endDuration, httpServletRequest);
      return ResponseMessageUtils.makeResponse(true, messageService.message("Error", null, false));
    }
  }

  public ResponseMessage<BaseResult> insert(RoleData roleData, BindingResult bindingResult, HttpServletRequest httpServletRequest) throws UnknownHostException {
    LocalTime startDuration = LocalTime.now();
    Long line = 1033L;
    try {
     // Check Permission
     Long userId = userService.getUserAuth().getId();
     if (permissionMapper.checkPermission(userId, "System Role (Add)") == 0) {
       return ResponseMessageUtils.makeResponseByPermission(true, messageService.message("No Permission access.", false));
     }

     // Check Validation
     if (bindingResult.hasErrors()) {
       return ResponseMessageUtils.makeResponse(true, bindingResult);
     }

     // Check Required
     if(roleData.getName().isEmpty() || roleData.getName() == null){
       return ResponseMessageUtils.makeResponse(true, messageService.message("Required", false));
     }

     // Check Duplicate
     if(roleMapper.checkDuplicate(roleData.getName(), null) > 0){
       return ResponseMessageUtils.makeResponse(true, messageService.message("Duplicate", false));
     }

     // Check Data
     Role role = new Role();
     role.setName(roleData.getName());
     role.setCreatedBy(userId);
     role.setIsActive(1);

     Boolean result = roleMapper.insert(role);
     if (result) {
       //Insert User and Module
       if(roleData.getUserList() != null){
         insertRoleUser(roleData.getUserList(), role.getId());
       }
       if(roleData.getModuleList() != null){
         insertRolePermission(roleData.getModuleList(), role.getId());
       }
       /*System Activity*/
       LocalTime endDuration = LocalTime.now();
       activityLogService.insert("/role/add",null,null,"System Role","System Role (Add)","Add",1,"Success",startDuration,endDuration, httpServletRequest);
       return ResponseMessageUtils.makeResponse(true, messageService.message("Success", true));
     } else {
       return ResponseMessageUtils.makeResponse(true, messageService.message("Fail", false));
     }
   } catch (Exception error) {
     /*System Activity*/
     LocalTime endDuration = LocalTime.now();
     activityLogService.insert("/role/add",line, error.toString(),"System Role","System Role (Add)","Add",2,"Error",startDuration,endDuration, httpServletRequest);
     return ResponseMessageUtils.makeResponse(true, messageService.message("Error", null, false));
   }
  }

  public ResponseMessage<BaseResult> update(RoleDataUpdate roleDataUpdate, BindingResult bindingResult, HttpServletRequest httpServletRequest) throws UnknownHostException {
    LocalTime startDuration = LocalTime.now();
    Long line = 1033L;
    try {
      // Check Permission
      Long userId = userService.getUserAuth().getId();
      if (permissionMapper.checkPermission(userId, "System Role (Edit)") == 0) {
        return ResponseMessageUtils.makeResponseByPermission(true, messageService.message("No Permission access.", false));
      }

      // Check Validation
      if (bindingResult.hasErrors()) {
        return ResponseMessageUtils.makeResponse(true, bindingResult);
      }

      // Check Required field
      if (roleDataUpdate.getId() == null || roleDataUpdate.getId() == 0 || roleDataUpdate.getName().isEmpty() || roleDataUpdate.getName() == null) {
        return ResponseMessageUtils.makeResponse(true, messageService.message("Required", false));
      }

      // Check Duplicate
      if(roleMapper.checkDuplicate(roleDataUpdate.getName(), roleDataUpdate.getId()) > 0){
        return ResponseMessageUtils.makeResponse(true, messageService.message("Duplicate", false));
      }

      // Check Data
      Role role = new Role();
      role.setId(roleDataUpdate.getId());
      role.setName(roleDataUpdate.getName());
      role.setIsActive(1);
      role.setModifiedBy(userService.getUserAuth().getId());

      Boolean result = roleMapper.update(role);
      if (result) {
        //Insert User and Module
        insertRoleUser(roleDataUpdate.getUserList(), role.getId());
        insertRolePermission(roleDataUpdate.getModuleList(), role.getId());

        /*System Activity*/
        LocalTime endDuration = LocalTime.now();
        activityLogService.insert("/role/update",null,null,"System Role","System Role (Edit)","Edit",1,"Success",startDuration,endDuration, httpServletRequest);
        return ResponseMessageUtils.makeResponse(true, messageService.message("Success", true));
      } else {
        return ResponseMessageUtils.makeResponse(true, messageService.message("Fail", false));
      }
    } catch (Exception error) {
      /*System Activity*/
      LocalTime endDuration = LocalTime.now();
      activityLogService.insert("/role/update",line, error.toString(),"System Role","System Role (Edit)","Edit",2,"Error",startDuration,endDuration, httpServletRequest);
      return ResponseMessageUtils.makeResponse(true, messageService.message("Error", null, false));
    }
  }

  public ResponseMessage<BaseResult> delete(Long id, HttpServletRequest httpServletRequest) throws UnknownHostException {
    LocalTime startDuration = LocalTime.now();
    Long line = 1033L;
    try {
      // Check Permission
      Long userId = userService.getUserAuth().getId();
      if (permissionMapper.checkPermission(userId, "System Role (Delete)") == 0) {
        return ResponseMessageUtils.makeResponseByPermission(true, messageService.message("No Permission access.", false));
      }

      Boolean result = roleMapper.delete(id);
      if (result) {
        /*System Activity*/
        LocalTime endDuration = LocalTime.now();
        activityLogService.insert("/role/delete/{id}",null,null,"System Role","System Role (Delete)","Delete",1,"Success",startDuration,endDuration, httpServletRequest);
        return ResponseMessageUtils.makeResponse(true, messageService.message("Success", true));
      } else {
        return ResponseMessageUtils.makeResponse(true, messageService.message("Fail", false));
      }
    } catch (Exception error) {
      /*System Activity*/
      LocalTime endDuration = LocalTime.now();
      activityLogService.insert("/role/delete/{id}",line, error.toString(),"System Role","System Role (Delete)","Delete",2,"Error",startDuration,endDuration, httpServletRequest);
      return ResponseMessageUtils.makeResponse(true, messageService.message("Error", null, false));
    }
  }

  //Menu System Role
  public ResponseMessage<BaseResult> menu(HttpServletRequest httpServletRequest) throws UnknownHostException {
    LocalTime startDuration = LocalTime.now();
    Long line = 1033L;
    try {
      Long userId = userService.getUserAuth().getId();
      List<ModuleType> roleList = roleMapper.getModule(userId);

      if(roleList != null && roleList.size() > 0){
        for (ModuleType moduleType : roleList) {
          Long moduleTypeId = moduleType.getId();
          moduleType.setModuleList(moduleMapper.getOneByUserId(moduleTypeId, userId));
        }
      }

      /*System Activity*/
      LocalTime endDuration = LocalTime.now();
      activityLogService.insert("/role/menu",null,null,"System Role","","",1,"Success",startDuration,endDuration, httpServletRequest);
      return ResponseMessageUtils.makeResponse(true, messageService.message("Success", roleList,true));
    } catch (Exception error) {
      /*System Activity*/
      LocalTime endDuration = LocalTime.now();
      activityLogService.insert("/role/menu",line, error.toString(),"System Role","","",2,"Error",startDuration,endDuration, httpServletRequest);
      return ResponseMessageUtils.makeResponse(true, messageService.message("Error", null, false));
    }
  }

  private void insertRoleUser(List<UserList> userLists, Long roleId){
    if(userLists != null){
      roleMapper.deleteRoleUser(roleId);
      for (UserList userList : userLists) {
        roleMapper.insertRoleUser(roleId, userList.getUserId());
      }
    }
  }

  private void insertRolePermission(List<ModuleList> moduleLists, Long roleId){
    if(moduleLists != null){
      permissionMapper.delete(roleId);
      for (ModuleList moduleList : moduleLists) {
        permissionMapper.insert(roleId, moduleList.getModuleId());
      }
    }
  }

}