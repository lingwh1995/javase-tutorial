package headfirst.designpatterns.factory.challenge;

import java.util.*;

/**
 * @author lingwh
 * @desc 日历测试
 * @date 2026/7/9 00:00
 */
public class CalendarTestDrive {

    public static void main(String[] args) {
        ZoneFactory zoneFactory = new ZoneFactory();
        Calendar calendar = new PacificCalendar(zoneFactory);
        List<String> appts = Arrays.asList("appt 1", "appt 2");
        calendar.createCalendar(appts);
        calendar.print();
    }
}
