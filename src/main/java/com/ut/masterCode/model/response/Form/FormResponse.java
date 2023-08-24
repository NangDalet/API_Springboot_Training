package com.ut.masterCode.model.response.Form;

import com.ut.masterCode.model.base.BaseModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
public class FormResponse extends BaseModel {
    @ApiModelProperty(position = 1)
    private Long id;
    @ApiModelProperty(position = 2)
    private String name;
    @ApiModelProperty(position = 3)
    private Long formTypeId;
    @ApiModelProperty(position = 4)
    private String formTypeName;
    @ApiModelProperty(position = 5)
    private String createdByUser;
    @ApiModelProperty(position = 6)
    private Long isUsed;
    @ApiModelProperty(position = 10)
    private List<SubFormResponse> subFormResponses;
}
