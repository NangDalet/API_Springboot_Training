package com.ut.masterCode.model.response.Notification;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class NotificationResponse {

    @ApiModelProperty(position = 1)
    private Long id;

    @ApiModelProperty(position = 2)
    private Long notificationType;

    @ApiModelProperty(position = 3)
    private String message;

    @ApiModelProperty(position = 4)
    private Long patientId;

    @ApiModelProperty(position = 5)
    private String patientName;

    @ApiModelProperty(position = 6)
    private Long doctorId;

    @ApiModelProperty(position = 7)
    private String doctorName;

    @ApiModelProperty(position = 8)
    private String createdDate;

    @ApiModelProperty(position = 10)
    private String photo;

    @ApiModelProperty(position = 10)
    private Long photoType;

}
