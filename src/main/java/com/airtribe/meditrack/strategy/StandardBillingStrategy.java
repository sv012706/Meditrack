package com.airtribe.meditrack.strategy;

import com.airtribe.meditrack.constants.Constants;

public class StandardBillingStrategy implements BillingStrategy{
  @Override
  public double calculate(double consultationFee) {
    double tax=consultationFee* Constants.taxRate;
    return consultationFee+tax;
  }
}
