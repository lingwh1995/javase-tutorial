package headfirst.designpatterns.command.remote;

/**
 * @author lingwh
 * @desc 灯
 * @date 2026/7/9 00:00
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
