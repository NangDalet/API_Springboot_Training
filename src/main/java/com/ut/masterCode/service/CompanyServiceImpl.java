package com.ut.masterCode.service;

import com.ut.masterCode.helper.ResponseMessageUtils;
import com.ut.masterCode.mapper.primary.CompanyMapper;
import com.ut.masterCode.model.Company;
import com.ut.masterCode.model.MessageService;
import com.ut.masterCode.model.base.BaseResult;
import com.ut.masterCode.model.base.Filter;
import com.ut.masterCode.model.base.Pagination;
import com.ut.masterCode.model.base.ResponseMessage;
import com.ut.masterCode.model.request.Login.CompanyRequest;
import com.ut.masterCode.model.request.Login.User.CompanyUpdateRequest;
import com.ut.masterCode.model.response.CompanyResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.awt.SystemColor.text;

@Service
public class CompanyServiceImpl implements CompanyService{

    @Autowired
    private CompanyMapper companyMapper;

    @Autowired
    private MessageService messageService;

    @Autowired
    private UserService userService;

    @Override
    public ResponseMessage<BaseResult> insert(CompanyRequest companyRequest) {
        Long userId=userService.getUserAuth().getId();
        //check Duplicate
        if (companyMapper.checkDuplicate(companyRequest.getName())>0){
            return ResponseMessageUtils.makeResponse(true, messageService.message("Duplicate", false));
        }
        // check Data
        Company company =new Company(); //create new obj
        company.setName((companyRequest.getName()));
        company.setNameOfStaff(companyRequest.getNameOfStaff());
        company.setSkill(companyRequest.getSkill());
        company.setSalary(companyRequest.getSalary());
        company.setCreatedBy(userId);
        company.setIsActive(1);
        Boolean result=companyMapper.insert(company);
        if(result){
            return ResponseMessageUtils.makeResponseByPermission(true, messageService.message("Success.", true));
        }else {
            return ResponseMessageUtils.makeResponseByPermission(true, messageService.message("Fail.", false));
        }
    }
    @Override
    public ResponseMessage<BaseResult> update(CompanyUpdateRequest companyUpdateRequest) {
        Long userId = userService.getUserAuth().getId();

        //check Duplicate
        if (companyMapper.checkDuplicateUpdate(companyUpdateRequest.getName(),companyUpdateRequest.getId())>0){
            return ResponseMessageUtils.makeResponse(true, messageService.message("Duplicate", false));
        }

        System.out.println(companyUpdateRequest);
        //check data
        Company company=new Company();
        company.setId(companyUpdateRequest.getId());
        company.setName(companyUpdateRequest.getName());
        company.setNameOfStaff(companyUpdateRequest.getNameOfStaff());
        company.setSkill(companyUpdateRequest.getSkill());
        company.setSalary(companyUpdateRequest.getSalary());
        company.setCreatedBy(userId);
        company.setIsActive(1);

        System.out.println(company);
        Boolean result=companyMapper.update(company);

        if (result){
            return ResponseMessageUtils.makeResponse(true, messageService.message("Success", true));
        }else {
            return ResponseMessageUtils.makeResponse(true, messageService.message("Fail", false));
        }
    }

    @Override
    public ResponseMessage<BaseResult> delete(Long id) {
        Boolean result=companyMapper.delete(id);
        if (result){
            return ResponseMessageUtils.makeResponse(true, messageService.message("Success", true));
        }else {
            return ResponseMessageUtils.makeResponse(true, messageService.message("Fail", false));
        }
    }

    @Override
    public ResponseMessage<BaseResult> list(Filter filter) {
       //pagination
        Pagination pagination=new Pagination();
        pagination.setPage(filter.getPage());
        pagination.setRowsPerPage(filter.getRowsPerPage());
        pagination.setTotal(companyMapper.checkTotal());

        filter.setPage((filter.getPage()-1)*filter.getRowsPerPage());
        List<CompanyResponse> companyResponseList=companyMapper.list(filter);
        return ResponseMessageUtils.makeResponse(true, messageService.message("Success", companyResponseList, true));
    }

    @Override
    public ResponseMessage<BaseResult> getOne(Long id) {
        List<CompanyResponse> companyResponses=companyMapper.getOne(id);
        return ResponseMessageUtils.makeResponse(true, messageService.message("Success", companyResponses, true));
    }
}
