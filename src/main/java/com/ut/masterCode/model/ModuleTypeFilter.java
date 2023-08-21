package com.ut.masterCode.model;

import com.ut.masterCode.model.base.Filter;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class ModuleTypeFilter extends Filter {

  @ApiModelProperty(position = 101)
  private Long roleId;

}
