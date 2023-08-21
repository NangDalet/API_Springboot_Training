package com.ut.masterCode.model;

import com.ut.masterCode.model.base.BaseModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class NotificationOnMobile extends BaseModel {

    @ApiModelProperty(position = 1)
    private Long id;

    @ApiModelProperty(position = 2)
    private Long notificationType;

    @ApiModelProperty(position = 3)
    private String message;

    @ApiModelProperty(position = 4)
    private Long patientId;

    @ApiModelProperty(position = 5)
    private Long doctorId;

}
