package headfirst.designpatterns.factory.challenge;

/**
 * 时区基类
 *
 * @author lingwh
 * @date 2023/12/7 09:03
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
