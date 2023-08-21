package com.ut.masterCode.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class Module {

  private static final long serialVersionUID = 1L;

  @ApiModelProperty(position = 1)
  private Long id;

  @ApiModelProperty(position = 2)
  private String name;

  @ApiModelProperty(position = 3)
  private String type;

  @ApiModelProperty(position = 4)
  private Boolean checked;

}
