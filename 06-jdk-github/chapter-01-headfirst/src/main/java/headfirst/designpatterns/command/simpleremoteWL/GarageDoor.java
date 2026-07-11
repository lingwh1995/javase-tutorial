package headfirst.designpatterns.command.simpleremoteWL;

/**
 * @author lingwh
 * @desc 车库门
 * @date 2026/7/9 00:00
 */
public class GarageDoor {

    public GarageDoor() {}

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
