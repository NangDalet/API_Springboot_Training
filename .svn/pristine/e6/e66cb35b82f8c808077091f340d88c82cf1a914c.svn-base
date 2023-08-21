package com.ut.masterCode.helper;

import com.ut.masterCode.model.base.BaseResult;
import com.ut.masterCode.model.base.Pagination;
import com.ut.masterCode.model.base.ResponseHeader;
import com.ut.masterCode.model.base.ResponseMessage;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.HashMap;
import java.util.Map;

public abstract class ResponseMessageUtils {

    /**
     * Make success response
     *
     * @param body
     * @return ResponseMessage<T>
     */
    public static <T> ResponseMessage<T> makeSuccessResponse(T body) {
        ResponseMessage<T> response = new ResponseMessage<>();

        ResponseHeader header = response.getHeader();
        header.setResult(true);
        header.setStatusCode(HttpStatus.OK.value());

        if (body instanceof Boolean) {
            return response;
        }

        response.setBody(body);

        return response;
    }

    /**
     * Make success response
     *
     * @param body
     * @param token
     * @param pagination
     * @return ResponseMessage<T>
     */
    public static <T> ResponseMessage<T> makeSuccessResponse(T body, String token, Pagination pagination) {
        ResponseMessage<T> response = new ResponseMessage<>();

        ResponseHeader header = response.getHeader();
        header.setResult(true);
        header.setStatusCode(HttpStatus.OK.value());
        header.setToken(token);
        header.setPagination(pagination);

        if (body instanceof Boolean) {
            return response;
        }

        response.setBody(body);

        return response;
    }

    /**
     * Make response with defining success status
     *
     * @param success
     * @param body
     * @return ResponseMessage<T>
     */
    public static <T> ResponseMessage<T> makeResponse(boolean success, T body) {
        ResponseMessage<T> response = new ResponseMessage<>();

        ResponseHeader header = response.getHeader();
        header.setResult(success);
        header.setStatusCode(HttpStatus.OK.value());

        response.setBody(body);

        return response;
    }

    /**
     * Make response with defining success status
     *
     * @param success
     * @param errorText
     * @param statusCode
     * @return ResponseMessage<T>
     */
    public static <T> ResponseMessage<T> makeResponse(boolean success, int statusCode, String errorCode, String errorText) {
        ResponseMessage<T> response = new ResponseMessage<>();

        ResponseHeader header = response.getHeader();
        header.setResult(success);
        header.setStatusCode(statusCode);
        header.setErrorCode(errorCode);
        header.setErrorText(errorText);

        return response;
    }

    /**
     * Make response with defining success status
     *
     * @param success
     * @param bindingResult
     * @return ResponseMessage<T>
     */
    public static <T> ResponseMessage<T> makeResponse(boolean success, BindingResult bindingResult) {
        ResponseMessage<T> response = new ResponseMessage<>();

        Map<String, String> errorMessageList = new HashMap<>();
        for (Object object : bindingResult.getAllErrors()) {
            FieldError fieldError = (FieldError) object;
            errorMessageList.put(fieldError.getField(), fieldError.getDefaultMessage());
        }

        ResponseHeader header = response.getHeader();
        header.setResult(success);
        header.setStatusCode(HttpStatus.BAD_REQUEST.value());
        header.setErrorText(new JSONObject(errorMessageList).toString());

        return response;
    }

    /**
     * Make response with defining success status
     *
     * @param success
     * @param baseResult
     * @return ResponseMessage<T>
     */
    public static <T> ResponseMessage<T> makeResponse(boolean success, BaseResult baseResult) {
        ResponseMessage<T> response = new ResponseMessage<>();
        ResponseHeader header = response.getHeader();
        header.setResult(success);
        header.setStatusCode(HttpStatus.OK.value());
        response.setBody((T) baseResult);

        return response;
    }

    public static <T> ResponseMessage<T> makeResponseByPermission(boolean success, BaseResult baseResult) {
        ResponseMessage<T> response = new ResponseMessage<>();
        ResponseHeader header = response.getHeader();
        header.setResult(success);
        header.setStatusCode(403);
        response.setBody((T) baseResult);

        return response;
    }

}