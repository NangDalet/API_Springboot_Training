package com.ut.masterCode.service;

import com.ut.masterCode.base.UserAuthSession;
import com.ut.masterCode.helper.ResponseMessageUtils;
import com.ut.masterCode.mapper.primary.FormMapper;
import com.ut.masterCode.mapper.primary.ProductMapper;
import com.ut.masterCode.mapper.primary.UserMapper;
import com.ut.masterCode.model.Form;
import com.ut.masterCode.model.MessageService;
import com.ut.masterCode.model.Product;
import com.ut.masterCode.model.Users.User;
import com.ut.masterCode.model.base.BaseResult;
import com.ut.masterCode.model.base.ResponseMessage;
import com.ut.masterCode.model.request.Form.FormRequest;
import com.ut.masterCode.model.request.Form.FormUpdateRequest;
import com.ut.masterCode.model.request.Form.SubFormRequest;
import com.ut.masterCode.model.request.Form.SubSubFormRequest;
import com.ut.masterCode.model.request.Login.Product.ProductSizeRequestDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.net.UnknownHostException;
import java.time.LocalTime;
import java.util.List;

@Service
public class FormServiceImp implements FormService{
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private FormMapper formMapper;
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
    public ResponseMessage<BaseResult> insert(FormRequest formRequest, HttpServletRequest httpServletRequest) throws UnknownHostException {
        LocalTime startDuration = LocalTime.now();
        Long line = 1033L;
        try {
            // Check Permission
            Long userId = userService.getUserAuth().getId();
            //if (permissionMapper.checkPermission(userId, "Leave Request (Add)") == 0) {
            //return ResponseMessageUtils.makeResponseByPermission(true, messageService.message("No Permission access.", false));
            //}
            // Check Duplicate
            if(formMapper.checkDuplicate(formRequest.getName(), null) > 0){
                return ResponseMessageUtils.makeResponse(true, messageService.message("Duplicate form name", false));
            }
            // check Data
            Form form = new Form(); //create new obj
            form.setName((formRequest.getName()));
            form.setFormType(formRequest.getFormType());
            form.setCreatedBy(userId);
            form.setIsActive(1);
            Boolean result = formMapper.insert(form);
            if (result) {
                //! Check subFormRequests request
                if (formRequest.getSubFormRequests().size() > 0) {
                    for (int i = 0; i < formRequest.getSubFormRequests().size(); i++) {
                        //! Check data before insert
                        SubFormRequest subFormRequest = new SubFormRequest();
                        subFormRequest.setFormId(form.getId());
                        subFormRequest.setName(formRequest.getSubFormRequests().get(i).getName());
                        subFormRequest.setFormTypeId(formRequest.getSubFormRequests().get(i).getFormTypeId());
                        subFormRequest.setRemarks(formRequest.getSubFormRequests().get(i).getRemarks());
                        subFormRequest.setStatus(formRequest.getSubFormRequests().get(i).getStatus());
                        subFormRequest.setOrdering(formRequest.getSubFormRequests().get(i).getOrdering());
                        formMapper.insertSubForm(subFormRequest);

                        if (formRequest.getSubFormRequests().size() > 0) {
                            for (int j = 0; j < formRequest.getSubFormRequests().size(); j++) {
                                //! Check data before insert
                                SubSubFormRequest subSubFormRequest = new SubSubFormRequest();
                                subSubFormRequest.setSubFormId(form.getId());
                                subSubFormRequest.setName(formRequest.getSubFormRequests().get(i).getSubSubFormRequests().get(j).getName());
                                formMapper.insertSubSubForm(subSubFormRequest);
                            }
                        }
                    }
                }
                /*System Activity*/
                LocalTime endDuration = LocalTime.now();
                activityLogService.insert("/form/add",null,null,"Form","Form (Add)","Add",1,"Success",startDuration,endDuration, httpServletRequest);
                return ResponseMessageUtils.makeResponse(true, messageService.message("Success", true));
            } else {
                return ResponseMessageUtils.makeResponse(true, messageService.message("Fail", false));
            }
        } catch (Exception error) {
            /*System Activity*/
            LocalTime endDuration = LocalTime.now();
            activityLogService.insert("/form/add",line, error.toString(),"Form","Form (Add)","Add",2,"Error",startDuration,endDuration, httpServletRequest);
            return ResponseMessageUtils.makeResponse(true, messageService.message("Error", null, false));
        }
    }

    @Override
    public ResponseMessage<BaseResult> update(FormUpdateRequest formUpdateRequest, HttpServletRequest httpServletRequest) throws UnknownHostException {
        return null;
    }
}
