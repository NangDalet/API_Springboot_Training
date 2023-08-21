package com.ut.masterCode.mapper.primary;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PermissionMapper {

  Long checkPermission(@Param("userId") Long userId, @Param("moduleName") String moduleName);

  Boolean insert(@Param("roleId") Long roleId, @Param("moduleId") Long moduleId);

  Boolean delete(@Param("roleId") Long roleId);

}