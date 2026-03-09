package com.airtribe.meditrack;


import com.airtribe.meditrack.constants.Constants;
import com.airtribe.meditrack.constants.MenuOptions;
import com.airtribe.meditrack.entity.Appointment;
import com.airtribe.meditrack.entity.BillSummary;
import com.airtribe.meditrack.entity.Doctor;
import com.airtribe.meditrack.entity.Patient;
import com.airtribe.meditrack.enums.Specialization;
import com.airtribe.meditrack.service.AppointmentService;
import com.airtribe.meditrack.service.DoctorService;
import com.airtribe.meditrack.service.PatientService;
import com.airtribe.meditrack.strategy.InsuranceBillingStrategy;
import com.airtribe.meditrack.strategy.SeniorCitizenBillingStrategy;
import com.airtribe.meditrack.strategy.StandardBillingStrategy;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;
import java.util.logging.Logger;


public class Main {
  private static Scanner scanner = new Scanner(System.in);
  private static final Logger logger = Logger.getLogger(Main.class.getName());
  private static DoctorService doctorService = DoctorService.getInstance();
  private static PatientService patientService = new PatientService();
  private static AppointmentService appointmentService =
          new AppointmentService(new StandardBillingStrategy());


  public static void main(String[] args) {
    doctorService.loadDoctorsFromCSV();
   System.out.println(Constants.App_Name);
       while (true) {

         System.out.println("\n====== MediTrack Clinic System ======");
         System.out.println("1. Add Doctor");
         System.out.println("2. Add Patient");
         System.out.println("3. Book Appointment");
         System.out.println("4. View Appointments");
         System.out.println("5. Cancel Appointment");
         System.out.println("6. Generate Bill");
         System.out.println("7. Exit");

         System.out.print("Enter choice: ");
         int choice = scanner.nextInt();
         scanner.nextLine();

         try {

           switch (choice) {

             case MenuOptions.Add_Doctor:
               addDoctor();
               break;

             case MenuOptions.Add_Patient:
               addPatient();
               break;

             case MenuOptions.Book_Appointment:
               createAppointment();
               break;

             case MenuOptions.View_Appointments:
               viewAppointments();
               break;

             case MenuOptions.Cancel_Appointment:
               cancelAppointment();
               break;

             case MenuOptions.Generate_Bill:
               generateBill();
               break;

             case MenuOptions.Exit:
               doctorService.saveDoctorsToCSV();
               logger.info("Exiting MediTrack...");
               return;

             default:
               logger.warning("Invalid option!");

           }

         } catch (Exception e) {

           logger.severe("Error: " + e.getMessage());

         }
       }
     }

     private static void addDoctor() {

       System.out.print("Doctor Name: ");
       String name = scanner.nextLine();

       System.out.print("Age: ");
       int age = scanner.nextInt();
       scanner.nextLine();

       System.out.println("Select Specialization:");
       for (Specialization s : Specialization.values()) {
         System.out.println(s.ordinal() + " - " + s);
       }

       int sp = scanner.nextInt();
       scanner.nextLine();

       System.out.print("Consultation Fee: ");
       double fee = scanner.nextDouble();

       String id = UUID.randomUUID().toString();

       Doctor doctor = new Doctor(
               id,
               name,
               age,
               Specialization.values()[sp],
               fee
       );

       doctorService.addDoctor(doctor);

       logger.info("Doctor added successfully!"+doctor.getId());


     }

     private static void addPatient() {

       System.out.print("Patient Name: ");
       String name = scanner.nextLine();

       System.out.print("Age: ");
       int age = scanner.nextInt();
       scanner.nextLine();

       System.out.print("Disease: ");
       String disease = scanner.nextLine();

       String id = UUID.randomUUID().toString();

       Patient patient = new Patient(id, name, age, disease);

       patientService.addPatient(patient);

       logger.info("Patient added successfully!"+patient.getId());

     }

     private static void createAppointment() {

       System.out.print("Patient ID: ");
       String patientId = scanner.nextLine();

       System.out.print("Doctor ID: ");
       String doctorId = scanner.nextLine();

       Patient patient = patientService.searchPatient(patientId);

       Doctor doctor = doctorService.getAllDoctors()
               .stream()
               .filter(d -> d.getId().equals(doctorId))
               .findFirst()
               .orElse(null);

       if (patient == null || doctor == null) {

         logger.warning("Invalid Doctor or Patient ID");
         return;

       }

       Appointment appointment = appointmentService.createAppointment(
               patient,
               doctor,
               LocalDateTime.now()
       );

       logger.info("Appointment created with ID: " + appointment.getAppointmentID());

     }

     private static void viewAppointments() {

       List<Appointment> appointments = appointmentService.getAppointments();

       for (Appointment a : appointments) {

         logger.info(
                 a.getAppointmentID() + " | " +
                         a.getPatient().getName() + " | " +
                         a.getDoctor().getName() + " | " +
                         a.getStatus()
         );

       }

     }

     private static void cancelAppointment() {

       System.out.print("Appointment ID: ");
       String id = scanner.nextLine();

       appointmentService.cancelAppointment(id);
       logger.info("Appointment cancelled!");

     }

     private static void generateBill() {

       System.out.print("Appointment ID: ");
       String id = scanner.nextLine();

       System.out.println("Select Billing Type");
       System.out.println("1 Standard");
       System.out.println("2 Insurance");
       System.out.println("3 Senior Citizen");

       int type = scanner.nextInt();
       scanner.nextLine();

       switch (type) {

         case 1:
           appointmentService.setBillingStrategy(new StandardBillingStrategy());
           break;

         case 2:
           appointmentService.setBillingStrategy(new InsuranceBillingStrategy());
           break;

         case 3:
           appointmentService.setBillingStrategy(new SeniorCitizenBillingStrategy());
           break;

         default:
           logger.warning("Invalid billing option");
           return;
       }

       BillSummary bill = appointmentService.generateBill(id);

       logger.info("Bill Generated | ID: " + bill.getBillId()
               + " | Amount: " + bill.getTotalAmount());

     }
   }


