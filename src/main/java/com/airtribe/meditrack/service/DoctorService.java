package com.airtribe.meditrack.service;

import com.airtribe.meditrack.constants.Constants;
import com.airtribe.meditrack.entity.Doctor;
import com.airtribe.meditrack.enums.Specialization;
import com.airtribe.meditrack.exception.DuplicateException;
import com.airtribe.meditrack.exception.InvalidDataException;
import com.airtribe.meditrack.util.CSVUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class DoctorService {
  private static DoctorService instance;
  private Map<String, Doctor> doctorMap=new HashMap<>();
  private DoctorService(){}
  public static DoctorService getInstance()
  {
    if(instance==null)
    {
      instance=new DoctorService();
    }
    return instance;
  }
  public void addDoctor(Doctor doctor)
  {
    if(doctor==null) {
      throw new InvalidDataException("Doctor data invalid");
    }
    if(doctor.getId()==null||doctor.getId().isEmpty())
    {
      throw new InvalidDataException("Doctor Id cannot be empty");
    }
    if(doctorMap.containsKey(doctor.getId()))
    {
      throw new DuplicateException("Doctor already exists: "+doctor.getId());
    }
    doctorMap.put(doctor.getId(), doctor);
  }
  public List<Doctor> getAllDoctors()
  {
    return new ArrayList<>(doctorMap.values());
  }
  public List<Doctor>findBySpecialization(Specialization specialization)
  {
    if(specialization==null)
    {
      throw new InvalidDataException("Specialization cannot be null");
    }
    return doctorMap.values().stream().filter(spec->spec.getSpecialization()==specialization)
            .collect(Collectors.toList());
  }
  public void removeDoctor(String id)
  {
    if(!doctorMap.containsKey(id))
    {
      throw new InvalidDataException("Doctor not found");
    }
    doctorMap.remove(id);
  }
  public void saveDoctorsToCSV() {
    try {
      List<String> lines = new ArrayList<>();
      for (Doctor d : doctorMap.values()) {
        String line =
                d.getId() + "," +
                        d.getName() + "," +
                        d.getAge() + "," +
                        d.getSpecialization() + "," +
                        d.getConsultationFees();
        lines.add(line);
      }
      CSVUtil.writeCSV(Constants.doctorFile, lines);

    } catch (Exception e) {
      System.out.println("Error saving doctors: " + e.getMessage());

    }
  }
    public void loadDoctorsFromCSV() {

      try {

        List<String[]> data = CSVUtil.readCSV(Constants.doctorFile);

        for(String[] row : data){

          Doctor doctor = new Doctor(
                  row[0],
                  row[1],
                  Integer.parseInt(row[2]),
                  Specialization.valueOf(row[3]),
                  Double.parseDouble(row[4])
          );

          doctorMap.put(doctor.getId(), doctor);
        }

      }
      catch(Exception e){

        System.out.println("Error loading doctors");


      }
  }
}
