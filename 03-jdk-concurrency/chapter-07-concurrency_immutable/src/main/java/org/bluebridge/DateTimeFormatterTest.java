package org.bluebridge;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * DateTimeFormatter 线程安全测试
 *
 * @author lingwh
 * @date 2026/4/21 15:45
 */
public class DateTimeFormatterTest {

    public static void main(String[] args) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        for (int i = 0; i < 10; i++) {
            new Thread(
                    () -> {
                        LocalDate date = dtf.parse("2018-10-01", LocalDate::from);
                        System.out.println("date = " + date);
                    })
                    .start();
        }
    }
}
