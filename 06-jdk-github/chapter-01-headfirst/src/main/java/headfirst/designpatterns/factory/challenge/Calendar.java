package headfirst.designpatterns.factory.challenge;

import java.util.*;

/**
 * 日历抽象类
 *
 * @author lingwh
 * @date 2023/12/7 11:46
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
