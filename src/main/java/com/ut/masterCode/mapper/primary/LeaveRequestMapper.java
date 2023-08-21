package com.ut.masterCode.mapper.primary;

import com.ut.masterCode.model.LeaveRequest;
import com.ut.masterCode.model.Product;
import com.ut.masterCode.model.base.BaseResult;
import com.ut.masterCode.model.base.Filter;
import com.ut.masterCode.model.base.ResponseMessage;
import com.ut.masterCode.model.filter.LeaveRequestFilter;
import com.ut.masterCode.model.request.LeaveRequest.LeaveRequestDetailRequest;
import com.ut.masterCode.model.request.LeaveRequest.LeaveRequestUpdateRequest;
import com.ut.masterCode.model.response.CompanyResponse;
import com.ut.masterCode.model.response.EmployeeResponse;
import com.ut.masterCode.model.response.LeaveRequest.LeaveRequestDateResponse;
import com.ut.masterCode.model.response.LeaveRequest.LeaveRequestResponse;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import javax.servlet.http.HttpServletRequest;
import java.net.UnknownHostException;
import java.util.Collection;
import java.util.List;

@Repository
public interface LeaveRequestMapper {
    List<LeaveRequestResponse> getList(@Param("filter") LeaveRequestFilter filter);

    List<Long> countList(@Param("filter") LeaveRequestFilter filter);

    List<LeaveRequestResponse> getOne(@Param("id") Long id);

    Boolean insert(@Param("leaveRequest") LeaveRequest leaveRequest);

    Boolean update(@Param("leaveRequest") LeaveRequest leaveRequest);

    Boolean delete(@Param("id") Long id, @Param("userId") Long userId);

    //! Update leave request detail
    List<LeaveRequestDateResponse> getLeaveRequestDate(@Param("leaveRequestId") Long leaveRequestId);

    List<LeaveRequestDateResponse> getLeaveRequestDateList(@Param("leaveRequestId") Long leaveRequestId);

    Boolean insertLeaveRequestDetail(@Param("leaveRequestDetail") LeaveRequestDetailRequest leaveRequestDetail);

    Boolean deleteLeaveRequestDetail(@Param("leaveRequestId") Long leaveRequestId, @Param("userId") Long userId);
}
