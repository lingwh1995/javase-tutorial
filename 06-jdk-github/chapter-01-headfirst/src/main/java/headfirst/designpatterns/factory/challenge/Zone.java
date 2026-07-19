package headfirst.designpatterns.factory.challenge;

/**
 * 时区基类
 *
 * @author lingwh
 * @date 2026/7/9 00:00
 */
public class Zone {

    String displayName;
    int offset;

    public String getDisplayName() {
        return displayName;
    }

    public int getOffset() {
        return offset;
    }
}
