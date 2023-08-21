package com.ut.masterCode.mapper.primary;

import com.ut.masterCode.model.ModuleType;
import com.ut.masterCode.model.Role;
import com.ut.masterCode.model.RoleUser;
import com.ut.masterCode.model.base.Filter;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleMapper {

  List<Role> getList(@Param("filter") Filter filter);

  Long countList(@Param("filter") Filter filter);

  List<Role> getOne(@Param("id") Long id);

  List<RoleUser> getUser(@Param("id") Long id);

  Long checkDuplicate(@Param("name") String name, @Param("id") Long id);

  Boolean insert(@Param("role") Role role);

  Boolean update(@Param("role") Role role);

  Boolean delete(@Param("id") Long id);

  List<ModuleType> getModule(@Param("userId") Long userId);

  List<ModuleType> getSettingModule(@Param("userId") Long userId);

  Boolean insertUserRole(@Param("userId") Long userId, @Param("roleId") Long roleId);

  Boolean insertRoleUser(@Param("roleId") Long roleId, @Param("userId") Long userId);

  Boolean deleteRoleUser(@Param("roleId") Long roleId);

}