package com.airtribe.meditrack.entity;

public class Patient extends Person implements Cloneable{
  private String disease;

  public Patient(String id, String name, int age, String disease) {
    super(id, name, age);
    this.disease = disease;
  }

  public String getDisease() {
    return disease;
  }

  public void setDisease(String disease) {
    this.disease = disease;
  }
  @Override
  public Patient clone()  {
  try{
    return (Patient) super.clone();
  }catch (CloneNotSupportedException e)
    {
     throw new RuntimeException("cloning failed");
    }
}

  @Override
  public String toString() {
    return "Patient{" +
            "id='" + id + '\'' +
            ", name='" + name + '\'' +
            ", age=" + age +
            ", disease='" + disease + '\'' +
            '}';
  }
}
