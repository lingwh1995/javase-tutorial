package headfirst.designpatterns.command.remoteWL;

/**
 * 灯
 *
 * @author lingwh
 * @date 2023/12/7 11:37
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
