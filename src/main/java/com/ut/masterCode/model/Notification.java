package com.ut.masterCode.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class Notification {

    @ApiModelProperty(position = 1)
    private Long notificationType;

    @ApiModelProperty(position = 2)
    private Long patientId;

    @ApiModelProperty(position = 3)
    private Long doctorId;

/*    @ApiModelProperty(position = 1, hidden = true)
    private Long id;

    @ApiModelProperty(position = 2, hidden = true)
    private Long fromUserId;

    @ApiModelProperty(position = 2, hidden = true)
    private Long languageId;

    @ApiModelProperty(position = 3)
    private Long formRequestId;

    @ApiModelProperty(position = 4)
    private String title;

    @ApiModelProperty(position = 5)
    private String content;

    @ApiModelProperty(position = 6, hidden = true)
    private String isRead;

    @ApiModelProperty(position = 8)
    private Long status;

    @ApiModelProperty(position = 9, hidden = true)
    private Long isActive;*/

}
