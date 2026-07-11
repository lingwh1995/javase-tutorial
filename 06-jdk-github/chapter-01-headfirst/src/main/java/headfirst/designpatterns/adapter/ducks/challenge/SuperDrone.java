package headfirst.designpatterns.adapter.ducks.challenge;

/**
 * @author lingwh
 * @desc 超级无人机实现类
 * @date 2026/7/9 00:00
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
