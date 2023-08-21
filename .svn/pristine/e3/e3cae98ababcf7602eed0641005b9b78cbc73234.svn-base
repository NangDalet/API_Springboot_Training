package com.ut.masterCode.mapper.primary;

import com.ut.masterCode.model.ModuleType;
import com.ut.masterCode.model.base.Filter;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ModuleTypeMapper {

  List<ModuleType> getList(@Param("filter") Filter filter);

  Long countList(@Param("filter") Filter filter);

  List<ModuleType> getOne(@Param("id") Long id);

}