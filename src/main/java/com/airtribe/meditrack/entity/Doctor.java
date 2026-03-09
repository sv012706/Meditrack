package com.airtribe.meditrack.entity;

import com.airtribe.meditrack.enums.Specialization;

public class Doctor extends Person{
  private Specialization specialization;
  private double consultaionFees;


  public Doctor(String id, String name, int age, Specialization specialization, double consultaionFees) {
    super(id, name, age);
    this.specialization = specialization;
    this.consultaionFees = consultaionFees;
  }

  public Specialization getSpecialization() {
    return specialization;
  }

  public double getConsultationFees() {
    return consultaionFees;
  }

  @Override
  public String toString() {
    return "Doctor{" +
            "id='" + id + '\'' +
            ", name='" + name + '\'' +
            ", age=" + age +
            ", specialization=" + specialization +
            ", consultaionFees=" + consultaionFees +
            '}';
  }
}
