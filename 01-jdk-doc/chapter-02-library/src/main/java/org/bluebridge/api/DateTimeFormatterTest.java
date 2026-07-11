package org.bluebridge.api;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * @author lingwh
 * @desc DateTimeFormatter线程安全测试
 * @date 2026/7/9 00:00
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
