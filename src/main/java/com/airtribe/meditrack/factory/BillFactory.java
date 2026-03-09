package com.airtribe.meditrack.factory;

import com.airtribe.meditrack.entity.Bill;

public class BillFactory {
  public static Bill createBill(String billId,double consultationFee)
  {
    return new Bill(billId,consultationFee);
  }
}
