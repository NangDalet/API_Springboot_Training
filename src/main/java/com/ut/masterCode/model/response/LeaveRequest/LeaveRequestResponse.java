package com.ut.masterCode.model.response.LeaveRequest;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;
@Data
public class LeaveRequestResponse implements Serializable {

    @ApiModelProperty(position = 1)
    private Long id;

    @ApiModelProperty(position = 2)
    private Long employeeId;

    @ApiModelProperty(position = 3)
    private String employeeCode;

    @ApiModelProperty(position = 4)
    private String employeeName;

    @ApiModelProperty(position = 5)
    private String employeePhoto;

    @ApiModelProperty(position = 7)
    private Float numberOfDay;

    @ApiModelProperty(position = 9)
    private String reason;

    @ApiModelProperty(position = 11)
    private String created;

    @ApiModelProperty(position = 12)
    private String createdBy;

    @ApiModelProperty(position = 26)
    private List<LeaveRequestDateResponse> requestDate;

}
