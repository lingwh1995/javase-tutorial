package headfirst.designpatterns.facade.hometheater;

/**
 * @author lingwh
 * @desc 影院灯光
 * @date 2026/7/9 00:00
 */
public class TheaterLights {
    String description;

    public TheaterLights(String description) {
        this.description = description;
    }

    public void on() {
        System.out.println(description + " on");
    }

    public void off() {
        System.out.println(description + " off");
    }

    public void dim(int level) {
        System.out.println(description + " dimming to " + level + "%");
    }

    public String toString() {
        return description;
    }
}
