package com.ut.masterCode.mapper.primary;

import com.ut.masterCode.model.Form;
import com.ut.masterCode.model.base.Filter;
import com.ut.masterCode.model.request.Form.SubFormRequest;
import com.ut.masterCode.model.request.Form.SubSubFormRequest;
import com.ut.masterCode.model.response.Form.FormResponse;
import com.ut.masterCode.model.response.Form.SubFormResponse;
import com.ut.masterCode.model.response.Form.SubSubFormResponse;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FormMapper {
    List<FormResponse> getList(@Param("filter") Filter filter);
    Long countList(@Param("filter") Filter filter);
    List<FormResponse> getOne(@Param("id") Long id);
    List<SubFormResponse> getListSubForm(@Param("formId") Long formId);
    List<SubSubFormResponse> getListSubSubForm(@Param("subFormId") Long subFormId);
    Long checkDuplicate(@Param("name") String name, @Param("id") Long id);
    Boolean insert(@Param("form") Form form);
    Boolean insertSubForm(@Param("subFormRequest") SubFormRequest subFormRequest);
    Boolean insertSubSubForm(@Param("subSubFormRequest") SubSubFormRequest subSubFormRequest);
    Boolean update(@Param("form") Form form);
    Boolean delete(@Param("id") Long id);
    Boolean deleteSubFormId(@Param("id") Long id);
    Boolean deleteSubSubFormId(@Param("id") Long id);
}
