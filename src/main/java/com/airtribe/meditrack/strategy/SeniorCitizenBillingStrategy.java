package com.airtribe.meditrack.strategy;


import com.airtribe.meditrack.constants.Constants;

public class SeniorCitizenBillingStrategy implements BillingStrategy {
  @Override
  public double calculate(double consultationFee) {
    double tax=consultationFee* Constants.seniorCitizenDiscount;
    return consultationFee+tax;
  }
}
