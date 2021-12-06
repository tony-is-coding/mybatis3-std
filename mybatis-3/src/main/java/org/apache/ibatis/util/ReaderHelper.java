package org.apache.ibatis.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.atomic.AtomicInteger;

public final class ReaderHelper {
  private static final ReaderHelper helper = new ReaderHelper();
  private final AtomicInteger step = new AtomicInteger();

  private Integer incrStep() {
    return step.incrementAndGet();
  }

  public static void tip(String msg) {
    StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
    StackTraceElement callStack = stackTrace[2];
    String method = callStack.getClassName() + "." + callStack.getMethodName();
    String outMsg = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) +
      " [步骤 " + helper.incrStep() + "] " + method;
    if (msg != null && !"".equals(msg)) {
      outMsg = outMsg + " =>  " + msg;
    }
    System.out.println(outMsg);
  }
}
