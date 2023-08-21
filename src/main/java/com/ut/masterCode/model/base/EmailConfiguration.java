package com.ut.masterCode.model.base;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@EqualsAndHashCode(callSuper = true)
@Data
public class EmailConfiguration extends BaseModel implements Serializable {

  @ApiModelProperty(position = 1)
  private Long id;

  @ApiModelProperty(position = 2)
  private String email;

  @ApiModelProperty(position = 3)
  private Long type;

}