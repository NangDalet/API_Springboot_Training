package com.ut.masterCode.mapper.primary;

import com.ut.masterCode.model.Company;
import com.ut.masterCode.model.base.Filter;
import com.ut.masterCode.model.response.CompanyResponse;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface CompanyMapper {
    Boolean insert(@Param("company")Company company);
    Boolean update(@Param("company")Company company);
    Boolean delete(@Param("id")Long id);
    List<CompanyResponse>list(@Param("filter")Filter filter);
    List<CompanyResponse>getOne(@Param("id")Long id);
    Long checkDuplicate(@Param("name")String name);
    Long checkDuplicateUpdate(@Param("updateName")String updateName,@Param("id")Long id);
    Long checkTotal();
}
