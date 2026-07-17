package headfirst.designpatterns.adapter.ducks.challenge;

/**
 * 超级无人机实现类
 *
 * @author lingwh
 * @date 2026/7/9 19:02
 */
public class SuperDrone implements Drone {

    public void beep() {
        System.out.println("Beep beep beep");
    }

    public void spin_rotors() {
        System.out.println("Rotors are spinning");
    }

    public void take_off() {
        System.out.println("Taking off");
    }
}
