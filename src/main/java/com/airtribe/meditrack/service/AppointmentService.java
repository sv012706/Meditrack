package com.airtribe.meditrack.service;

import com.airtribe.meditrack.entity.*;
import com.airtribe.meditrack.enums.AppointmentStatus;
import com.airtribe.meditrack.exception.AppointmentNotFoundException;
import com.airtribe.meditrack.factory.BillFactory;
import com.airtribe.meditrack.strategy.BillingStrategy;
import com.airtribe.meditrack.util.IdGenerator;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AppointmentService {
  private Map<String, Appointment>appointments=new HashMap<>();

  public AppointmentService(BillingStrategy billingStrategy) {
    this.billingStrategy = billingStrategy;
  }

  public void setBillingStrategy(BillingStrategy billingStrategy) {
    this.billingStrategy = billingStrategy;
  }

  private BillingStrategy billingStrategy;
  public  Appointment createAppointment(Patient patient, Doctor doctor, LocalDateTime time)
  {
    if(patient == null || doctor == null || time == null){
      throw new IllegalArgumentException("Invalid appointment data");
    }
    String id= IdGenerator.getInstance().GenerateId("API");
  Appointment appointment=new Appointment(id,doctor,patient,time, AppointmentStatus.CONFIRMED);
  appointments.put(id,appointment);
    System.out.println("Appointment booked successfully");
    return appointment;
  }
  public List<Appointment> getAppointments()
  {
    return new ArrayList<>(appointments.values());
  }
  public void cancelAppointment(String id)
  {
    if(id == null || id.isEmpty()){
      throw new IllegalArgumentException("Appointment id cannot be empty");
    }

    Appointment appointment = appointments.get(id);

    if(appointment == null){
      throw new AppointmentNotFoundException("Appointment not found");
    }

    if(appointment.getStatus() == AppointmentStatus.CANCELLED){
      throw new IllegalStateException("Appointment already cancelled");
    }

    appointment.cancelAppointment();
  }
  public BillSummary generateBill(String appointmentId)
  {
    Appointment appointment=appointments.get(appointmentId);
    if (appointment==null)
    {
      throw new AppointmentNotFoundException("Appointment not found");
    }
    double consultationFee=appointment.getDoctor().getConsultationFees();
    double amount=billingStrategy.calculate(consultationFee);
    Bill bill= BillFactory.createBill(IdGenerator.getInstance().GenerateId("BILL"),amount );
            return new BillSummary(bill.getBillId(), amount);
  }

}
