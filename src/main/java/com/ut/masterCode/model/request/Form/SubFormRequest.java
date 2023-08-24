package com.ut.masterCode.model.request.Form;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
public class SubFormRequest {

    @ApiModelProperty(position = 1)
    private Long id;

    @ApiModelProperty(position = 2, hidden = true)
    private Long formId;

    @ApiModelProperty(position = 3)
    private String name;

    @ApiModelProperty(position = 4)
    private Long formTypeId;

    @ApiModelProperty(position = 5, hidden = true)
    private String remarks;

    @ApiModelProperty(position = 6)
    private Long status;

    @ApiModelProperty(position = 7)
    private Long isDeleted;

    @ApiModelProperty(position = 8)
    private Float ordering;

    @ApiModelProperty(position = 10)
    private List<SubSubFormRequest> subSubFormRequests;
}
