package com.airtribe.meditrack.service;

import com.airtribe.meditrack.entity.Patient;
import com.airtribe.meditrack.exception.DuplicateException;
import com.airtribe.meditrack.exception.InvalidDataException;

import java.util.*;

public class PatientService {
  private Map<String, Patient> patientMap=new HashMap<>();
  public void addPatient(Patient patient)
  {
    if(patient==null)
    {
      throw new InvalidDataException("Patient data invalid");
    }
    if(patient.getId()==null|| patient.getId().isEmpty())
    {
      throw new InvalidDataException("patient id cannot be empty");
    }
    if(patientMap.containsKey(patient.getId()))
    {
      throw new DuplicateException("Already Patient exists: "+patient.getId());
    }
    patientMap.put(patient.getId(),patient);
  }
  public Patient searchPatient(String id)
  {
    if(id==null||id.isEmpty())
    {
      throw new InvalidDataException("Id cannot be null");
    }
    Patient patient=patientMap.get(id);
    if(patient==null)
    {
      throw new InvalidDataException("patient not found with this id: "+id);
    }
    return patient;
  }
  public List<Patient> searchByPatientName(String name)
  {
    if(name==null||name.trim().isEmpty())
    {
      throw new InvalidDataException("name cannot be null");
    }
    return patientMap.values()
            .stream().
            filter(p->p.getName().equalsIgnoreCase(name))
            .toList();
  }
  public List<Patient> searchByAge(int age){

    if(age <= 0){
      throw new InvalidDataException("Age must be positive");
    }

    return patientMap.values()
            .stream()
            .filter(p -> p.getAge() == age)
            .toList();
  }

  public List<Patient> getAllPatients()
  {
    return new ArrayList<>(patientMap.values());
  }
}
