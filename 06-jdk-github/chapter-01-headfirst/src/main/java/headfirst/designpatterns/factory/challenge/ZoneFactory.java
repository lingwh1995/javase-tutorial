package headfirst.designpatterns.factory.challenge;

/**
 * 时区工厂
 *
 * @author lingwh
 * @date 2026/7/9 00:00
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
