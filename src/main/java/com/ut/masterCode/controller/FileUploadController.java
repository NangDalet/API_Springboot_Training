package com.ut.masterCode.controller;


import com.ut.masterCode.base.UserAuthSession;
import com.ut.masterCode.helper.ResponseMessageUtils;
import com.ut.masterCode.model.base.BaseResult;
import com.ut.masterCode.model.base.ResponseMessage;
import com.ut.masterCode.service.FileUploadService;
import io.micrometer.core.annotation.Timed;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.net.UnknownHostException;

@RestController
@RequestMapping("/file")
@Api(tags = "15. File", description = "File Resource")
@Timed
public class FileUploadController {

    @Autowired
    private FileUploadService fileUploadService;

    @PostMapping(value = "/upload-file", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ApiOperation(value = "Upload Photo (List)", notes = "statusCode: 400: Bad Request (Invalid Parameter); 401: Unauthorized (Token Expired or Invalid); 200: Success", authorizations = {@Authorization(value = "Bearer")})
    public ResponseMessage<BaseResult> uploadImage(@RequestPart(name = "file", required = false) MultipartFile file, HttpServletRequest httpServletRequest) throws UnknownHostException {

        // Check Header Token
        if (UserAuthSession.getUserAuth() == null) {
            return ResponseMessageUtils.makeResponse(false, 401, "unauthorized", "No Permission to access");
        }
        return fileUploadService.insert(file,httpServletRequest);
    }

}
