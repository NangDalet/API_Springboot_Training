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
import com.ut.masterCode.model.base.Filter;
import com.ut.masterCode.model.base.Pagination;
import com.ut.masterCode.model.base.ResponseMessage;
import com.ut.masterCode.model.request.Form.FormRequest;
import com.ut.masterCode.model.request.Form.FormUpdateRequest;
import com.ut.masterCode.model.request.Form.SubFormRequest;
import com.ut.masterCode.model.request.Form.SubSubFormRequest;
import com.ut.masterCode.model.request.Login.Product.ProductSizeRequestDetail;
import com.ut.masterCode.model.response.Form.FormResponse;
import com.ut.masterCode.model.response.Form.SubFormResponse;
import com.ut.masterCode.model.response.Form.SubSubFormResponse;
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
    public ResponseMessage<BaseResult> getList(Filter filter, HttpServletRequest httpServletRequest) throws UnknownHostException {
        LocalTime startDuration = LocalTime.now();
        Long line = 1033L;
        try {
            // Check Permission
            Long userId = userService.getUserAuth().getId();
//            if (permissionMapper.checkPermission(userId, "Form (View)") == 0) {
//                return ResponseMessageUtils.makeResponseByPermission(true, messageService.message("No Permission access.", false));
//            }

            Pagination pagination = new Pagination();
            pagination.setPage(filter.getPage());
            pagination.setRowsPerPage(filter.getRowsPerPage());
            pagination.setTotal(formMapper.countList(filter));

            filter.setPage((filter.getPage() - 1) * filter.getRowsPerPage());

            List<FormResponse> formRespones = formMapper.getList(filter);

            /*System Activity*/
            LocalTime endDuration = LocalTime.now();
            activityLogService.insert("/form/list",null,null,"Form","Form (View)","View",1,"Success",startDuration,endDuration, httpServletRequest);
            return ResponseMessageUtils.makeResponse(true, messageService.message("Success", formRespones, pagination, true));
        } catch (Exception error) {
            /*System Activity*/
            LocalTime endDuration = LocalTime.now();
            activityLogService.insert("/form/list",line, error.toString(),"Form","Form (View)","View",2,"Error",startDuration,endDuration, httpServletRequest);
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
//            if (permissionMapper.checkPermission(userId, "Form (View)") == 0 && permissionMapper.checkPermission(userId, "Question (View)") == 0) {
//                return ResponseMessageUtils.makeResponseByPermission(true, messageService.message("No Permission access.", false));
//            }

            //! Set zone user
            List<FormResponse> formRespones = formMapper.getOne(id);
            if(formRespones.size() > 0) {
                //! /*Set sub form data*/
                for (FormResponse formRespone : formRespones) {
                    Long formId = formRespone.getId();
                    List<SubFormResponse> subFormRespones = formMapper.getListSubForm(formId);
                    formRespone.setSubFormResponses(subFormRespones);

                    if(subFormRespones.size() > 0) {
                        for (int j = 0; j < subFormRespones.size(); j++) {
                            Long subFormId = subFormRespones.get(j).getId();
                            List<SubSubFormResponse> subSubFormRespones = formMapper.getListSubSubForm(subFormId);
                            formRespone.getSubFormResponses().get(j).setSubSubFormResponses(subSubFormRespones);
                        }
                    }
                }
            }

            /*System Activity*/
            LocalTime endDuration = LocalTime.now();
            activityLogService.insert("/form/find/{id}",null,null,"Form","Form (View)","View",1,"Success",startDuration,endDuration, httpServletRequest);
            return ResponseMessageUtils.makeResponse(true, messageService.message("Success", formRespones, true));
        } catch (Exception error) {
            /*System Activity*/
            LocalTime endDuration = LocalTime.now();
            activityLogService.insert("/form/find/{id}",line, error.toString(),"Form","Form (View)","View",2,"Error",startDuration,endDuration, httpServletRequest);
            return ResponseMessageUtils.makeResponse(true, messageService.message("Error", null, false));
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
            LocalTime startDuration = LocalTime.now();
            Long line = 1033L;
            try {
                // Check Permission
                Long userId = userService.getUserAuth().getId();
//                if (permissionMapper.checkPermission(userId, "Form (Edit)") == 0 && permissionMapper.checkPermission(userId, "Question (Edit)") == 0) {
//                    return ResponseMessageUtils.makeResponseByPermission(true, messageService.message("No Permission access.", false));
//                }

                // Check Duplicate
                if(formMapper.checkDuplicate(formUpdateRequest.getName(), formUpdateRequest.getId()) > 0){
                    return ResponseMessageUtils.makeResponse(true, messageService.message("Duplicate form name", false));
                }

//      if (formMapper.countIsUsedForm(formUpdateRequest.getId()) > 0) {
//        return ResponseMessageUtils.makeResponseByPermission(true, messageService.message("This form has been used.", false));
//      }

                // Check Data
                Form form = new Form();
                form.setId(formUpdateRequest.getId());
                form.setName(formUpdateRequest.getName());
                form.setFormType(formUpdateRequest.getFormType());
                form.setIsActive(1);
                form.setModifiedBy(userId);

                Boolean result = formMapper.update(form);
                if (result) {

                    if(formUpdateRequest.getSubFormRequests().size() > 0) {
                        for (int i = 0; i < formUpdateRequest.getSubFormRequests().size(); i++) {
                            //! *Check Data Before insert sub form*/
                            SubFormRequest subFormRequest = new SubFormRequest();
                            subFormRequest.setId(formUpdateRequest.getSubFormRequests().get(i).getId());
                            subFormRequest.setFormId(form.getId());
                            subFormRequest.setName(formUpdateRequest.getSubFormRequests().get(i).getName());
                            subFormRequest.setFormTypeId(formUpdateRequest.getSubFormRequests().get(i).getFormTypeId());
                            subFormRequest.setRemarks(formUpdateRequest.getSubFormRequests().get(i).getRemarks());
                            subFormRequest.setStatus(formUpdateRequest.getSubFormRequests().get(i).getStatus());
                            subFormRequest.setIsDeleted(formUpdateRequest.getSubFormRequests().get(i).getIsDeleted());
                            subFormRequest.setOrdering(formUpdateRequest.getSubFormRequests().get(i).getOrdering());

                            //! delete subform by id
                            if (subFormRequest.getId()!=null){
                                formMapper.deleteSubFormId(subFormRequest.getId());
                            }
                            // insert sub new
                            formMapper.insertSubForm(subFormRequest);

                            if(formUpdateRequest.getSubFormRequests().get(i).getSubSubFormRequests().size() > 0) {
                                for (int j = 0; j < formUpdateRequest.getSubFormRequests().get(i).getSubSubFormRequests().size(); j++) {
                                    //! /*Check Data Before insert sub sub form*/
                                    SubSubFormRequest subSubFormRequest = new SubSubFormRequest();
                                    subSubFormRequest.setId(formUpdateRequest.getSubFormRequests().get(i).getSubSubFormRequests().get(j).getId());
                                    subSubFormRequest.setSubFormId(subFormRequest.getId());
                                    subSubFormRequest.setName(formUpdateRequest.getSubFormRequests().get(i).getSubSubFormRequests().get(j).getName());
                                    subSubFormRequest.setIsDeleted(formUpdateRequest.getSubFormRequests().get(i).getSubSubFormRequests().get(j).getIsDeleted());

                                    //deleteSubSubFormId
                                    if (subSubFormRequest.getId()!=null){
                                        formMapper.deleteSubSubFormId(subSubFormRequest.getId());
                                    }
                                    //! Add New Sub Sub Form
                                    formMapper.insertSubSubForm(subSubFormRequest);
                                }
                            }
                        }
                    }

                    /*System Activity*/
                    LocalTime endDuration = LocalTime.now();
                    activityLogService.insert("/form/update",null,null,"Form","Form (Edit)","Edit",1,"Success",startDuration,endDuration, httpServletRequest);
                    return ResponseMessageUtils.makeResponse(true, messageService.message("Success", true));
                } else {
                    return ResponseMessageUtils.makeResponse(true, messageService.message("Fail", false));
                }
            } catch (Exception error) {
                /*System Activity*/
                LocalTime endDuration = LocalTime.now();
                activityLogService.insert("/form/update",line, error.toString(),"Form","Form (Edit)","Edit",2,"Error",startDuration,endDuration, httpServletRequest);
                return ResponseMessageUtils.makeResponse(true, messageService.message("Error", null, false));
            }
        }

    @Override
    public ResponseMessage<BaseResult> delete(Long id, HttpServletRequest httpServletRequest) throws UnknownHostException {
        {
            LocalTime startDuration = LocalTime.now();
            Long line = 1033L;
            try {
                // Check Permission
                Long userId = userService.getUserAuth().getId();
//                if (permissionMapper.checkPermission(userId, "Form (Delete)") == 0) {
//                    return ResponseMessageUtils.makeResponseByPermission(true, messageService.message("No Permission access.", false));
//                }

//      if (formMapper.countIsUsedForm(id) > 0) {
//        return ResponseMessageUtils.makeResponseByPermission(true, messageService.message("This form has been used.", false));
//      }

                Boolean result = formMapper.delete(id);
                if (result) {
                    /*System Activity*/
                    LocalTime endDuration = LocalTime.now();
                    activityLogService.insert("/form/delete/{id}",null,null,"Form","Form (Delete)","Delete",1,"Success",startDuration,endDuration, httpServletRequest);
                    return ResponseMessageUtils.makeResponse(true, messageService.message("Success", true));
                } else {
                    return ResponseMessageUtils.makeResponse(true, messageService.message("Fail", false));
                }
            } catch (Exception error) {
                /*System Activity*/
                LocalTime endDuration = LocalTime.now();
                activityLogService.insert("/form/delete/{id}",line, error.toString(),"Form","Form (Delete)","Delete",2,"Error",startDuration,endDuration, httpServletRequest);
                return ResponseMessageUtils.makeResponse(true, messageService.message("Error", null, false));
            }
        }
    }
}
