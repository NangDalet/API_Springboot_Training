package com.ut.masterCode.model.Users;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class UserGroup implements Serializable {

  private static final long serialVersionUID = 1L;

  @ApiModelProperty(position = 1)
  private Long id;

  @ApiModelProperty(position = 2)
  private Long userId;

  @ApiModelProperty(position = 3)
  private Long groupId;

}