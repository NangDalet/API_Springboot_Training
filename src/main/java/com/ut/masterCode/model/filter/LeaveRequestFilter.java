package com.ut.masterCode.model.filter;

import com.ut.masterCode.model.base.Filter;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class LeaveRequestFilter extends Filter {
    @ApiModelProperty(position = 12)
    private String employeeCode;

    @ApiModelProperty(position = 13)
    private Long employeeId;

    @ApiModelProperty(position = 14)
    private String requestDate;
}
