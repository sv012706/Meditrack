package com.airtribe.meditrack.entity;

import com.airtribe.meditrack.constants.Constants;
import com.airtribe.meditrack.interfaces.Payable;

public class Bill implements Payable {
  private String billId;
  private double consulationFee;

  public Bill(String billId, double consulationFee) {
    this.billId = billId;
    this.consulationFee = consulationFee;
  }

  public String getBillId() {
    return billId;
  }

  @Override
  public double calculateAmount() {
    return consulationFee+(consulationFee* Constants.taxRate);
  }
}
