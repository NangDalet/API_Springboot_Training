package com.ut.masterCode.service;

import com.ut.masterCode.base.UserAuthSession;
import com.ut.masterCode.helper.ResponseMessageUtils;
import com.ut.masterCode.mapper.primary.ProductMapper;
import com.ut.masterCode.mapper.primary.UserMapper;
import com.ut.masterCode.model.LeaveRequest;
import com.ut.masterCode.model.MessageService;
import com.ut.masterCode.model.Product;
import com.ut.masterCode.model.Users.User;
import com.ut.masterCode.model.base.BaseResult;
import com.ut.masterCode.model.base.Filter;
import com.ut.masterCode.model.base.Pagination;
import com.ut.masterCode.model.base.ResponseMessage;
import com.ut.masterCode.model.filter.LeaveRequestFilter;
import com.ut.masterCode.model.filter.ProductFilter;
import com.ut.masterCode.model.request.LeaveRequest.LeaveRequestDetailRequest;
import com.ut.masterCode.model.request.Login.Product.ProductRequest;
import com.ut.masterCode.model.request.Login.Product.ProductSizeRequestDetail;
import com.ut.masterCode.model.request.Login.Product.ProductUpdateRequest;
import com.ut.masterCode.model.response.CompanyResponse;
import com.ut.masterCode.model.response.LeaveRequest.LeaveRequestDateResponse;
import com.ut.masterCode.model.response.LeaveRequest.LeaveRequestResponse;
import com.ut.masterCode.model.response.Product.ProductResponse;
import com.ut.masterCode.model.response.Product.ProductSizeResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.net.UnknownHostException;
import java.time.LocalTime;
import java.util.List;

