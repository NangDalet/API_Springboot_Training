package com.ut.masterCode.mapper.primary;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface AuthMapper {

    Boolean insertClientId(@Param("clientId") String clientId, @Param("clientName") String clientName, @Param("clientSecure") String clientSecure, @Param("tokenValidate") int tokenValidate, @Param("refreshTokenValidate") int refreshTokenValidate);

    Long checkUserValid(@Param("username") String username);

    Long checkStatusUserLogin(@Param("username") String username);

    String checkUserLogout(@Param("username") String username, @Param("clientId") String clientId);

    Boolean clearUserToken(@Param("username") String username, @Param("clientId") String clientId, @Param("tokenId") String tokenId);

}