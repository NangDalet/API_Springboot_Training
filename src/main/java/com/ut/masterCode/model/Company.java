package com.ut.masterCode.model;

import com.ut.masterCode.model.base.BaseModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class Company extends BaseModel {
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
