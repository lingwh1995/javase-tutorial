package headfirst.designpatterns.facade.hometheater;

/**
 * @author lingwh
 * @desc 屏幕
 * @date 2026/7/9 00:00
 */
public class Screen {
    String description;

    public Screen(String description) {
        this.description = description;
    }

    public void up() {
        System.out.println(description + " going up");
    }

    public void down() {
        System.out.println(description + " going down");
    }

    public String toString() {
        return description;
    }
}
