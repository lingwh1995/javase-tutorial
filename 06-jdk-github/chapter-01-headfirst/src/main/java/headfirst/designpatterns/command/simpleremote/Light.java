package headfirst.designpatterns.command.simpleremote;

/**
 * 灯
 *
 * @author lingwh
 * @date 2023/12/7 17:52
 */
public class Light {

    public Light() {
    }

    public void on() {
        System.out.println("Light is on");
    }

    public void off() {
        System.out.println("Light is off");
    }
}
