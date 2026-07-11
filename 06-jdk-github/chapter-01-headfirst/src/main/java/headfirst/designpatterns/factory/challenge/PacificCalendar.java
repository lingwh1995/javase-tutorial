package headfirst.designpatterns.factory.challenge;

import java.util.*;

/**
 * @author lingwh
 * @desc 太平洋日历
 * @date 2026/7/9 00:00
 */
public class PacificCalendar extends Calendar {
    public PacificCalendar(ZoneFactory zoneFactory) {
        zone = zoneFactory.createZone("US/Pacific");
        // make a calendar for the pacific zone
        // ...
    }

    public void createCalendar(List<String> appointments) {
        // make calendar from appointments
        System.out.println("Making the calendar");
    }
}
