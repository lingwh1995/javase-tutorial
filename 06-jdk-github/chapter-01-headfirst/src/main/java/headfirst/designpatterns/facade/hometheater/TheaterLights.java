package headfirst.designpatterns.facade.hometheater;

/**
 * 影院灯光
 *
 * @author lingwh
 * @date 2023/12/7 13:39
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

    @Override
    public String toString() {
        return description;
    }
}
