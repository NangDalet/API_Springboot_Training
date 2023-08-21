package com.ut.masterCode.mapper.primary;

import com.ut.masterCode.model.Employee;
import com.ut.masterCode.model.base.Filter;
import com.ut.masterCode.model.response.EmployeeResponse;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeMapper {
    Boolean insert(@Param("employee") Employee employee);

    Boolean update(@Param("employee") Employee employee);

    Boolean delete(@Param("id") Long id);

    List<EmployeeResponse>list(@Param("filter") Filter filter);
    List<EmployeeResponse> getOne(@Param("id") Long id);

    Long checkDuplicate(@Param("employeeCode") String employeeCode);

    Long checkDuplicateUpdate(@Param("updateEmployeeCode") String updateEmployeeCode, @Param("id") Long id);
    Long checkTotal();
}
