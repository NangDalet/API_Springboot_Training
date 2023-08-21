package com.ut.masterCode.service;

import com.ut.masterCode.helper.ResponseMessageUtils;
import com.ut.masterCode.helper.TelegramUtils;
import com.ut.masterCode.mapper.primary.ActivityLogMapper;
import com.ut.masterCode.mapper.primary.PermissionMapper;
import com.ut.masterCode.model.ActivityLog;
import com.ut.masterCode.model.MessageService;
import com.ut.masterCode.model.base.BaseResult;
import com.ut.masterCode.model.base.Pagination;
import com.ut.masterCode.model.base.ResponseMessage;
import com.ut.masterCode.model.filter.ActivityFilter;
import org.apache.http.HttpRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class ActivityLogServiceImpl implements ActivityLogService {

  @Autowired
  private ActivityLogMapper activityLogMapper;

  @Autowired
  Environment environment;

  @Autowired
  private UserService userService;

  @Autowired
  private ActivityLogService activityLogService;

  @Autowired
  private MessageService messageService;

  @Autowired
  private PermissionMapper permissionMapper;

  @Override
  public void insert(String endpoint, Long line, String bug, String moduleName, String moduleId, String act, int status, String description,LocalTime startDuration, LocalTime endDuration, HttpServletRequest httpServletRequest) throws UnknownHostException {
    // Check User Id
    Long userId = userService.getUserAuth().getId();
    HttpRequest httpRequest = null;
    InetAddress addr = InetAddress.getLocalHost();
    String ip = addr.getHostAddress();
    String hostname = addr.getHostName();
    String operSys = System.getProperty("os.name").toLowerCase();
    String userAgent= httpServletRequest.getHeader("user-agent");
    String browserName = "";
    String  browserVer = "";

    if(userAgent.contains("Chrome")){ //checking if Chrome
      String substring=userAgent.substring(userAgent.indexOf("Chrome")).split(" ")[0];
      browserName=substring.split("/")[0];
    }
    else if(userAgent.contains("Firefox")){  //Checking if Firefox
      String substring=userAgent.substring(userAgent.indexOf("Firefox")).split(" ")[0];
      browserName=substring.split("/")[0];
    }

    Duration d = Duration.between(LocalTime.now(), LocalTime.parse("12:00:12"));

    /*GET module Id*/
    Long getModuleId = activityLogMapper.getModuleId("Payroll (View)");

    /*GET Username*/
    String getUsername = activityLogMapper.getFullName(userId);

    /*Get Duration*/
    Duration duration = Duration.between(startDuration, endDuration);
    Long durationProcess = duration.get(ChronoUnit.SECONDS);

    // Check Data
    ActivityLog activityLog = new ActivityLog();
    activityLog.setEndpoint(endpoint);
    activityLog.setLine(line);
    activityLog.setModule(moduleName);
    activityLog.setModuleId(getModuleId);
    activityLog.setAct(act);
    activityLog.setDescription(description);
    activityLog.setBug(bug);
    activityLog.setBrower(browserName);
    activityLog.setOperatingSystem(operSys);
    activityLog.setIp(ip);
    activityLog.setDuration(durationProcess);
    activityLog.setCreatedBy(userId);
    activityLog.setStatus(status);
    activityLog.setHostName(hostname);
    Boolean result = activityLogMapper.insert(activityLog);

    /*Push Telegram*/
    if(status == 2) {
      String messageBug;
      messageBug = bug;
      if(messageBug != null && messageBug.length()>= 49) {
        messageBug = messageBug.substring(0,48).replace(" ","").replace(":","")
                .replace("\n","").replace("#","").replace("\\", "")
                .replace("?","").toLowerCase();
      } 

      if(messageBug.equalsIgnoreCase("org.springframework.jdbc.badsqlgrammarexception")) {
        messageBug = "Error SQL";
      }

      if(messageBug.equalsIgnoreCase("java.lang.NullPointerException")) {
        messageBug = "Variable java Null";
      }

      if(messageBug.equalsIgnoreCase("org.springframework.dao.dataintegrityviolationex")) {
        messageBug = "Variable sql can not be null";
      }

      if(messageBug.equalsIgnoreCase("java.lang.numberformatexceptionforinputstrin")) {
        messageBug = "Invalid value from excel";
      }

      if(messageBug.equalsIgnoreCase("org.mybatis.spring.mybatissystemexceptionneste")) {
        messageBug = "selectOne(), but found: 3";
      }

      /*DateTime*/
      LocalDateTime now = LocalDateTime.now();

      TelegramUtils.sendMessage(
              "<b><i> Structure Project APi </i></b> %0A %0A" +
                      "<b> System Activity ID:</b> " + activityLog.getId() +
                      "%0A <b>From:</b> <u>" + environment.getProperty("server.from") +"</u>"+
                      "%0A <b>DateTime:</b> " + now +
                      "%0A <b>Hostname:</b> " + hostname +
                      "%0A <b>Endpiont:</b> <u>" + endpoint +"</u>" +
                      "%0A <b>Username:</b> " + getUsername +
                      "%0A <b>Duration:</b> " + durationProcess + " SECONDS"+
                      "%0A <b>Line Code:</b> "+ line +
                      "%0A <b>Module name:</b> "+ moduleId +
                      "%0A <b>Description:</b> "+ description +
                      "%0A <b>Message:</b> "+ messageBug, "-1001819780053");
    }
  }

  public ResponseMessage<BaseResult> getList(ActivityFilter filter, HttpServletRequest httpServletRequest) throws UnknownHostException {
    LocalTime startDuration = LocalTime.now();
    Long line = 1033L;
    try {

      Pagination pagination = new Pagination();
      pagination.setPage(filter.getPage());
      pagination.setRowsPerPage(filter.getRowsPerPage());
      pagination.setTotal(activityLogMapper.countList(filter));
      filter.setPage((filter.getPage() - 1) * filter.getRowsPerPage());

      List<ActivityLog> activityLogs = activityLogMapper.getList(filter);
      /*System Activity*/
      LocalTime endDuration = LocalTime.now();
      activityLogService.insert("/system-activity/list",null,null,"System Activity","System Activity (View)","View",1,"Success",startDuration,endDuration, httpServletRequest);
      return ResponseMessageUtils.makeResponse(true, messageService.message("Success", activityLogs, pagination, true));
    } catch (Exception error) {
      /*System Activity*/
      LocalTime endDuration = LocalTime.now();
      activityLogService.insert("/system-activity/list",line, error.toString(),"System Activity","System Activity (View)","View",2,"Error",startDuration,endDuration, httpServletRequest);
      return ResponseMessageUtils.makeResponse(true, messageService.message("Error", null, false));
    }
  }

  public ResponseMessage<BaseResult> getOne(Long id, HttpServletRequest httpServletRequest) throws UnknownHostException {
    LocalTime startDuration = LocalTime.now();
    Long line = 1033L;
    try {
      List<ActivityLog> activityLogs = activityLogMapper.getOne(id);
      /*System Activity*/
      LocalTime endDuration = LocalTime.now();
      activityLogService.insert("/system-activity/find/{id}",null,null,"Costcenter","Costcenter (View)","View",1,"Success",startDuration,endDuration, httpServletRequest);
      return ResponseMessageUtils.makeResponse(true, messageService.message("Success", activityLogs, true));
    }catch (Exception error) {
      /*System Activity*/
      LocalTime endDuration = LocalTime.now();
      activityLogService.insert("/system-activity/find/{id}",line, error.toString(),"Costcenter","Costcenter (View)","View",2,"Error",startDuration,endDuration, httpServletRequest);
      return ResponseMessageUtils.makeResponse(true, messageService.message("Error", null, false));
    }
  }
}
