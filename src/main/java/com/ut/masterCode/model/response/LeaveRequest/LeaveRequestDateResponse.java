package com.ut.masterCode.model.response.LeaveRequest;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
@Data
public class LeaveRequestDateResponse implements Serializable {
    @ApiModelProperty(position = 1, hidden = true)
    private Long leaveRequestId;

    @ApiModelProperty(position = 2)
    private String date;
}
