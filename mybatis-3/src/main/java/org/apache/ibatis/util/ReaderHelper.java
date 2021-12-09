package org.apache.ibatis.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

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
    String outMsg = LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")) +
      " [" + helper.incrStep() + "] " + method;
    if (msg != null && !"".equals(msg)) {
      outMsg = outMsg + " =>  " + msg;
    }
    System.out.println(outMsg);
  }

  public static void formatTrace() {
    StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
    StackTraceElement callStack = stackTrace[2];
    List<StackTraceElement> printTraces = Arrays
      .stream(stackTrace)
      .filter(e -> e.toString().startsWith("org.apache.ibatis"))
      .collect(Collectors.toList());
    if (printTraces.size() > 2) {
      printTraces = printTraces.subList(1, Math.min(10, printTraces.size()));
    }

    System.out.println("打印调用追踪: " + callStack.getClassName() + "." + callStack.getMethodName());
    String space = "|__";
    for (StackTraceElement traceElement : printTraces) {
      System.out.println(space + traceElement.toString());
      space = "  " + space;
    }
  }

}
