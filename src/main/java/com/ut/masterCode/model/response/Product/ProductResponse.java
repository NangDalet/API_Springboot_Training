package com.ut.masterCode.model.response.Product;

import com.ut.masterCode.model.response.LeaveRequest.LeaveRequestDateResponse;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
public class ProductResponse {
    @ApiModelProperty(position = 1)
    private Long id;
    @ApiModelProperty(position = 2)
    private String name;
    @ApiModelProperty(position = 3)
    private String photo;
    @ApiModelProperty(position = 4)
    private String description;
    @ApiModelProperty(position = 5)
    private Double price;
    @ApiModelProperty(position = 15)
    private List<ProductSizeResponse> productSize;
}
