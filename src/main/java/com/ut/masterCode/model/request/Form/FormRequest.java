package com.ut.masterCode.model.request.Form;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
public class FormRequest {
    @ApiModelProperty(position = 1)
    private String name;

    @ApiModelProperty(position = 2)
    private Long formType;

    @ApiModelProperty(position = 3)
    private List<SubFormRequest> subFormRequests;
}
