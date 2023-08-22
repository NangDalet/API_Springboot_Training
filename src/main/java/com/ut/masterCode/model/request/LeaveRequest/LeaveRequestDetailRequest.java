package com.ut.masterCode.model.request.LeaveRequest;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class LeaveRequestDetailRequest {
    @ApiModelProperty(position = 1, hidden = true)
    private Long leaveRequestId;

    @ApiModelProperty(position = 2)
    private String date;

    @ApiModelProperty(position = 3, hidden = true)
    private Long createdBy;

    @ApiModelProperty(position = 2, hidden = true)
    private Long modifiedBy;
}
