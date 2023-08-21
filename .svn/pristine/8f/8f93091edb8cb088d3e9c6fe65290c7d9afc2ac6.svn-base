package com.ut.masterCode.exception;

import com.ut.masterCode.helper.ResponseMessageUtils;
import com.ut.masterCode.model.base.ResponseMessage;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RestController
public class CustomResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler(Exception.class)
  public final ResponseMessage handleAllExceptions(Exception ex, WebRequest request) {
    return ResponseMessageUtils.makeResponse(false, 200, "", ex.getMessage());
  }
}
