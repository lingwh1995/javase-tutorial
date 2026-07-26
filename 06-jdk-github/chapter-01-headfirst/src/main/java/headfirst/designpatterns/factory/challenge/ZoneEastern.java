package headfirst.designpatterns.factory.challenge;

/**
 * 东部时区
 *
 * @author lingwh
 * @date 2023/12/7 22:49
 */
public class ZoneEastern extends Zone {

    public ZoneEastern() {
        displayName = "US/Eastern";
        offset = -5;
    }
}
