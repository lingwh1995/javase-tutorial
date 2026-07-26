package headfirst.designpatterns.factory.challenge;

/**
 * 中部时区
 *
 * @author lingwh
 * @date 2023/12/7 08:26
 */
public class ZoneCentral extends Zone {

    public ZoneCentral() {
        displayName = "US/Central";
        offset = -6;
    }
}
