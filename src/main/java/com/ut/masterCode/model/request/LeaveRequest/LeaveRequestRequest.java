package com.ut.masterCode.model.request.LeaveRequest;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;
@Data
public class LeaveRequestRequest {
    @ApiModelProperty(position = 1)
    private Long employeeId;

    @ApiModelProperty(position = 2)
    private String reason;

    @ApiModelProperty(position = 3)
    private List<String> requestDate;
}
