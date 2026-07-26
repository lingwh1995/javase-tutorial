package headfirst.designpatterns.factory.challenge;

import java.util.*;

/**
 * 太平洋日历
 *
 * @author lingwh
 * @date 2023/12/7 09:40
 */
public class PacificCalendar extends Calendar {

    public PacificCalendar(ZoneFactory zoneFactory) {
        zone = zoneFactory.createZone("US/Pacific");
        // make a calendar for the pacific zone
        // ...
    }

    @Override
    public void createCalendar(List<String> appointments) {
        // make calendar from appointments
        System.out.println("Making the calendar");
    }
}
