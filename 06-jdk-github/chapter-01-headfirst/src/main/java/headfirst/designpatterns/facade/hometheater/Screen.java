package headfirst.designpatterns.facade.hometheater;

/**
 * 屏幕
 *
 * @author lingwh
 * @date 2026/4/21 19:02
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