@Service
public class ProductServiceImp implements ProductService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private MessageService messageService;

    @Autowired
    private UserService userService;
    @Autowired
    private ActivityLogService activityLogService;

    public User getUserAuth() {
        try {
            if (UserAuthSession.getUserAuth() != null) {
                List<User> userList = userMapper.getOneByUsername(UserAuthSession.getUserAuth().getUsername());
                return userList.get(0);
            } else {
                return new User();
            }
        } catch (Exception errorr) {
            return null;
        }
    }
    @Override
    public ResponseMessage<BaseResult> getList(ProductFilter filter, HttpServletRequest httpServletRequest) throws UnknownHostException {
        LocalTime startDuration = LocalTime.now();
        Long line = 1033L;
        try {
            // Check Permission
            Long userId = userService.getUserAuth().getId();
            //if (permissionMapper.checkPermission(userId, "Leave Request (View)") == 0) {
            //return ResponseMessageUtils.makeResponseByPermission(true, messageService.message("No Permission access.", false));
            //}

            Pagination pagination = new Pagination();
            pagination.setPage(filter.getPage());
            pagination.setRowsPerPage(filter.getRowsPerPage());
            pagination.setTotal((long) productMapper.countList(filter).size());
            filter.setPage((filter.getPage() - 1) * filter.getRowsPerPage());

            List<ProductResponse> productResponses = productMapper.getList(filter);

            if (productResponses.size() > 0) {
                for (int i = 0; i < productResponses.size(); i++) {
                    //! Get Product Id
                    Long productId = productResponses.get(i).getId();
                    //! Get product size
                    List<ProductSizeResponse> productSizeResponses = productMapper.getProductSizeList(productId);
                    productResponses.get(i).setProductSize(productSizeResponses);
                }
            }

            /*System Activity*/
            LocalTime endDuration = LocalTime.now();
            activityLogService.insert("/product/list", null, null, "Product", "Product (View)", "View", 1, "Success", startDuration, endDuration, httpServletRequest);
            return ResponseMessageUtils.makeResponse(true, messageService.message("Success", productResponses, pagination, true));
        } catch (Exception error) {
            System.out.println(error);
            /*System Activity*/
            LocalTime endDuration = LocalTime.now();
            activityLogService.insert("/Product/list", line, error.toString(), "Product", "Product (View)", "View", 2, "Error", startDuration, endDuration, httpServletRequest);
            return ResponseMessageUtils.makeResponse(true, messageService.message("Error", null, false));
        }
    }

    @Override
    public ResponseMessage<BaseResult> getOne(Long id, HttpServletRequest httpServletRequest) throws UnknownHostException {
        LocalTime startDuration = LocalTime.now();
        Long line = 1033L;
        try {
            // Check Permission
            Long userId = userService.getUserAuth().getId();
            //if (permissionMapper.checkPermission(userId, "Leave Request (View)") == 0) {
            //return ResponseMessageUtils.makeResponseByPermission(true, messageService.message("No Permission access.", false));
            //}

            List<ProductResponse> productResponses = productMapper.getOne(id);

            if (productResponses.size() > 0) {
                for (int i = 0; i < productResponses.size(); i++) {
                    //! Get Product Id
                    Long productId = productResponses.get(i).getId();
                    //! Get Product Size
                    List<ProductSizeResponse> productSizeResponses = productMapper.getProductSizeResponse(productId);
                    productResponses.get(i).setProductSize(productSizeResponses);
                }
            }
            /*System Activity*/
            LocalTime endDuration = LocalTime.now();
            activityLogService.insert("/Product/find/{id}", null, null, "Product", "Product (View)", "View", 1, "Success", startDuration, endDuration, httpServletRequest);
            return ResponseMessageUtils.makeResponse(true, messageService.message("Success", productResponses, true));
        } catch (Exception error) {
            /*System Activity*/
            LocalTime endDuration = LocalTime.now();
            activityLogService.insert("/Product/find/{id}", line, error.toString(), "Product", "Product (View)", "View", 2, "Error", startDuration, endDuration, httpServletRequest);
            return ResponseMessageUtils.makeResponse(true, messageService.message("Error", null, false));
        }
    }


    @Override
    public ResponseMessage<BaseResult> insert(ProductRequest productRequest, HttpServletRequest httpServletRequest) throws UnknownHostException {
        LocalTime startDuration = LocalTime.now();
        Long line = 1033L;
        try {
            // Check Permission
            Long userId = userService.getUserAuth().getId();
            //if (permissionMapper.checkPermission(userId, "Leave Request (Add)") == 0) {
            //return ResponseMessageUtils.makeResponseByPermission(true, messageService.message("No Permission access.", false));
            //}
            // check Data
            Product product = new Product(); //create new obj
            product.setName((productRequest.getName()));
            product.setPhoto(productRequest.getPhoto());
            product.setDescription(productRequest.getDescription());
            product.setPrice(productRequest.getPrice());
            product.setCreatedBy(userId);
            product.setIsActive(1);
            Boolean result = productMapper.insert(product);
            if (result) {
                //! Check productSize request
                if (productRequest.getProductSize().size() > 0) {
                    for (int i = 0; i < productRequest.getProductSize().size(); i++) {
                        //! Check data before insert
                        ProductSizeRequestDetail productSizeDetail = new ProductSizeRequestDetail();
                        productSizeDetail.setProductId(product.getId());
                        productSizeDetail.setSize(productRequest.getProductSize().get(i));
                        productSizeDetail.setCreatedBy(userId);
                        productMapper.insertProductSizeDetail(productSizeDetail);
                    }
                }
                /*System Activity*/
                LocalTime endDuration = LocalTime.now();
                activityLogService.insert("/product-request/add",null,null,"Product Request","Product Request (Add)","Add",1,"Success",startDuration,endDuration, httpServletRequest);
                return ResponseMessageUtils.makeResponse(true, messageService.message("Success", true));
            } else {
                return ResponseMessageUtils.makeResponse(true, messageService.message("Fail", false));
            }
        } catch (Exception error) {
            /*System Activity*/
            LocalTime endDuration = LocalTime.now();
            activityLogService.insert("/product-request/add",line, error.toString(),"Product Request","Product Request (Add)","Add",2,"Error",startDuration,endDuration, httpServletRequest);
            return ResponseMessageUtils.makeResponse(true, messageService.message("Error", null, false));
        }
    }

    @Override
    public ResponseMessage<BaseResult> update(ProductUpdateRequest productUpdateRequest, HttpServletRequest httpServletRequest) throws UnknownHostException {
        LocalTime startDuration = LocalTime.now();
        Long line = 1033L;
        try {
            // Check Permission
            Long userId = userService.getUserAuth().getId();

            // Check Data
            Product product = new Product(); //create new obj
            product.setId(productUpdateRequest.getId());
            product.setName(productUpdateRequest.getName());
            product.setPhoto(productUpdateRequest.getPhoto());
            product.setDescription(productUpdateRequest.getDescription());
            product.setPrice(productUpdateRequest.getPrice());
            product.setModifiedBy(getUserAuth().getId());
            product.setIsActive(1);


            System.out.println(product);
            Boolean result = productMapper.update(product);

            if (result) {
                //! delete leave request detail by id
                productMapper.deleteProductSizeDetail(product.getId(), userId);

                //! Check leave request detail
                if(productUpdateRequest.getProductSize().size() > 0) {
                    for (int i = 0; i < productUpdateRequest.getProductSize().size(); i++){
                        //! Check data before insert
                        ProductSizeRequestDetail productSizeRequestDetail = new ProductSizeRequestDetail();
                        productSizeRequestDetail.setProductId(product.getId());
                        productSizeRequestDetail.setSize(productUpdateRequest.getProductSize().get(i));
                        productSizeRequestDetail.setCreatedBy(userId);
                        productMapper.insertProductSizeDetail(productSizeRequestDetail);
                    }
                }

                /*System Activity*/
                LocalTime endDuration = LocalTime.now();
                activityLogService.insert("/product-request/update",null,null,"Product Request","Product Request (Update)","Update",1,"Success",startDuration,endDuration, httpServletRequest);
                return ResponseMessageUtils.makeResponse(true, messageService.message("Success", true));
            } else {
                return ResponseMessageUtils.makeResponse(true, messageService.message("Fail", false));
            }
        } catch (Exception error) {
            /*System Activity*/
            LocalTime endDuration = LocalTime.now();
            activityLogService.insert("/product-request/update",line, error.toString(),"Product Request","Product Request (Update)","Update",2,"Error",startDuration,endDuration, httpServletRequest);
            return ResponseMessageUtils.makeResponse(true, messageService.message("Error", null, false));
        }
    }

    @Override
    public ResponseMessage<BaseResult> delete(Long id, HttpServletRequest httpServletRequest) throws UnknownHostException {
        Long userId = userService.getUserAuth().getId();

        Boolean result = productMapper.delete(id, userId);
        if (result) {
            return ResponseMessageUtils.makeResponse(true, messageService.message("Success", true));
        } else {
            return ResponseMessageUtils.makeResponse(true, messageService.message("Fail", false));
        }
    }
}
