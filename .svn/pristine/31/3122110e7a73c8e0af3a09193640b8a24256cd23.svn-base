package com.ut.masterCode.helper;


import java.io.IOException;
import java.util.Random;

public class RandomNumber {

    public static String generatePasswordPDF() throws IOException {
        String rechaptcha;

        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 6) { // length of the random string.
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        rechaptcha = salt.toString();

        return rechaptcha;
    }
}
