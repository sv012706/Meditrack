package com.airtribe.meditrack.util;

public class AiHelper {
  public static String recommendDoctor(String Symptom)
  {
    if(Symptom.equalsIgnoreCase("heart"))
    {
      return "Cardiologist";
    }
    if(Symptom.equalsIgnoreCase("skin"))
    {
      return "Dermatologist";
    }
    return "General Physician";
  }
}
