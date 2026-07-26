package headfirst.designpatterns.facade.hometheater;

/**
 * 屏幕
 *
 * @author lingwh
 * @date 2023/12/7 12:14
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

    @Override
    public String toString() {
        return description;
    }
}
