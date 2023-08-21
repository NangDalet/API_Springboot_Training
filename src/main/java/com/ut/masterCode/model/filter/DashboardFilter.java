package com.ut.masterCode.model.filter;

import com.ut.masterCode.model.base.Filter;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class DashboardFilter extends Filter {

    private Long userId;

}
