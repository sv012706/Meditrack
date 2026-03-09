package com.airtribe.meditrack.strategy;

import com.airtribe.meditrack.constants.Constants;

public class InsuranceBillingStrategy implements BillingStrategy{
  @Override
  public double calculate(double consultationFee) {
    double tax=consultationFee* Constants.insuranceDiscount;
    return consultationFee+tax;
  }
}
