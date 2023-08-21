package com.ut.masterCode.model.base;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
public class ResponseHeader {

  @ApiModelProperty(position = 1)
  private Long serverTimestamp;

  @ApiModelProperty(position = 2)
  private Boolean result;

  @ApiModelProperty(position = 3)
  private Integer statusCode;

  @ApiModelProperty(position = 4)
  private String errorCode;

  @ApiModelProperty(position = 5)
  private String errorText;

  @ApiModelProperty(position = 6)
  private String token;

  @ApiModelProperty(position = 7)
  private Pagination pagination;

  public ResponseHeader() {
    Date date = new Date();
    Long time = date.getTime();
    this.serverTimestamp = time;
  }

}