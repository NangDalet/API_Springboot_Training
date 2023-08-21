package com.ut.masterCode.model.Users;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class UserBilling {

  @ApiModelProperty(position = 1)
  private String sysCode;

  @ApiModelProperty(position = 2)
  private String firstName;

  @ApiModelProperty(position = 3)
  private String lastName;

  @ApiModelProperty(position = 4)
  private String username;

  @ApiModelProperty(position = 5)
  private String password;

  @ApiModelProperty(position = 6)
  private String isActive;

}