package com.ut.masterCode.mapper.primary;

import com.ut.masterCode.model.Product;
import com.ut.masterCode.model.filter.LeaveRequestFilter;
import com.ut.masterCode.model.filter.ProductFilter;
import com.ut.masterCode.model.request.Login.Product.ProductSizeRequestDetail;
import com.ut.masterCode.model.response.LeaveRequest.LeaveRequestDateResponse;
import com.ut.masterCode.model.response.LeaveRequest.LeaveRequestResponse;
import com.ut.masterCode.model.response.Product.ProductResponse;
import com.ut.masterCode.model.response.Product.ProductSizeResponse;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ProductMapper {
    List<ProductResponse> getList(@Param("filter") ProductFilter filter);
    List<Long> countList(@Param("filter") ProductFilter filter);
    List<ProductResponse> getOne(@Param("id") Long id);
    Boolean insert(@Param("product") Product product);
    Boolean update(@Param("product")Product product);
    Boolean delete(@Param("id") Long id, @Param("userId") Long userId);
    Long checkDuplicate(@Param("name")String name);
    Long checkDuplicateUpdate(@Param("updateName")String updateName,@Param("id")Long id);
    Long checkTotal();
    //! Update product size detail
    List<ProductSizeResponse> getProductSizeResponse(@Param("productId") Long productRequestId);
    List<ProductSizeResponse> getProductSizeList(@Param("productId") Long productId);
    Boolean insertProductSizeDetail(@Param("productSizeRequestDetail") ProductSizeRequestDetail productSizeRequestDetail);
    Boolean deleteProductSizeDetail(@Param("productId") Long productId, @Param("userId") Long userId);
}
