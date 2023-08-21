package com.ut.masterCode.service;

import com.ut.masterCode.base.UserAuthSession;
import com.ut.masterCode.helper.ResponseMessageUtils;
import com.ut.masterCode.mapper.primary.EmployeeMapper;
import com.ut.masterCode.mapper.primary.UserMapper;
import com.ut.masterCode.model.Company;
import com.ut.masterCode.model.Employee;
import com.ut.masterCode.model.MessageService;
import com.ut.masterCode.model.Users.User;
import com.ut.masterCode.model.base.BaseResult;
import com.ut.masterCode.model.base.Filter;
import com.ut.masterCode.model.base.Pagination;
import com.ut.masterCode.model.base.ResponseMessage;
import com.ut.masterCode.model.request.Login.Employee.EmployeeRequest;
import com.ut.masterCode.model.request.Login.Employee.EmployeeUpdateRequest;
import com.ut.masterCode.model.response.CompanyResponse;
import com.ut.masterCode.model.response.EmployeeResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImp implements EmployeeService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private EmployeeMapper employeeMapper;

    @Autowired
    private MessageService messageService;

    @Autowired
    private UserService userService;
    @Override
    public ResponseMessage<BaseResult> insert(EmployeeRequest employeeRequest) {
        Long userId=userService.getUserAuth().getId();
        //check Duplicate
        if (employeeMapper.checkDuplicate(employeeRequest.getEmployeeCode())>0){
            return ResponseMessageUtils.makeResponse(true, messageService.message("Duplicate", false));
        }
        // check Data
        Employee employee=new Employee(); //create new obj
        employee.setUserId((employeeRequest.getUserId()));
        employee.setDepartmentId(employeeRequest.getDepartmentId());
        employee.setPositionId(employeeRequest.getPositionId());
        employee.setSendPayslipViaId(employeeRequest.getSendPayslipViaId());
        employee.setEmployeeStatusId(employeeRequest.getEmployeeStatusId());
        employee.setName(employeeRequest.getName());
        employee.setEmployeeCode(employeeRequest.getEmployeeCode());
        employee.setGender(employeeRequest.getGender());
        employee.setDateOfBirth(employeeRequest.getDateOfBirth());
        employee.setPhoto(employeeRequest.getPhoto());
        employee.setWg(employeeRequest.getWg());
        employee.setCostCenterId(employeeRequest.getCostCenterId());
        employee.setDateOfEmployee(employeeRequest.getDateOfEmployee());
        employee.setBaseLocation(employeeRequest.getBaseLocation());
        employee.setRegion(employeeRequest.getRegion());
        employee.setVehicleRemark(employeeRequest.getVehicleRemark());
        employee.setPayslipMethodId(employeeRequest.getPayslipMethodId());
        employee.setBankName(employeeRequest.getBankName());
        employee.setBankAccNumber(employeeRequest.getBankAccNumber());
        employee.setPensionAccountNumber(employeeRequest.getPensionAccountNumber());
        employee.setEmail(employeeRequest.getEmail());
        employee.setPhoneNumber(employeeRequest.getPhoneNumber());
        employee.setTelegramId(employeeRequest.getTelegramId());
        employee.setAddress(employeeRequest.getAddress());
        employee.setWife(employeeRequest.getWife());
        employee.setChild(employeeRequest.getChild());
        employee.setIsProvidentFund(employeeRequest.getIsProvidentFund());
        employee.setEmployeePensionPercentage(employeeRequest.getEmployeePensionPercentage());
        employee.setEmployerPensionPercentage(employeeRequest.getEmployerPensionPercentage());
        employee.setCurrentSalary(employeeRequest.getCurrentSalary());
        employee.setCreatedBy(userId);
        employee.setIsActive(1);
        Boolean result=employeeMapper.insert(employee);
        if(result){
            return ResponseMessageUtils.makeResponseByPermission(true, messageService.message("Success.", true));
        }else {
            return ResponseMessageUtils.makeResponseByPermission(true, messageService.message("Fail.", false));
        }
    }
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
    public ResponseMessage<BaseResult> update(EmployeeUpdateRequest employeeUpdateRequest) {
        Long userId = userService.getUserAuth().getId();

        //check Duplicate
        if (employeeMapper.checkDuplicateUpdate(employeeUpdateRequest.getEmployeeCode(),employeeUpdateRequest.getId())>0){
            return ResponseMessageUtils.makeResponse(true, messageService.message("Duplicate", false));
        }

        System.out.println(employeeUpdateRequest);
        //check data
        Employee employee=new Employee();
        employee.setId(employeeUpdateRequest.getId());
        employee.setUserId((employeeUpdateRequest.getUserId()));
        employee.setDepartmentId(employeeUpdateRequest.getDepartmentId());
        employee.setPositionId(employeeUpdateRequest.getPositionId());
        employee.setSendPayslipViaId(employeeUpdateRequest.getSendPayslipViaId());
        employee.setEmployeeStatusId(employeeUpdateRequest.getEmployeeStatusId());
        employee.setName(employeeUpdateRequest.getName());
        employee.setEmployeeCode(employeeUpdateRequest.getEmployeeCode());
        employee.setGender(employeeUpdateRequest.getGender());
        employee.setDateOfBirth(employeeUpdateRequest.getDateOfBirth());
        employee.setPhoto(employeeUpdateRequest.getPhoto());
        employee.setWg(employeeUpdateRequest.getWg());
        employee.setCostCenterId(employeeUpdateRequest.getCostCenterId());
        employee.setDateOfEmployee(employeeUpdateRequest.getDateOfEmployee());
        employee.setBaseLocation(employeeUpdateRequest.getBaseLocation());
        employee.setRegion(employeeUpdateRequest.getRegion());
        employee.setVehicleRemark(employeeUpdateRequest.getVehicleRemark());
        employee.setPayslipMethodId(employeeUpdateRequest.getPayslipMethodId());
        employee.setBankName(employeeUpdateRequest.getBankName());
        employee.setBankAccNumber(employeeUpdateRequest.getBankAccNumber());
        employee.setPensionAccountNumber(employeeUpdateRequest.getPensionAccountNumber());
        employee.setEmail(employeeUpdateRequest.getEmail());
        employee.setPhoneNumber(employeeUpdateRequest.getPhoneNumber());
        employee.setTelegramId(employeeUpdateRequest.getTelegramId());
        employee.setAddress(employeeUpdateRequest.getAddress());
        employee.setWife(employeeUpdateRequest.getWife());
        employee.setChild(employeeUpdateRequest.getChild());
        employee.setIsProvidentFund(employeeUpdateRequest.getIsProvidentFund());
        employee.setEmployeePensionPercentage(employeeUpdateRequest.getEmployeePensionPercentage());
        employee.setEmployerPensionPercentage(employeeUpdateRequest.getEmployerPensionPercentage());
        employee.setCurrentSalary(employeeUpdateRequest.getCurrentSalary());
        employee.setModifiedBy(getUserAuth().getId());
        employee.setIsActive(1);

        System.out.println(employee);
        Boolean result=employeeMapper.update(employee);

        if (result){
            return ResponseMessageUtils.makeResponse(true, messageService.message("Success", true));
        }else {
            return ResponseMessageUtils.makeResponse(true, messageService.message("Fail", false));
        }
    }

    @Override
    public ResponseMessage<BaseResult> delete(Long id) {
        Boolean result=employeeMapper.delete(id);
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
        pagination.setTotal(employeeMapper.checkTotal());

        filter.setPage((filter.getPage()-1)*filter.getRowsPerPage());
        List<EmployeeResponse> employeeResponseList=employeeMapper.list(filter);
        return ResponseMessageUtils.makeResponse(true, messageService.message("Success", employeeResponseList, true));

    }

    @Override
    public ResponseMessage<BaseResult> getOne(Long id) {
        List<EmployeeResponse> employeeResponses=employeeMapper.getOne(id);
        return ResponseMessageUtils.makeResponse(true, messageService.message("Success", employeeResponses, true));
    }
}
