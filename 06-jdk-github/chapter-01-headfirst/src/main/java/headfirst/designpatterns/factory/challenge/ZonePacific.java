package headfirst.designpatterns.factory.challenge;

/**
 * 太平洋时区
 *
 * @author lingwh
 * @date 2023/12/7 11:43
 */
public class ZonePacific extends Zone {

    public ZonePacific() {
        displayName = "US/Pacific";
        offset = -8;
    }
}
