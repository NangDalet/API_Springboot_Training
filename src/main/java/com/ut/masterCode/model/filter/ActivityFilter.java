package com.ut.masterCode.model.filter;

import com.ut.masterCode.model.base.Filter;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class ActivityFilter extends Filter {

    @ApiModelProperty(position = 10)
    private String startDate;

    @ApiModelProperty(position = 11)
    private String endDate;

    @ApiModelProperty(position = 12)
    private Long moduleId;

    @ApiModelProperty(position = 13)
    private Long userId;

    @ApiModelProperty(position = 14)
    private Long status;

}
