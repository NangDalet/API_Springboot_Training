package com.ut.masterCode.mapper.primary;


import com.ut.masterCode.model.NotificationOnMobile;
import com.ut.masterCode.model.UserDeviceNotification;
import com.ut.masterCode.model.base.Filter;
import com.ut.masterCode.model.response.Notification.NotificationResponse;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationMapper {

  List<NotificationResponse> getList(@Param("filter") Filter filter);

  Long countList(@Param("filter") Filter filter);

  Boolean insertNotification(@Param("notification") NotificationOnMobile notification);

  Boolean readAll(@Param("userId") Long userId);

  Boolean insertUserDeviceToken(@Param("userDeviceNotification") UserDeviceNotification userDeviceNotification);

  Long checkDuplicate(@Param("userId") Long userId,@Param("deviceId") String deviceId, @Param("deviceToken") String deviceToken);

  List<String> getDeviceToken();

}