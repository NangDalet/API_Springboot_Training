package com.ut.masterCode.model;

import com.ut.masterCode.model.base.BaseResult;
import com.ut.masterCode.model.base.Pagination;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageService {

  public BaseResult message(String text, List array, Boolean status) {
    BaseResult baseResult = new BaseResult();
    baseResult.setMessage(text);
    baseResult.setStatus(status);
    baseResult.setData(array);
    return baseResult;
  }

  public BaseResult message(String text, List array, Pagination pagination, Boolean status) {
    BaseResult baseResult = new BaseResult();
    baseResult.setMessage(text);
    baseResult.setStatus(status);
    baseResult.setData(array);
    baseResult.setPagination(pagination);
    return baseResult;
  }

  public BaseResult message(String text, Boolean status) {
    BaseResult baseResult = new BaseResult();
    baseResult.setMessage(text);
    baseResult.setStatus(status);
    return baseResult;
  }

  public BaseResult message(String text, Boolean status, Long id) {
    BaseResult baseResult = new BaseResult();
    baseResult.setMessage(text);
    baseResult.setStatus(status);
    baseResult.setId(id);
    return baseResult;
  }
}