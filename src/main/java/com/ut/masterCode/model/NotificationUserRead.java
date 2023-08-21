package com.ut.masterCode.model;

import com.ut.masterCode.model.base.BaseModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class NotificationUserRead extends BaseModel {

    @ApiModelProperty(position = 1)
    private Long notificationId;

}
