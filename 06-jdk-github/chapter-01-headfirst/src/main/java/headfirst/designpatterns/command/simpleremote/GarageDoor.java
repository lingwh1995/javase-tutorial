package headfirst.designpatterns.command.simpleremote;

/**
 * 车库门
 *
 * @author lingwh
 * @date 2023/12/7 19:53
 */
public class GarageDoor {

    public GarageDoor() {
    }

    public void up() {
        System.out.println("Garage Door is Open");
    }

    public void down() {
        System.out.println("Garage Door is Closed");
    }

    public void stop() {
        System.out.println("Garage Door is Stopped");
    }

    public void lightOn() {
        System.out.println("Garage light is on");
    }

    public void lightOff() {
        System.out.println("Garage light is off");
    }
}
