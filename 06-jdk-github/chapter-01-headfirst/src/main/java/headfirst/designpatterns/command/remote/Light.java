package headfirst.designpatterns.command.remote;

/**
 * 灯
 *
 * @author lingwh
 * @date 2023/12/7 13:35
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
