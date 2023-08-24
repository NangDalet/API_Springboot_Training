package com.ut.masterCode.model.response.Form;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class SubSubFormResponse {
    @ApiModelProperty(position = 1)
    private Long id;
    @ApiModelProperty(position = 2)
    private String name;
}
