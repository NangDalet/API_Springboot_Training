package com.ut.masterCode.model.Users;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class UserList {

  private static final long serialVersionUID = 1L;

  @ApiModelProperty(position = 1)
  private Long userId;

}
