package com.airtribe.meditrack.util;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CSVUtil {
  public static List<String[]>readCSV(String filepath) throws IOException {
    List<String[]> records=new ArrayList<>();
    BufferedReader reader=new BufferedReader(new FileReader(filepath));
    String line;
    while((line=reader.readLine())!=null)
    {
      records.add(line.split(","));
    }
    reader.close();
    return records;
  }
  public static void writeCSV(String filepath,List<String>lines) throws IOException {
    BufferedWriter writer=new BufferedWriter(new FileWriter(filepath));
    for(String line:lines)
    {
      writer.write(line);
      writer.newLine();
    }
    writer.close();

  }
}
