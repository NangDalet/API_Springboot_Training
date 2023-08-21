package com.ut.masterCode.model.filter;

import com.ut.masterCode.model.base.Filter;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class NurseFilter extends Filter {

    @ApiModelProperty(position = 10)
    private String fromDate;

    @ApiModelProperty(position = 11)
    private String toDate;

}
