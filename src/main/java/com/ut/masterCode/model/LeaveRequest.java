package com.ut.masterCode.model;

import com.ut.masterCode.model.base.BaseModel;
import com.ut.masterCode.model.response.LeaveRequest.LeaveRequestDateResponse;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.List;
@Data
@EqualsAndHashCode(callSuper = true)
public class LeaveRequest extends BaseModel {
    @ApiModelProperty(position = 1)
    private Long id;

    @ApiModelProperty(position = 2)
    private Long employeeId;

    @ApiModelProperty(position = 2)
    private String employeeCode;

    @ApiModelProperty(position = 3)
    private String employeeName;

    @ApiModelProperty(position = 9)
    private String reason;

    @ApiModelProperty(position = 26)
    private List<LeaveRequestDateResponse> requestDate;

}
