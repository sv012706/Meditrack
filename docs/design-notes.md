# Design Notes

The system follows Object-Oriented Design and SOLID principles.

1. Architecture

The application follows a layered architecture to separate responsibilities.

Entity Layer → Represents domain objects (Doctor, Patient, Appointment, Bill).

Service Layer → Contains business logic for managing doctors, patients, and appointments.

Utility Layer → Provides helper classes like validation, CSV handling, and ID generation.

Constants Layer → Stores application-wide constants.

This improves maintainability, modularity, and scalability.

2. OOP Principles
   Encapsulation

All entity fields are private and accessed using getters and setters.

Example:

private String name;

This protects object state and enforces validation.

Inheritance

A base class Person is extended by:

Doctor

Patient

This avoids code duplication and promotes reuse.

Person
├── Doctor
└── Patient
Polymorphism

Method overloading is used in PatientService.

Example:

searchPatient(String id)
searchPatient(String name)
searchPatient(int age)

This allows flexible search operations.

Abstraction

Abstract classes and interfaces are used.

Examples:

Person → abstract class

Payable → interface

Searchable → interface

This hides implementation details and exposes only required functionality.

3. Design Patterns Used
   Singleton Pattern

Used in IdGenerator and DoctorService.

Purpose:
Ensure only one instance exists across the application.

Benefit:
Provides centralized ID generation.

Strategy Pattern

Used for Billing Calculation.

Billing strategies implemented:

StandardBillingStrategy

InsuranceBillingStrategy

SeniorCitizenBillingStrategy

Benefit:
Allows changing billing logic at runtime without modifying service code.

Factory Pattern

Used for Bill creation.

Example:

BillFactory.createBill(...)

Benefit:
Centralizes object creation and improves maintainability.

4. Collections Usage

The system uses Java Collections:

HashMap → storing doctors, patients, appointments

ArrayList → returning lists of objects

Benefit:
Efficient data storage and retrieval.

5. Generics

Generic class used:

DataStore<T>

Benefit:
Provides type-safe reusable storage for different entities.

6. Exception Handling

Custom exceptions implemented:

InvalidDataException

AppointmentNotFoundException

Purpose:
Provide meaningful error messages and better error handling.

7. Immutable Object

BillSummary is implemented as an immutable class.

Features:

final fields

no setters

Benefit:
Thread-safe and prevents accidental modification.

8. Enums

Enums replace string values.

Examples:

AppointmentStatus

Specialization

Benefit:
Improves type safety and readability.

9. Concurrency Support

AtomicInteger is used in IdGenerator.

Purpose:
Ensure thread-safe ID generation in concurrent environments.

10. Java 8 Features

Streams are used for filtering and searching.

Example:

doctorMap.values()
.stream()
.filter(d -> d.getSpecialization() == specialization)
.collect(Collectors.toList());

Benefit:
Cleaner and more expressive code.

11. Menu Driven Application

The Main class implements a console-based menu system to allow users to:

Add Doctor

Add Patient

Book Appointment

Cancel Appointment

Generate Bill