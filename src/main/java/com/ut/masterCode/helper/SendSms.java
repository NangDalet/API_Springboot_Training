package com.ut.masterCode.helper;

import org.json.JSONException;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

public class SendSms {

  public static Boolean sendSmsApi(String phoneNumber, String sms, String code) {
    String smsApi = "http://mvar6dpxpbj3wuxd.udaya.asia:8095/SmSGateW/sms/send-by-one";
    try {
      try {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("tc", "ut5c4bb:ade99:e247acc62:");
        map.add("sc", "ea9169c69f45e80d9d3e8d2c4a509953");
        map.add("num", phoneNumber);
        map.add("msg", sms);
        map.add("sendId", code);
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);
        ResponseEntity<String> response = restTemplate.postForEntity(smsApi, request, String.class);
        System.out.println(response);
        return true;
      } catch (Exception e) {
        return false;
      }
    } catch (JSONException e) {
      e.printStackTrace();
      return false;
    }
  }

}