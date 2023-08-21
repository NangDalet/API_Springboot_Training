package com.ut.masterCode.model.Billing;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class BillingResponseBody implements Serializable {

  @ApiModelProperty(position = 1)
  private int status;

  @ApiModelProperty(position = 2)
  private String message;

  @ApiModelProperty(position = 3)
  private String expired;

}