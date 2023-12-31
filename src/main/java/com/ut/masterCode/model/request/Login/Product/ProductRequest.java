package com.ut.masterCode.model.request.Login.Product;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
public class ProductRequest {
    @ApiModelProperty(position = 1)
    private String name;
    @ApiModelProperty(position = 2)
    private String photo;
    @ApiModelProperty(position = 3)
    private String description;
    @ApiModelProperty(position = 4)
    private Double price;
    @ApiModelProperty(position = 15)
    private List<Long> productSize;
}
