package com.ut.masterCode.service;

import com.ut.masterCode.model.base.BaseResult;
import com.ut.masterCode.model.base.ResponseMessage;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.net.UnknownHostException;

public interface FileUploadService {

  ResponseMessage<BaseResult> insert(MultipartFile file, HttpServletRequest httpServletRequest) throws UnknownHostException;

}