package com.ut.masterCode.service;

import com.ut.masterCode.helper.FileUploadUtils;
import com.ut.masterCode.helper.ResponseMessageUtils;
import com.ut.masterCode.mapper.primary.PermissionMapper;
import com.ut.masterCode.model.MessageService;
import com.ut.masterCode.model.base.BaseResult;
import com.ut.masterCode.model.base.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.net.UnknownHostException;
import java.time.LocalTime;

@Service
public class FileUploadServiceImpl implements FileUploadService {


    @Autowired
    private UserService userService;

    @Autowired
    private MessageService messageService;

    @Autowired
    private PermissionMapper permissionMapper;

    @Autowired
    private ActivityLogService activityLogService;

    @Autowired
    Environment environment;

    @Override
    public ResponseMessage<BaseResult> insert(MultipartFile file, HttpServletRequest httpServletRequest) throws UnknownHostException {
        LocalTime startDuration = LocalTime.now();
        Long line = 1033L;
        try {
            // Check Permission
            Long userId = userService.getUserAuth().getId();
            String filePhoto = "";
            // upload Profile Photo

            System.out.println("File "+ file);
            if (file!= null){
                filePhoto = FileUploadUtils.saveFileUploaded(file,  "logs/" + environment.getProperty("server.servlet.context-path"));
                System.out.println(filePhoto);
            }

            Boolean result = true;
            /*System Activity*/
            LocalTime endDuration = LocalTime.now();
            activityLogService.insert("/file/insert",null,null,"","","",1,"Success",startDuration,endDuration, httpServletRequest);
            if (result) {
                return ResponseMessageUtils.makeResponse(true, messageService.message(filePhoto, true));
            } else {
                return ResponseMessageUtils.makeResponse(true, messageService.message("Fail", false));
            }
        } catch (Exception error) {
            /*System Activity*/
            LocalTime endDuration = LocalTime.now();
            activityLogService.insert("/file/insert",line, error.toString(),"","","",2,"Error",startDuration,endDuration, httpServletRequest);
            return ResponseMessageUtils.makeResponse(true, messageService.message("Error", null, false));
        }
    }


}
