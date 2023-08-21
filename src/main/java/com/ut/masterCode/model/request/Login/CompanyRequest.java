package com.ut.masterCode.model.request.Login;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class CompanyRequest {
    @ApiModelProperty(position = 2)
    private String name;
    @ApiModelProperty(position = 3)
    private String nameOfStaff;
    @ApiModelProperty(position = 4)
    private String skill;
    @ApiModelProperty(position = 5)
    private String salary;
}
