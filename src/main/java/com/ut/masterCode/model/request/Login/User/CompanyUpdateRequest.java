package com.ut.masterCode.model.request.Login.User;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class CompanyUpdateRequest {
    @ApiModelProperty(position = 1)
    private Long id;
    @ApiModelProperty(position = 2)
    private String name;
    @ApiModelProperty(position = 3)
    private String nameOfStaff;
    @ApiModelProperty(position = 4)
    private String skill;
    @ApiModelProperty(position = 5)
    private String salary;
}
