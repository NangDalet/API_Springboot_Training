package com.ut.masterCode.model.lang;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class LangList implements Serializable {

  @ApiModelProperty(position = 1)
  private Long id;

  @ApiModelProperty(position = 2)
  private String variable;

  @ApiModelProperty(position = 3)
  private String lang;

  @ApiModelProperty(position = 4)
  private String value;

}