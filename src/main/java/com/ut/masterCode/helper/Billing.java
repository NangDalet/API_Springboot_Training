package com.ut.masterCode.helper;

import com.ut.masterCode.model.Billing.BillingResponse;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Billing {

  private static String billingUrl   = "http://apicamset.cam-ticket.com/UTbS/";
  private static String securityCode = "";

  public static Boolean userLogin(String username, String password) {
    Boolean result = false;
    String bodyResponse = "";
    try {
      // Set header
      Map<String, String> headers = new HashMap<>();
      headers.put("Accept", "*/*");
      PostMultiPart multipart = new PostMultiPart(billingUrl+"system-login/login", "utf-8", headers);
      // Add form field
      multipart.addFormField("securityCode", securityCode);
      multipart.addFormField("typeId", "1");
      multipart.addFormField("username", username);
      multipart.addFormField("password", password);
      // Add file
//      multipart.addFilePart("imgFile", new File("/Users/apple/Desktop/test.png"));
      // Print result
      bodyResponse = multipart.finish();
    } catch (Exception e) {
      e.printStackTrace();
    }
    if(!bodyResponse.isEmpty()){
      System.out.println(bodyResponse);
      BillingResponse billingResponse = convertResponse(bodyResponse);
      if(billingResponse.getHeader().getResult() == true){
        if(billingResponse.getBody().getStatus() == 1 && billingResponse.getBody().getExpired() != null){
          int checkExpired = checkExpired(billingResponse.getBody().getExpired());
          if(checkExpired <= 0){ // date now smaller than expiry date
            result = true;
          }
        }
      }
    }
    return result;
  }

  public static Boolean updateUser(String sysCode, String firstName, String lastName, String username, String password, String isActive) {
    Boolean result = false;
    String bodyResponse = "";
    try {
      // Set header
      Map<String, String> headers = new HashMap<>();
      headers.put("Accept", "*/*");
      PostMultiPart multipart = new PostMultiPart(billingUrl+"syn-update-user/update-profile", "utf-8", headers);
      // Add form field
      multipart.addFormField("securityCode", securityCode);
      multipart.addFormField("sysCode", sysCode);
      multipart.addFormField("firstName", firstName);
      multipart.addFormField("lastName", lastName);
      multipart.addFormField("username", username);
      multipart.addFormField("password", password);
      multipart.addFormField("isActive", isActive);
      // Print result
      bodyResponse = multipart.finish();
    } catch (Exception e) {
      e.printStackTrace();
    }
    if(!bodyResponse.isEmpty()){
      BillingResponse billingResponse = convertResponse(bodyResponse);
      if(billingResponse.getHeader().getResult() == true){
        if(billingResponse.getBody().getStatus() == 1){
          result = true;
        }
      }
    }
    return result;
  }

  private static BillingResponse convertResponse(String bodyResponse){
    BillingResponse billingResponse = new BillingResponse();
    ObjectMapper objectMapper = new ObjectMapper();
    try {
      billingResponse = objectMapper.readValue(bodyResponse, BillingResponse.class);
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return billingResponse;
  }

  public static int checkExpired(String dateInput){
    SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy-MM-dd");
    Date now = new Date();
    int response = 1;
    try {
      String date   = sdFormat.format(now);
      Date dateNow  = sdFormat.parse(date);
      Date dateCompare = sdFormat.parse(dateInput);
      response = dateNow.compareTo(dateCompare); // -1: Smaller; 0: Equal; 1: Bigger
    } catch (ParseException e) {
      e.printStackTrace();
    }
    return response;
  }

}