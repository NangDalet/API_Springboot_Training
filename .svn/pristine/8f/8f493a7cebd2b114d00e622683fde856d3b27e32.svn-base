package com.ut.masterCode.model.notification;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class DeviceToken {

  @ApiModelProperty(position = 1)
  private DeviceType type;

  @ApiModelProperty(position = 2)
  private String token;

  public enum DeviceType {
    ANDROID,
    IOS,
    WEB
  }

}
