package com.ut.masterCode.model.base;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class SmsSetting {

  @ApiModelProperty(position = 1)
  private String tc;

  @ApiModelProperty(position = 2)
  private String sc;

}