package headfirst.designpatterns.factory.challenge;

/**
 * 时区工厂
 *
 * @author lingwh
 * @date 2023/12/7 11:40
 */
public class ZoneFactory {

    public Zone createZone(String zoneId) {
        Zone zone = null;
        if (zoneId == "US/Pacific") {
            zone = new ZonePacific();
        } else if (zoneId == "US/Mountain") {
            zone = new ZoneMountain();
        } else if (zoneId == "US/Central") {
            zone = new ZoneCentral();
        } else if (zoneId == "US/Eastern") {
            zone = new ZoneEastern();
        }
        return zone;
    }
}
