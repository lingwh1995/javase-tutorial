package headfirst.designpatterns.factory.challenge;

import java.util.*;

/**
 * @author lingwh
 * @desc 日历抽象类
 * @date 2026/7/9 00:00
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
