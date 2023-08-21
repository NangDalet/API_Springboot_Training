package com.ut.masterCode.model.notification;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class ApnsProvider implements Serializable {

  private static final long serialVersionUID = 1L;

  @ApiModelProperty(position = 1)
  private Long id;

  @ApiModelProperty(position = 2)
  private String name;

  @ApiModelProperty(position = 3)
  private String apiUrl;

  @ApiModelProperty(position = 4)
  private String devKey;

  @ApiModelProperty(position = 5)
  private String prodKey;

}