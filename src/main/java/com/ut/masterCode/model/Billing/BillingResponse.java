package com.ut.masterCode.model.Billing;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class BillingResponse implements Serializable {

  @ApiModelProperty(position = 1)
  private BillingResponseHeader header;

  @ApiModelProperty(position = 2)
  private BillingResponseBody body;

}