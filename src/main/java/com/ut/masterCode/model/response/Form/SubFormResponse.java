package com.ut.masterCode.model.response.Form;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;
@Data
public class SubFormResponse {
    @ApiModelProperty(position = 1)
    private Long id;
    @ApiModelProperty(position = 2)
    private String name;
    @ApiModelProperty(position = 3)
    private Long formTypeId;
    @ApiModelProperty(position = 4)
    private String formTypeName;
    @ApiModelProperty(position = 5)
    private Long status;
    @ApiModelProperty(position = 6)
    private Float ordering;
    @ApiModelProperty(position = 10)
    private List<SubSubFormResponse> subSubFormResponses;
}
