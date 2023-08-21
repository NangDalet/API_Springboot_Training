package com.ut.masterCode.model.request.Login.Product;

import com.ut.masterCode.model.Product;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class ProductSizeRequestDetail {
    @ApiModelProperty(position = 1, hidden = true)
    private Long productId;
    @ApiModelProperty(position = 2)
    private Long size;
    @ApiModelProperty(position = 3, hidden = true)
    private Long createdBy;
    @ApiModelProperty(position = 4, hidden = true)
    private Long modifiedBy;
}
