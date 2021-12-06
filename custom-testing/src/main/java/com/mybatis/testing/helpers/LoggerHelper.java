package com.mybatis.testing.helpers;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LoggerHelper {
    public static void log(String msg) {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        StackTraceElement callStack = stackTrace[2];
        String method = callStack.getClassName() + "." + callStack.getMethodName();
        System.out.println(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) + " | " + method + " =>  " + msg);
    }
}
