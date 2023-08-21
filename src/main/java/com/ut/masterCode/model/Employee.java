package com.ut.masterCode.model;

import com.ut.masterCode.model.base.BaseModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;
@Data
@EqualsAndHashCode(callSuper = true)
public class Employee extends BaseModel {
    @ApiModelProperty(position = 1)
    private Long id;
    @ApiModelProperty(position = 2)
    private Long userId;
    @ApiModelProperty(position = 3)
    private Long departmentId;
    @ApiModelProperty(position = 4)
    private Long positionId;
    @ApiModelProperty(position = 5)
    private Long sendPayslipViaId;
    @ApiModelProperty(position = 6)
    private Long employeeStatusId;
    @ApiModelProperty(position = 7)
    private String name;
    @ApiModelProperty(position = 8)
    private String EmployeeCode;
    @ApiModelProperty(position = 9)
    private String gender;
    @ApiModelProperty(position = 10)
    private Date dateOfBirth;
    @ApiModelProperty(position = 11)
    private String photo;
    @ApiModelProperty(position = 12)
    private String wg;
    @ApiModelProperty(position = 13)
    private Long costCenterId;
    @ApiModelProperty(position = 14)
    private Date dateOfEmployee;
    @ApiModelProperty(position = 15)
    private String baseLocation;
    @ApiModelProperty(position = 16)
    private String region;
    @ApiModelProperty(position = 17)
    private String vehicleRemark;
    @ApiModelProperty(position = 18)
    private Double payslipMethodId;
    @ApiModelProperty(position = 19)
    private String bankName;
    @ApiModelProperty(position = 20)
    private String bankAccNumber;
    @ApiModelProperty(position = 21)
    private String pensionAccountNumber;
    @ApiModelProperty(position = 22)
    private String email;
    @ApiModelProperty(position = 23)
    private String phoneNumber;
    @ApiModelProperty(position = 24)
    private String telegramId;
    @ApiModelProperty(position = 25)
    private String address;
    @ApiModelProperty(position = 26)
    private Long wife;
    @ApiModelProperty(position = 27)
    private Long child;
    @ApiModelProperty(position = 28)
    private Integer isProvidentFund;
    @ApiModelProperty(position = 29)
    private Double employeePensionPercentage;
    @ApiModelProperty(position = 30)
    private Double employerPensionPercentage;
    @ApiModelProperty(position = 31)
    private Double currentSalary;
}
