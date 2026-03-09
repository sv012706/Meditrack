package com.airtribe.meditrack.util;

import java.util.concurrent.atomic.AtomicInteger;

public class IdGenerator {
  private static IdGenerator instance = new IdGenerator();
  private AtomicInteger counter = new AtomicInteger(1000);

  private IdGenerator() {
  }

  public static IdGenerator getInstance() {
    return instance;
  }

  public String GenerateId(String prefix) {
    return prefix +counter.incrementAndGet();
  }
}
