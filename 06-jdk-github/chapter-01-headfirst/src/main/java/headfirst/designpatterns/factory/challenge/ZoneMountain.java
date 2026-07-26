package headfirst.designpatterns.factory.challenge;

/**
 * 山区时区
 *
 * @author lingwh
 * @date 2023/12/7 22:12
 */
public class ZoneMountain extends Zone {

    public ZoneMountain() {
        displayName = "US/Mountain";
        offset = -7;
    }
}
