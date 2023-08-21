package com.ut.masterCode.service;

import com.ut.masterCode.base.UserAuthSession;
import com.ut.masterCode.helper.ResponseMessageUtils;
import com.ut.masterCode.mapper.primary.LeaveRequestMapper;
import com.ut.masterCode.mapper.primary.PermissionMapper;
import com.ut.masterCode.mapper.primary.ProductMapper;
import com.ut.masterCode.mapper.primary.UserMapper;
import com.ut.masterCode.model.Company;
import com.ut.masterCode.model.Employee;
import com.ut.masterCode.model.LeaveRequest;
import com.ut.masterCode.model.MessageService;
import com.ut.masterCode.model.base.BaseResult;
import com.ut.masterCode.model.base.Filter;
import com.ut.masterCode.model.base.Pagination;
import com.ut.masterCode.model.base.ResponseMessage;
import com.ut.masterCode.model.filter.LeaveRequestFilter;
import com.ut.masterCode.model.request.LeaveRequest.LeaveRequestDetailRequest;
import com.ut.masterCode.model.request.LeaveRequest.LeaveRequestRequest;
import com.ut.masterCode.model.request.LeaveRequest.LeaveRequestUpdateRequest;
import com.ut.masterCode.model.response.EmployeeResponse;
import com.ut.masterCode.model.response.LeaveRequest.LeaveRequestDateResponse;
import com.ut.masterCode.model.response.LeaveRequest.LeaveRequestResponse;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import java.net.UnknownHostException;
import java.time.LocalTime;
import java.util.List;

@Service
public class LeaveRequestServiceIml implements LeaveRequestService {
    @Autowired
    private LeaveRequestMapper leaveRequestMapper;

    @Autowired
    private UserService userService;

    @Autowired
    private PermissionMapper permissionMapper;

    @Autowired
    private MessageService messageService;

    @Autowired
    private ActivityLogService activityLogService;

    @Autowired
    Environment environment;

    @Override
    public ResponseMessage<BaseResult> getList(LeaveRequestFilter filter, HttpServletRequest httpServletRequest) throws UnknownHostException {
        LocalTime startDuration = LocalTime.now();
        Long line = 1033L;
        try {
            // Check Permission
            Long userId = userService.getUserAuth().getId();
            //if (permissionMapper.checkPermission(userId, "Leave Request (View)") == 0) {
            //return ResponseMessageUtils.makeResponseByPermission(true, messageService.message("No Permission access.", false));
            //}

            Pagination pagination = new Pagination();
            pagination.setPage(filter.getPage());
            pagination.setRowsPerPage(filter.getRowsPerPage());
            pagination.setTotal((long) leaveRequestMapper.countList(filter).size());
            filter.setPage((filter.getPage() - 1) * filter.getRowsPerPage());

            List<LeaveRequestResponse> leaveRequestResponses = leaveRequestMapper.getList(filter);

            if (leaveRequestResponses.size() > 0) {
                for (int i = 0; i < leaveRequestResponses.size(); i++) {
                    //! Get Leave Request Id
                    Long leaveRequestId = leaveRequestResponses.get(i).getId();
                    //! Get leave request date
                    List<LeaveRequestDateResponse> leaveRequestDateResponses = leaveRequestMapper.getLeaveRequestDateList(leaveRequestId);
                    leaveRequestResponses.get(i).setRequestDate(leaveRequestDateResponses);
                }
            }

            /*System Activity*/
            LocalTime endDuration = LocalTime.now();
            activityLogService.insert("/leave-request/list", null, null, "Leave Request", "Leave Request (View)", "View", 1, "Success", startDuration, endDuration, httpServletRequest);
            return ResponseMessageUtils.makeResponse(true, messageService.message("Success", leaveRequestResponses, pagination, true));
        } catch (Exception error) {
            System.out.println(error);
            /*System Activity*/
            LocalTime endDuration = LocalTime.now();
            activityLogService.insert("/leave-request/list", line, error.toString(), "Leave Request", "Leave Request (View)", "View", 2, "Error", startDuration, endDuration, httpServletRequest);
            return ResponseMessageUtils.makeResponse(true, messageService.message("Error", null, false));
        }
    }

    @Override
    public ResponseMessage<BaseResult> insert(LeaveRequestRequest leaveRequestRequest, HttpServletRequest httpServletRequest) throws UnknownHostException {
        LocalTime startDuration = LocalTime.now();
        Long line = 1033L;
        try {
            // Check Permission
            Long userId = userService.getUserAuth().getId();
            // Check Data
            LeaveRequest leaveRequest = new LeaveRequest();
            leaveRequest.setEmployeeId(leaveRequestRequest.getEmployeeId());
            leaveRequest.setReason(leaveRequestRequest.getReason());
            leaveRequest.setCreatedBy(userId);
            leaveRequest.setIsActive(1);

            Boolean result = leaveRequestMapper.insert(leaveRequest);

            if (result) {
                //! Check leave request detail
                if (leaveRequestRequest.getRequestDate().size() > 0) {
                    for (int i = 0; i < leaveRequestRequest.getRequestDate().size(); i++) {
                        //! Check data before insert
                        LeaveRequestDetailRequest leaveRequestDetail = new LeaveRequestDetailRequest();
                        leaveRequestDetail.setLeaveRequestId(leaveRequest.getId());
                        leaveRequestDetail.setDate(leaveRequestRequest.getRequestDate().get(i));
                        leaveRequestDetail.setCreatedBy(userId);
                        leaveRequestMapper.insertLeaveRequestDetail(leaveRequestDetail);
                    }
                }
                /*System Activity*/
                LocalTime endDuration = LocalTime.now();
                activityLogService.insert("/leave-request/add", null, null, "Leave Request", "Leave Request (Add)", "Add", 1, "Success", startDuration, endDuration, httpServletRequest);
                return ResponseMessageUtils.makeResponse(true, messageService.message("Success", true));
            } else {
                return ResponseMessageUtils.makeResponse(true, messageService.message("Fail", false));
            }
        } catch (Exception error) {
            System.out.println(error);
            /*System Activity*/
            LocalTime endDuration = LocalTime.now();
            activityLogService.insert("/leave-request/add", line, error.toString(), "Leave Request", "Leave Request (Add)", "Add", 2, "Error", startDuration, endDuration, httpServletRequest);
            return ResponseMessageUtils.makeResponse(true, messageService.message("Error", null, false));
        }
    }

