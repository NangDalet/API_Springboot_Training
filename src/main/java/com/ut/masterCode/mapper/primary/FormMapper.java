package com.ut.masterCode.mapper.primary;

import com.ut.masterCode.model.Form;
import com.ut.masterCode.model.request.Form.SubFormRequest;
import com.ut.masterCode.model.request.Form.SubSubFormRequest;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface FormMapper {
    Long checkDuplicate(@Param("name") String name, @Param("id") Long id);
    Boolean insert(@Param("form") Form form);
    Boolean insertSubForm(@Param("subFormRequest") SubFormRequest subFormRequest);
    Boolean insertSubSubForm(@Param("subSubFormRequest") SubSubFormRequest subSubFormRequest);
    Boolean update(@Param("form") Form form);
}
