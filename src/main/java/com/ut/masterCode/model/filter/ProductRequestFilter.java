package com.ut.masterCode.model.filter;

import com.ut.masterCode.model.base.Filter;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
public class ProductRequestFilter extends Filter {
    @ApiModelProperty(position = 12)
    private String name;

    @ApiModelProperty(position = 13)
    private Long productId;

    @ApiModelProperty(position = 14)
    private String productSize;
}
