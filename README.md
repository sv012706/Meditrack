MediTrack – Clinic & Appointment Management System
Project Overview

MediTrack is a modular Clinic & Appointment Management System built using Core Java.
The application manages patients, doctors, appointments, and billing while demonstrating Object-Oriented Programming, Java Collections, Exception Handling, and Design Patterns.

The project is designed to practice Java fundamentals, system design principles, and clean architecture.

Features

Doctor Management (Add / Search / View)

Patient Management (Add / Search / View)

Appointment Booking

Appointment Cancellation

Billing System

Multiple Billing Strategies

Console Menu-Based Application

Custom Exception Handling

CSV File Utility Support

Unique ID Generation

Technologies Used

Java (JDK 8+)

Java Collections Framework

Java Streams & Lambdas

Object-Oriented Programming

Design Patterns

File I/O (CSV)

Git & GitHub

Design Patterns Used
Singleton Pattern

Used in IdGenerator and DoctorService to ensure only one instance exists.

Strategy Pattern

Used for billing calculations.

Billing strategies implemented:

StandardBillingStrategy

InsuranceBillingStrategy

SeniorCitizenBillingStrategy

Allows billing logic to change at runtime.

Factory Pattern

Used in BillFactory to centralize bill object creation.

Project Structure
src/main/java/com/airtribe/meditrack/

Main.java

constants/
   Constants.java

entity/
   Person.java
   Doctor.java
   Patient.java
   Appointment.java
   Bill.java
   BillSummary.java

service/
   DoctorService.java
   PatientService.java
   AppointmentService.java

util/
   Validator.java
   DateUtil.java
   CSVUtil.java
   IdGenerator.java
   DataStore.java
   AIHelper.java

exception/
   AppointmentNotFoundException.java
   InvalidDataException.java

interface/
   Searchable.java
   Payable.java

strategy/
   BillingStrategy.java
   StandardBillingStrategy.java
   InsuranceBillingStrategy.java
   SeniorCitizenBillingStrategy.java

factory/
   BillFactory.java

test/
   TestRunner.java
OOP Concepts Demonstrated

Encapsulation

Inheritance

Polymorphism

Abstraction

Immutable Objects

Enums

Generics

Additional Java Concepts

Java Collections (HashMap, ArrayList)

Streams & Lambdas

Exception Handling

AtomicInteger

File I/O

Command Line Application

How to Run the Project
1. Clone Repository
git clone https://github.com/your-username/meditrack.git
2. Compile
javac Main.java
3. Run
java Main
Sample Menu
====== MediTrack Clinic System ======

1 Add Doctor
2 Add Patient
3 Book Appointment
4 View Appointments
5 Cancel Appointment
6 Generate Bill
7 Exit
Example Output
Doctor added successfully
Patient added successfully
Appointment booked successfully
Bill Generated
Total Amount: 1180
Learning Outcomes

This project demonstrates:

Core Java programming

Design Patterns implementation

Clean architecture design

Exception handling strategies

Real-world backend system modeling

Future Improvements

Database integration (MySQL)

REST API with Spring Boot

Web UI using React

Notification system

Authentication & authorization

Author

Srikanth
Software Engineer
