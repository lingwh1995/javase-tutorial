package headfirst.designpatterns.command.remote;

/**
 * 车库门
 *
 * @author lingwh
 * @date 2023/12/7 19:07
 */
public class GarageDoor {

    String location;

    public GarageDoor(String location) {
        this.location = location;
    }

    public void up() {
        System.out.println(location + " garage Door is Up");
    }

    public void down() {
        System.out.println(location + " garage Door is Down");
    }

    public void stop() {
        System.out.println(location + " garage Door is Stopped");
    }

    public void lightOn() {
        System.out.println(location + " garage light is on");
    }

    public void lightOff() {
        System.out.println(location + " garage light is off");
    }
}
