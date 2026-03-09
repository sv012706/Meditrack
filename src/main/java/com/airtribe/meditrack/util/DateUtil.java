package com.airtribe.meditrack.util;

import com.airtribe.meditrack.constants.Constants;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateUtil {
  private static  final DateTimeFormatter formatter=DateTimeFormatter.ofPattern(Constants.dateFormat);
  public static String format(LocalDateTime dateTime)
  {
    return dateTime.format(formatter);
  }
  public static LocalDateTime parse(String date)
  {
    return LocalDateTime.parse(date,formatter);
  }
}
