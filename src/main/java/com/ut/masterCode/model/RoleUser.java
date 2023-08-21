package com.ut.masterCode.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class RoleUser {

  private static final long serialVersionUID = 1L;

  @ApiModelProperty(position = 1)
  private Long id;

  @ApiModelProperty(position = 2, hidden = true)
  private String username;

  @ApiModelProperty(position = 3, hidden = true)
  private Boolean checked;

}
