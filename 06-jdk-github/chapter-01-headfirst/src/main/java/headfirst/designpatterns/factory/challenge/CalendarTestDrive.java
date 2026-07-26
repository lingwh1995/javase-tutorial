package headfirst.designpatterns.factory.challenge;

import java.util.*;

/**
 * 日历测试
 *
 * @author lingwh
 * @date 2023/12/7 10:17
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
