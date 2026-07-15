package headfirst.designpatterns.command.remote;

/**
 * 灯
 *
 * @author lingwh
 * @date 2026/4/21 19:02
 */
public class Light {

    String location = "";

    public Light(String location) {
        this.location = location;
    }

    public void on() {
        System.out.println(location + " light is on");
    }

    public void off() {
        System.out.println(location + " light is off");
    }
}
