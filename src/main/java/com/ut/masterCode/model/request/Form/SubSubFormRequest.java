package com.ut.masterCode.model.request.Form;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class SubSubFormRequest {
    @ApiModelProperty(position = 1)
    private Long id;

    @ApiModelProperty(position = 2, hidden = true)
    private Long subFormId;

    @ApiModelProperty(position = 3)
    private String name;

    @ApiModelProperty(position = 4)
    private Long isDeleted;
}
