package com.ut.masterCode.model;

import com.ut.masterCode.model.base.BaseModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class Role extends BaseModel implements Serializable {

  private static final long serialVersionUID = 1L;

  @ApiModelProperty(position = 1)
  private Long id;

  @ApiModelProperty(position = 2)
  private String name;

  @ApiModelProperty(position = 2)
  private String Username;

  @ApiModelProperty(position = 2)
  private String modifiedDate;

  @ApiModelProperty(position = 3)
  private List<RoleUser> userList;

  @ApiModelProperty(position = 4)
  private List<ModuleType> moduleTypeList;
}
