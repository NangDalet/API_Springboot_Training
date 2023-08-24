package com.ut.masterCode.model.request.Form;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
public class FormUpdateRequest {
    @ApiModelProperty(position = 1)
    private Long id;

    @ApiModelProperty(position = 2)
    private String name;

    @ApiModelProperty(position = 3)
    private Long formType;

    @ApiModelProperty(position = 4)
    private List<SubFormRequest> subFormRequests;
}
