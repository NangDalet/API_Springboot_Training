package com.ut.masterCode.model.request.Login;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class LoginRequest {

    @ApiModelProperty(position = 1)
    private String deviceId;

    @ApiModelProperty(position = 2)
    private String deviceName;

    @ApiModelProperty(position = 3)
    private String password;

    @ApiModelProperty(position = 4)
    private String username;

    @ApiModelProperty(position = 5, hidden = true)
    private String siteKey;
}
