package com.ut.masterCode.mapper.primary;

import com.ut.masterCode.model.Module;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ModuleMapper {

  List<Module> getOne(@Param("id") Long id);

  List<Module> getOneByRoleId(@Param("id") Long id, @Param("roleId") Long roleId);

  List<Module> getOneByUserId(@Param("id") Long id, @Param("userId") Long userId);

}