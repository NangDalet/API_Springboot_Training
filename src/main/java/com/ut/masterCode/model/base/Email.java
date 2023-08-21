package com.ut.masterCode.model.base;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class Email extends BaseModel implements Serializable {

  @ApiModelProperty(position = 1)
  private Long id;

  @ApiModelProperty(position = 2)
  private String host;

  @ApiModelProperty(position = 3)
  private Integer port;

  @ApiModelProperty(position = 4)
  private String username;

  @ApiModelProperty(position = 5)
  private String password;

}