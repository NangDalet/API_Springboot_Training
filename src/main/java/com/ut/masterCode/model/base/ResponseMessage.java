package com.ut.masterCode.model.base;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class ResponseMessage<T> {

  @ApiModelProperty(position = 1)
  private ResponseHeader header;

  @ApiModelProperty(position = 2)
  private T body;

  public ResponseHeader getHeader() {
    if (header == null) {
      header = new ResponseHeader();
    }
    return header;
  }

}