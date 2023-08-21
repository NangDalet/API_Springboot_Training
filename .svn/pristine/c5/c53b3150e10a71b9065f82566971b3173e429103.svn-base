package com.ut.masterCode.model.Users;

import com.ut.masterCode.enums.AuthProvider;
import com.ut.masterCode.model.base.BaseModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class User extends BaseModel implements Serializable {

  private static final long serialVersionUID = 1L;

  public static final Long ROLE_ADMIN = 1L;

  @ApiModelProperty(position = 1)
  private Long id;

  @ApiModelProperty(position = 1)
  private Long employeeId;

  @ApiModelProperty(position = 2)
  private String employeeName;

  @ApiModelProperty(position = 2)
  private String fullName;

  @ApiModelProperty(position = 3)
  private String username;

  @ApiModelProperty(position = 4)
  private String password;

  @ApiModelProperty(position = 5)
  private String filename;

  @ApiModelProperty(position = 6)
  private Long type;

  @ApiModelProperty(position = 7)
  private String sex;

  @ApiModelProperty(position = 8)
  private String address;

  @ApiModelProperty(position = 9)
  private String photo;

  @ApiModelProperty(position = 2)
  private Long photoType;

  @ApiModelProperty(position = 10)
  private String email;

  @ApiModelProperty(position = 11)
  private String telephone;

  @ApiModelProperty(position = 11)
  private String signature;

  @ApiModelProperty(position = 12)
  private String phoneVerifyToken;

  @ApiModelProperty(position = 13)
  private Date phoneVerifyTokenExpiredDate;

  @ApiModelProperty(position = 14)
  private String phoneVerifyCode;

  @ApiModelProperty(position = 15)
  private Date phoneVerifyCodeExpiredDate;

  @ApiModelProperty(position = 16)
  private String phoneResetPasswordToken;

  @ApiModelProperty(position = 17)
  private Date phoneResetPasswordTokenExpiredDate;

  @ApiModelProperty(position = 18)
  private String phoneResetPasswordCode;

  @ApiModelProperty(position = 19)
  private Date phoneResetPasswordCodeExpiredDate;

  @ApiModelProperty(position = 20)
  private String phoneNewPasswordToken;

  @ApiModelProperty(position = 21)
  private Date phoneNewPasswordTokenExpiredDate;

  @ApiModelProperty(position = 22)
  private AuthProvider provider;

  @ApiModelProperty(position = 23)
  private String providerId;

  @ApiModelProperty(position = 24)
  private String providerImageUrl;

  @ApiModelProperty(position = 25)
  private String providerData;

  @ApiModelProperty(position = 26)
  private String loginType;

  @ApiModelProperty(position = 27)
  private Long groupId;

  @ApiModelProperty(position = 28)
  private String groupName;

  @ApiModelProperty(position = 30)
  private String modifiedDate;

  @ApiModelProperty(position = 31)
  private Long statusLogin;

  @ApiModelProperty(position = 4)
  private String employeeCode;

  @ApiModelProperty(position = 6)
  private String employeeGender;

  @ApiModelProperty(position = 7)
  private String employeeDob;

  @ApiModelProperty(position = 8)
  private String employeePhoneNumber;

  @ApiModelProperty(position = 9)
  private String employeeEmail;

  @ApiModelProperty(position = 10)
  private Long provinceId;

  @ApiModelProperty(position = 11)
  private String provinceName;

  @ApiModelProperty(position = 12)
  private Long districtId;

  @ApiModelProperty(position = 13)
  private String districtName;

  @ApiModelProperty(position = 14)
  private Long communeId;

  @ApiModelProperty(position = 15)
  private String communeName;

  @ApiModelProperty(position = 16)
  private Long villageId;

  @ApiModelProperty(position = 17)
  private String villageName;

  @ApiModelProperty(position = 31)
  private String[] userGroup;

  @ApiModelProperty(position = 32)
  private List<UserGroupList> userRoleList;

}