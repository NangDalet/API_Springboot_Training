package com.ut.masterCode.model.request.Login.User;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class EditProfileRequest {

    @ApiModelProperty(position = 1)
    private Long employeeId;

    @ApiModelProperty(position = 2)
    private String photo;

    @ApiModelProperty(position = 2)
    private Long photoType;

    @ApiModelProperty(position = 2)
    private String employeeName;

    @ApiModelProperty(position = 3)
    private String employeeGender;

    @ApiModelProperty(position = 4)
    private String employeeDob;

    @ApiModelProperty(position = 5)
    private String employeePhoneNumber;

    @ApiModelProperty(position = 6)
    private String employeeEmail;

    @ApiModelProperty(position = 7)
    private Long provinceId;

    @ApiModelProperty(position = 8)
    private Long districtId;

    @ApiModelProperty(position = 9)
    private Long communeId;

    @ApiModelProperty(position = 10)
    private Long villageId;

}
