package com.ut.masterCode.mapper.primary;

import com.ut.masterCode.model.Company;
import com.ut.masterCode.model.Product;
import com.ut.masterCode.model.base.Filter;
import com.ut.masterCode.model.filter.LeaveRequestFilter;
import com.ut.masterCode.model.filter.ProductRequestFilter;
import com.ut.masterCode.model.request.Login.Product.ProductSizeRequestDetail;
import com.ut.masterCode.model.response.CompanyResponse;
import com.ut.masterCode.model.response.LeaveRequest.LeaveRequestDateResponse;
import com.ut.masterCode.model.response.LeaveRequest.LeaveRequestResponse;
import com.ut.masterCode.model.response.Product.ProductResponse;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ProductMapper {
    Boolean insert(@Param("product") Product product);
    Boolean update(@Param("product")Product product);
    Boolean delete(@Param("id") Long id, @Param("userId") Long userId);
    Long checkDuplicate(@Param("name")String name);
    Long checkDuplicateUpdate(@Param("updateName")String updateName,@Param("id")Long id);
    Long checkTotal();
    //! Update product size detail
    Boolean insertProductSizeDetail(@Param("productSizeRequestDetail") ProductSizeRequestDetail productSizeRequestDetail);
    Boolean deleteProductSizeDetail(@Param("productId") Long leaveRequestId, @Param("userId") Long userId);
}
