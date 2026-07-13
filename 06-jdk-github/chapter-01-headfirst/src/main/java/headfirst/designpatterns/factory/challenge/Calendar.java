package headfirst.designpatterns.factory.challenge;

import java.util.*;

/**
 * 日历抽象类
 *
 * @author lingwh
 * @date 2026/4/21 19:02
 */
public abstract class Calendar {

    Zone zone;

    public void print() {
        System.out.println("--- " + zone.getDisplayName() + " Calendar ---");
        // print all appointments in correct time zone
        System.out.println("Offset from GMT: " + zone.getOffset());
    }

    public abstract void createCalendar(List<String> appointments);
}
