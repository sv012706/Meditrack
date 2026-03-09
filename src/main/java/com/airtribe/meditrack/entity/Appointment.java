package com.airtribe.meditrack.entity;

import com.airtribe.meditrack.enums.AppointmentStatus;

import java.time.LocalDateTime;

public class Appointment implements Cloneable{
  private String appointmentID;
  private Doctor doctor;
  private Patient patient;
  private LocalDateTime appointmentTime;
  private AppointmentStatus status;


  public Appointment(String appointmentID, Doctor doctor, Patient patient, LocalDateTime appointmentTime, AppointmentStatus status) {
    this.appointmentID = appointmentID;
    this.doctor = doctor;
    this.patient = patient;
    this.appointmentTime = appointmentTime;
    this.status = status;
  }

  public String getAppointmentID() {
    return appointmentID;
  }

  public void setAppointmentID(String appointmentID) {
    this.appointmentID = appointmentID;
  }

  public Doctor getDoctor() {
    return doctor;
  }

  public void setDoctor(Doctor doctor) {
    this.doctor = doctor;
  }

  public Patient getPatient() {
    return patient;
  }

  public void setPatient(Patient patient) {
    this.patient = patient;
  }

  public LocalDateTime getAppointmentTime() {
    return appointmentTime;
  }

  public void setAppointmentTime(LocalDateTime appointmentTime) {
    this.appointmentTime = appointmentTime;
  }

  public AppointmentStatus getStatus() {
    return status;
  }

  public void cancelAppointment() {
    this.status = AppointmentStatus.CANCELLED;
  }

  @Override
  protected Appointment clone() {
    try {
      Appointment copy = (Appointment) super.clone();
      copy.patient = patient.clone();
      return copy;
    } catch (CloneNotSupportedException e) {
      throw new RuntimeException("clone failed");
    }
  }
}
