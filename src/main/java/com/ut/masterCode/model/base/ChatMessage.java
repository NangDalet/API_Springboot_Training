package com.ut.masterCode.model.base;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class ChatMessage extends BaseModel implements Serializable {

  private static final long serialVersionUID = 1L;

  @ApiModelProperty(position = 1)
  private Long id;

  @ApiModelProperty(position = 2)
  private MessageType type;

  @ApiModelProperty(position = 3)
  private String room;

  @ApiModelProperty(position = 4)
  private String sender;

  @ApiModelProperty(position = 5)
  private String senderContact;

  @ApiModelProperty(position = 6)
  private String recipient;

  @ApiModelProperty(position = 7)
  private String recipientContact;

  @ApiModelProperty(position = 8)
  private String content;

  @ApiModelProperty(position = 9)
  private ContentType contentType;

  @ApiModelProperty(position = 10)
  private Double lat;

  @ApiModelProperty(position = 11)
  private Double lng;

  @ApiModelProperty(position = 12)
  private Double angle;

  @ApiModelProperty(position = 13)
  private Long timestamp;

  @ApiModelProperty(position = 14)
  private int isUnread;

  @ApiModelProperty(position = 15)
  private long typeId;

  @ApiModelProperty(position = 16)
  private String deviceToken;

  public enum MessageType {
    JOIN,
    TYPING,
    CHAT,
    LEAVE
  }

  public enum ContentType {
    TEXT,
    IMAGE,
    AUDIO,
    VIDEO,
    OTHER
  }

  @Data
  public static class SlideShows extends BaseModel implements Serializable {

    private static final long serialVersionUID = 1L;

    public static final Long ROLE_ADMIN = 1L;

    @ApiModelProperty(position = 1)
    private Long id;

    @ApiModelProperty(position = 2)
    private String url;

  }

}
