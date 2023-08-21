package com.ut.masterCode.model.base;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;

@Data
public class Filter {

  @ApiModelProperty(position = 1)
  private int page;

  @ApiModelProperty(position = 2)
  private int rowsPerPage;

  @ApiModelProperty(position = 3)
  private String orderBy;

  @ApiModelProperty(position = 4)
  private String searchText;

}
