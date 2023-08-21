package com.ut.masterCode.helper;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class PushNotificationOneSignal {

  //!     https://onesignal.com/api/v1/notifications
  public static final String REST_API_KEY = "MjM0NTA4N2MtOTdjOC00ODg0LTkyNzMtMTBiMjQ3NTM4NzI2";

  //! Work Permit
//  public static final String APP_ID = "586c45ab-1051-4533-8fa6-62b51ac89abb";

  //! UT-EMR Mobile
  public static final String APP_ID = "0c6361b0-aa7c-4835-b920-f69b3d098140";

  public static void pushAndroid(String deviceToken, String title, String body) {
    try {

      URL url = new URL("https://onesignal.com/api/v1/notifications");
      HttpURLConnection con = (HttpURLConnection)url.openConnection();
      con.setUseCaches(false);
      con.setDoOutput(true);
      con.setDoInput(true);

      con.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
      con.setRequestProperty("Authorization","Basic " + REST_API_KEY); //REST API
      con.setRequestMethod("POST");


      Long idNotification = 1l;
      Long typeNotification = 1l;
      String strJsonBody = "{"
              +   "\"app_id\": \""+ APP_ID +"\","

              +   "\"include_player_ids\": [\""+ deviceToken +"\"],"

              +   "\"headings\": {\"en\": \""+title+"\"},"

              +   "\"contents\": {\"en\": \""+ body +"\"},"

              +   "\"data\": { \"type\": \"1\"}"

              + "}";

      System.out.println(strJsonBody);
      byte[] sendBytes = strJsonBody.getBytes(StandardCharsets.UTF_8);
      con.setFixedLengthStreamingMode(sendBytes.length);

      OutputStream outputStream = con.getOutputStream();
      outputStream.write(sendBytes);

      int httpResponse = con.getResponseCode();

    } catch(Throwable t) {
      t.printStackTrace();
    }
  }

}