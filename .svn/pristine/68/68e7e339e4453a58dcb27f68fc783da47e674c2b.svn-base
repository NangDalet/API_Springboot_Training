package com.ut.masterCode.helper;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;

public class TelegramUtils {

    private TelegramUtils() {}

    public static Boolean sendMessage(String sendMessage, String chatId) {
        String urlString = "https://api.telegram.org/bot%s/sendMessage?chat_id=%s&text=%s&parse_mode=%s";

        //Add Telegram token
        String apiToken = "5037681481:AAEjHkKHndPd8Fi9bS1pgWG7J6VSHvZ49sE";

        //Add chatId
//        String chatId = "846658599";
        String html = "html";

        urlString = String.format(urlString, apiToken, chatId, sendMessage, html);
        try {
            URL url = new URL(urlString);
            URLConnection conn = url.openConnection();


            StringBuilder sb = new StringBuilder();
            InputStream is = new BufferedInputStream(conn.getInputStream());
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String inputLine;
            while ((inputLine = br.readLine()) != null) {
                sb.append(inputLine);
            }
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean sendMessageWithFile(String file, String chatId) {
        String urlString = "https://api.telegram.org/bot%s/sendDocument?chat_id=%s&document=%s";
        //Add Telegram token
        String apiToken = "5037681481:AAEjHkKHndPd8Fi9bS1pgWG7J6VSHvZ49sE";

        urlString = String.format(urlString, apiToken, chatId, file);
        try {
            URL url = new URL(urlString);
            URLConnection conn = url.openConnection();


            StringBuilder sb = new StringBuilder();
            InputStream is = new BufferedInputStream(conn.getInputStream());
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String inputLine;
            while ((inputLine = br.readLine()) != null) {
                sb.append(inputLine);
            }
            return  true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

}
