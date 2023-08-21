package com.ut.masterCode.notification;

import com.ut.masterCode.helper.ResponseMessageUtils;
import com.ut.masterCode.model.base.ResponseMessage;
import com.ut.masterCode.model.notification.DeviceToken;
import com.ut.masterCode.model.notification.PushMessage;
import io.micrometer.core.annotation.Timed;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

@RestController
@RequestMapping("/push-server")
@Api(tags = "05. Push Notification", description = "Push Notification Resource")
@Timed
public class PushController {
    @Autowired
  Environment environment;

  public enum PushType {
    DEV,
    PROD
  }

    @PostMapping("/dev/send")
    @ApiOperation(value = "publicPushServerDevSend")
    public ResponseMessage<String> publicPushServerDevSend(@RequestBody PushMessage pushMessage) throws IOException {
        for (DeviceToken deviceToken : pushMessage.getDeviceTokens()) {
            System.out.println(deviceToken);
            switch (deviceToken.getType()) {
                case ANDROID:
                case IOS:
                    pushAndroid(deviceToken.getToken(), pushMessage, PushType.DEV);
                    break;
                case WEB:
                    break;
            }
        }
        return ResponseMessageUtils.makeSuccessResponse("OK", null, null);
    }

    @PostMapping("/prod/send")
    @ApiOperation(value = "publicPushServerProdSend")
    public ResponseMessage<String> publicPushServerProdSend(@RequestBody PushMessage pushMessage) throws IOException {
        for (DeviceToken deviceToken : pushMessage.getDeviceTokens()) {
            switch (deviceToken.getType()) {
                case ANDROID:
                case IOS:
                    pushAndroid(deviceToken.getToken(), pushMessage, PushType.PROD);
                    break;
                case WEB:
                    break;
            }
        }
        return ResponseMessageUtils.makeSuccessResponse("OK", null, null);
    }

    // https://onesignal.com/api/v1/notifications
    public static final String REST_API_KEY = "ODNhMzdmZDMtNDMwNS00MDBiLWIwNmEtN2VlZDZkNWIzYTJi";
    public static final String APP_ID = "4500b090-399f-402e-8047-c3436e92c04c";

    public void pushAndroid(String deviceToken, PushMessage pushMessage, PushType pushType) {

        try {

            URL url = new URL("https://onesignal.com/api/v1/notifications");
            HttpURLConnection con = (HttpURLConnection)url.openConnection();
            con.setUseCaches(false);
            con.setDoOutput(true);
            con.setDoInput(true);

            con.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            con.setRequestProperty("Authorization","Basic "+REST_API_KEY); //REST API
            con.setRequestMethod("POST");

            String bodySMS = pushMessage.getBody();
            String title = pushMessage.getTitle();
            Long badge = pushMessage.getBadge();
            String launchUrl = "https://qacltom.udaya-tech.com/notifications";
            Long idNotification = pushMessage.getNotificationId();
            Long typeNotification = pushMessage.getNotificationType();
            String strJsonBody = "{"
                    +   "\"app_id\": \""+ APP_ID +"\","

                    +   "\"url\": \""+ launchUrl +"\","

                    +   "\"include_player_ids\": [\""+ deviceToken +"\"],"

                    //+   "\"data\": {\"badge\":[\""+badge+"\"]},"

                    +   "\"headings\": {\"en\": \""+title+"\"},"

                    +   "\"contents\": {\"en\": \""+bodySMS+"\"},"

                    +   "\"data\": { \"idNotification\": \""+idNotification+"\", \"typeNotification\": \""+typeNotification+"\"}"

                    + "}";

            System.out.println("strJsonBody:\n" + strJsonBody);

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

