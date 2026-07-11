package org.bluebridge.api;

import java.util.Calendar;
import org.junit.Test;

/**
 * @author lingwh
 * @desc Calendar类api
 * @date 2026/7/9 00:00
 */
public class CalendarTest {

    @Test
    public void testCalendar() {
        Calendar cal = Calendar.getInstance();
        System.out.println("DAY_OF_WEEK: " + cal.get(Calendar.DAY_OF_WEEK));
        System.out.println("DAY_OF_MONTH: " + cal.get(Calendar.DAY_OF_MONTH));
        System.out.println("DAY_OF_YEAR: " + cal.get(Calendar.DAY_OF_YEAR));
        System.out.println("YEAR: " + cal.get(Calendar.YEAR));
        System.out.println("MONTH: " + cal.get(Calendar.MONTH));
        System.out.println("HOUR_OF_DAY : " + cal.get(Calendar.HOUR_OF_DAY));
        System.out.println("MINUTE : " + cal.get(Calendar.MINUTE));
        System.out.println("SECOND : " + cal.get(Calendar.SECOND));
    }
}
