package com.ut.masterCode.model.notification;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
public class PushMessage {

  @ApiModelProperty(position = 1)
  private String category;

  @ApiModelProperty(position = 2)
  private String title;

  @ApiModelProperty(position = 3)
  private String body;

  @ApiModelProperty(position = 4)
  private Long badge;

  @ApiModelProperty(position = 5)
  private String sound;

  @ApiModelProperty(position = 6)
  private String type;

  @ApiModelProperty(position = 7)
  private String goodsTransferId;

  @ApiModelProperty(position = 8)
  private String image;

  @ApiModelProperty(position = 8)
  private int status;

  @ApiModelProperty(position = 9)
  private List<DeviceToken> deviceTokens;

  @ApiModelProperty(position = 10)
  private String apiKey;

  public String getSound() {
    if (sound == null) {
      sound = "default";
    }
    return sound;
  }

  public String getType() {
    if (type == null) {
      type = "NOTIFICATION";
    }
    return type;
  }

  @ApiModelProperty(position = 11)
  private Long notificationId;

  @ApiModelProperty(position = 12)
  private Long notificationType;

  @ApiModelProperty(position = 14)
  private String branchName;

}
