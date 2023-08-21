package com.ut.masterCode.model.response.Product;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class ProductSizeResponse {
    @ApiModelProperty(position = 1, hidden = true)
    private Long id;

    @ApiModelProperty(position = 2)
    private int size;
}
