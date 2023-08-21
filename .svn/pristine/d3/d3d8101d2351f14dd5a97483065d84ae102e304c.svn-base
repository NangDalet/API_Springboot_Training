package com.ut.masterCode.helper;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidateEmail {

  private ValidateEmail() {

  }

  public static final Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
  public static final Pattern VALID_DATE_REGEX = Pattern.compile("^\\d{4}-\\d{2}-\\d{2}$", Pattern.CASE_INSENSITIVE);

  public static boolean email(String email) {
    Matcher matcher = VALID_EMAIL_ADDRESS_REGEX .matcher(email);
    return matcher.find();
  }

  public static boolean date(String date) {
    Matcher matcher = VALID_DATE_REGEX .matcher(date);
    return matcher.find();
  }

}