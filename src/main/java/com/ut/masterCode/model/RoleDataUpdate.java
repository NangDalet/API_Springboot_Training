package com.ut.masterCode.model;

import com.ut.masterCode.model.Users.UserList;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
public class RoleDataUpdate {

  private static final long serialVersionUID = 1L;

  @ApiModelProperty(position = 1)
  private Long id;

  @ApiModelProperty(position = 2)
  private String Username;

  @ApiModelProperty(position = 2)
  private String name;

  @ApiModelProperty(position = 3)
  private List<UserList> userList;

  @ApiModelProperty(position = 4)
  private List<ModuleList> moduleList;

}