    @Override
    public ResponseMessage<BaseResult> update(LeaveRequestUpdateRequest leaveRequestUpdateRequest, HttpServletRequest httpServletRequest) throws UnknownHostException {
        LocalTime startDuration = LocalTime.now();
        Long line = 1033L;
        try {
            // Check Permission
            Long userId = userService.getUserAuth().getId();
            //if (permissionMapper.checkPermission(userId, "Leave Request (Edit)") == 0) {
                //return ResponseMessageUtils.makeResponseByPermission(true, messageService.message("No Permission access.", false));
            //}

            // Check Data
            LeaveRequest leaveRequest = new LeaveRequest();
            leaveRequest.setId(leaveRequestUpdateRequest.getId());
            leaveRequest.setEmployeeId(leaveRequestUpdateRequest.getEmployeeId());
            leaveRequest.setReason(leaveRequestUpdateRequest.getReason());
            leaveRequest.setModifiedBy(userId);
            leaveRequest.setIsActive(1);

            Boolean result = leaveRequestMapper.update(leaveRequest);

            if (result) {
                //! delete leave request detail by id
                leaveRequestMapper.deleteLeaveRequestDetail(leaveRequest.getId(), userId);

                //! Check leave request detail
                if(leaveRequestUpdateRequest.getRequestDate().size() > 0) {
                    for (int i = 0; i < leaveRequestUpdateRequest.getRequestDate().size(); i++){
                        //! Check data before insert
                        LeaveRequestDetailRequest leaveRequestDetail = new LeaveRequestDetailRequest();
                        leaveRequestDetail.setLeaveRequestId(leaveRequest.getId());
                        leaveRequestDetail.setDate(leaveRequestUpdateRequest.getRequestDate().get(i));
                        leaveRequestDetail.setCreatedBy(userId);
                        leaveRequestMapper.insertLeaveRequestDetail(leaveRequestDetail);
                    }
                }

                /*System Activity*/
                LocalTime endDuration = LocalTime.now();
                activityLogService.insert("/leave-request/update",null,null,"Leave Request","Leave Request (Update)","Update",1,"Success",startDuration,endDuration, httpServletRequest);
                return ResponseMessageUtils.makeResponse(true, messageService.message("Success", true));
            } else {
                return ResponseMessageUtils.makeResponse(true, messageService.message("Fail", false));
            }
        } catch (Exception error) {
            /*System Activity*/
            LocalTime endDuration = LocalTime.now();
            activityLogService.insert("/leave-request/update",line, error.toString(),"Leave Request","Leave Request (Update)","Update",2,"Error",startDuration,endDuration, httpServletRequest);
            return ResponseMessageUtils.makeResponse(true, messageService.message("Error", null, false));
        }
    }

    @Override
    public ResponseMessage<BaseResult> delete(Long id, HttpServletRequest httpServletRequest) throws UnknownHostException {
        Long userId = userService.getUserAuth().getId();

        Boolean result = leaveRequestMapper.delete(id, userId);
        if (result) {
            return ResponseMessageUtils.makeResponse(true, messageService.message("Success", true));
        } else {
            return ResponseMessageUtils.makeResponse(true, messageService.message("Fail", false));
        }
    }

    @Override
    public ResponseMessage<BaseResult> getOne(Long id, HttpServletRequest httpServletRequest) throws UnknownHostException {
        LocalTime startDuration = LocalTime.now();
        Long line = 1033L;
        try {
            // Check Permission
            Long userId = userService.getUserAuth().getId();
            //if (permissionMapper.checkPermission(userId, "Leave Request (View)") == 0) {
            //return ResponseMessageUtils.makeResponseByPermission(true, messageService.message("No Permission access.", false));
            //}

            List<LeaveRequestResponse> leaveRequestResponses = leaveRequestMapper.getOne(id);

            if (leaveRequestResponses.size() > 0) {
                for (int i = 0; i < leaveRequestResponses.size(); i++) {
                    //! Get Leave Request Id
                    Long leaveRequestId = leaveRequestResponses.get(i).getId();
                    //! Get leave request date
                    List<LeaveRequestDateResponse> leaveRequestDateResponses = leaveRequestMapper.getLeaveRequestDate(leaveRequestId);
                    leaveRequestResponses.get(i).setRequestDate(leaveRequestDateResponses);
                }
            }
            /*System Activity*/
            LocalTime endDuration = LocalTime.now();
            activityLogService.insert("/leave-request/find/{id}", null, null, "Leave Request", "Leave Request (View)", "View", 1, "Success", startDuration, endDuration, httpServletRequest);
            return ResponseMessageUtils.makeResponse(true, messageService.message("Success", leaveRequestResponses, true));
        } catch (Exception error) {
            /*System Activity*/
            LocalTime endDuration = LocalTime.now();
            activityLogService.insert("/leave-request/find/{id}", line, error.toString(), "Leave Request", "Leave Request (View)", "View", 2, "Error", startDuration, endDuration, httpServletRequest);
            return ResponseMessageUtils.makeResponse(true, messageService.message("Error", null, false));
        }
    }
}
