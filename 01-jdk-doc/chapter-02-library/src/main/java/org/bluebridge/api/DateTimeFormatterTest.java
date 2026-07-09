package org.bluebridge.api;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * SimpleDateFormat.parse()线程安全问题以及解决
 *      方式二：使用不可变类DateTimeFormatter解决日期类线程安全问题
 *
 * DateTimeFormatter类定义
 *      //This class is immutable and thread-safe.
 *
 *      public final class DateTimeFormatter {
 *      }
 */
public class DateTimeFormatterTest {

    public static void main(String[] args) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                LocalDate date = dtf.parse("2018-10-01", LocalDate::from);
                System.out.println("date = " + date);
            }).start();
        }
    }
}
