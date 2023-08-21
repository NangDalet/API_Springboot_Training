package com.ut.masterCode.service;

import com.ut.masterCode.helper.ResponseMessageUtils;
import com.ut.masterCode.mapper.primary.ModuleMapper;
import com.ut.masterCode.mapper.primary.ModuleTypeMapper;
import com.ut.masterCode.model.MessageService;
import com.ut.masterCode.model.ModuleType;
import com.ut.masterCode.model.ModuleTypeFilter;
import com.ut.masterCode.model.base.BaseResult;
import com.ut.masterCode.model.base.Pagination;
import com.ut.masterCode.model.base.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.net.UnknownHostException;
import java.time.LocalTime;
import java.util.List;

@Service
public class ModuleTypeServiceImpl implements ModuleTypeService {

    @Autowired
    private ModuleTypeMapper moduleTypeMapper;

    @Autowired
    private ModuleMapper moduleMapper;

    @Autowired
    private MessageService messageService;

    @Autowired
    private UserService userService;

    @Autowired
    private ActivityLogService activityLogService;

    public ResponseMessage<BaseResult> getList(ModuleTypeFilter filter, HttpServletRequest httpServletRequest) throws UnknownHostException {
        LocalTime startDuration = LocalTime.now();
        Long line = 1033L;
        try {
            Long userId = userService.getUserAuth().getId();

            Pagination pagination = new Pagination();
            pagination.setPage(filter.getPage());
            pagination.setRowsPerPage(filter.getRowsPerPage());
            pagination.setTotal(moduleTypeMapper.countList(filter));

            filter.setPage((filter.getPage() - 1) * filter.getRowsPerPage());

            List<ModuleType> moduleTypeList = moduleTypeMapper.getList(filter);
            if (moduleTypeList != null) {
                for (ModuleType moduleType : moduleTypeList) {
                    Long id = moduleType.getId();
                    if (filter.getRoleId() == null || filter.getRoleId() == 0) {
                        moduleType.setModuleList(moduleMapper.getOne(id));
                    } else {
                        moduleType.setModuleList(moduleMapper.getOneByRoleId(id, filter.getRoleId()));
                    }
                }
            } /*System Activity*/
            LocalTime endDuration = LocalTime.now();
            activityLogService.insert("/module-type/list",null,null,"Moudle Type","Moudle Type (View)","View",1,"Success",startDuration,endDuration, httpServletRequest);
            return ResponseMessageUtils.makeResponse(true, messageService.message("Success", moduleTypeList, true));
        } catch (Exception error) {
            /*System Activity*/
            LocalTime endDuration = LocalTime.now();
            activityLogService.insert("/module-type/list",line, error.toString(),"Moudle Type","Moudle Type (View)","View",2,"Error",startDuration,endDuration, httpServletRequest);
            return ResponseMessageUtils.makeResponse(true, messageService.message("Error", null, false));
        }
    }

    public ResponseMessage<BaseResult> getOne(Long id, HttpServletRequest httpServletRequest) throws UnknownHostException {
        LocalTime startDuration = LocalTime.now();
        Long line = 1033L;
        try {
            List<ModuleType> moduleTypes = moduleTypeMapper.getOne(id);
            if (moduleTypes != null) {
                for (ModuleType moduleType : moduleTypes) {
                    moduleType.setModuleList(moduleMapper.getOne(id));
                }
            }
            /*System Activity*/
            LocalTime endDuration = LocalTime.now();
            activityLogService.insert("/module-type/find/{id}",null,null,"Moudle Type","Moudle Type (View)","View",1,"Success",startDuration,endDuration, httpServletRequest);
            return ResponseMessageUtils.makeResponse(true, messageService.message("Success", moduleTypes, true));
        } catch (Exception error) {
            /*System Activity*/
            LocalTime endDuration = LocalTime.now();
            activityLogService.insert("/module-type/find/{id}",line, error.toString(),"Moudle Type","Moudle Type (View)","View",2,"Error",startDuration,endDuration, httpServletRequest);
            return ResponseMessageUtils.makeResponse(true, messageService.message("Error", null, false));
        }
    }
}