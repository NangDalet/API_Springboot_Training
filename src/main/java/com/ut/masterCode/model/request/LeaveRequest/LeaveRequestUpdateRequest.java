package com.ut.masterCode.model.request.LeaveRequest;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;
@Data
public class LeaveRequestUpdateRequest {
    @ApiModelProperty(position = 1)
    private Long id;

    @ApiModelProperty(position = 2)
    private Long employeeId;

    @ApiModelProperty(position = 3)
    private String reason;

    @ApiModelProperty(position = 4)
    private List<String> requestDate;
}
