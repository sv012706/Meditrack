package com.airtribe.meditrack.util;

public class Validator {
  public static boolean isValidName(String name)
  {
    return name!=null&&name.length()>=2;
  }
  public static boolean isValidAge(int age)
  {
    return age>0 && age<110;
  }
}
