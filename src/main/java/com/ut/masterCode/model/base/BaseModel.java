package com.ut.masterCode.model.base;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class BaseModel {

  @ApiModelProperty(position = 101, hidden = true)
  private String created;

  @ApiModelProperty(position = 102, hidden = true)
  private Long createdBy;

  @ApiModelProperty(position = 103, hidden = true)
  private String modified;

  @ApiModelProperty(position = 104, hidden = true)
  private Long modifiedBy;

  @ApiModelProperty(position = 105, hidden = true)
  private int isActive;

  @ApiModelProperty(position = 106, hidden = true)
  private int status;

}